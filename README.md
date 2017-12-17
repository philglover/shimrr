# shimmr
A type-safe, read-repair shim dsl

## The Build
### Where is Jenkins?
http://camsw.darktech.org:8080/job/shimmr

### To Build and test
```bash
sbt clean update test
```

## The backlog
### Where is trello
https://trello.com/b/tsoNCghn/shimmr

## Example

###
```scala
trait ExampleMigrationRules {

  import shapeless.syntax.singleton.mkSingletonOps

  private[shimrr] val DefaultAge = -99


  // Here we define our rules for defaulting fields.
  // Currently there must be an entry for every new field added
  // and they apply globally across
  // all possible migrations for a given coproduct
  private[shimrr] val fieldDefaultRules =
      'age ->> DefaultAge ::
      HNil

  // We must specify the type of our field defaulter
  type FIELD_DEFAULTS = fieldDefaultRules.type
}

class ExampleTest extends WordSpec with MigrationContext with ExampleMigrationRules {

  // We need to import the syntax to get our migrateTo extensions
  import syntax._

  // And provide a stable reference to our field defaults
  val fieldDefaults = fieldDefaultRules

  "Given we have setup field defaulters, we can migrate from V1 to V2" in {
    CustomerV1("Leon").migrateTo[CustomerV2] shouldBe CustomerV2("Leon", DefaultAge)
  }

  "In the other direction, missing fields are dropped" in {
    CustomerV2("Leon", 43).migrateTo[CustomerV1] shouldBe CustomerV1("Leon")
  }

  "Re-ordered fields are aligned automatically" in {
    CustomerV2("Leon", 43).migrateTo[CustomerV3] shouldBe CustomerV3(43, "Leon")
  }

  "Migration rules compose" in {
    CustomerV1("Leon").migrateTo[CustomerV3] shouldBe CustomerV3(DefaultAge, "Leon")
  }

  "Co-products are supported (with a little type help)" in {
    val xs: List[Customer] = List(
      CustomerV1("Leon"),
      CustomerV2("Leon", 43),
      CustomerV3(43, "Leon")
    )

    xs.migrateTo[CustomerV3] shouldBe List(
      CustomerV3(DefaultAge, "Leon"),
      CustomerV3(43, "Leon"),
      CustomerV3(43, "Leon")
    )
  }

  "New fields with associated default, fail to compile" in {
    case class CustomerV4(name: String, postCode: String)
    assertTypeError("""CustomerV1("Leon").migrateTo[CustomerV4]""")
  }

}

```

