package uk.camsw.shimrr.tutorial.usecase2

import org.scalatest.FreeSpec
import org.scalatest.Matchers._

class InMemoryRepositoryTest extends FreeSpec {

  "given a hard-coded in memory repository of products" - {
    "find all should return all products" in {
      InMemoryRepository.findAll() should contain only (
        BicycleV1("Raleigh", "Grifter", 124.99f),
        BicycleV1("Diamond Back", "Grind 2018", 230f),
        BicycleV2("Mondraker", "Podium Carbon", 4395f, leadTime = 14))
    }
  }
}
