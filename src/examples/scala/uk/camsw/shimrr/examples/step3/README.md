# Scenario 3 - More field changes

The CTO is becoming a little obsessed with the *user journey* monitoring and you become aware of a 
ton of stories on the backlog that will result in the addition and removal of fields on your 
bicycle model.  The CTO is keen to deploy each of these changes individually in order that their impact
can be assessed

You tell the CTO about your concerns:

- The version migration code contains quite a lot of boiler-plate
- As upgraded versions are never persisted back, there's a combinatorial explosion of migrations appearing 
- Testing all possible upgrade paths is getting more difficult (and boring!)
- A missed case would lead to data being dropped or (at best) a more visible error in production may occur

The CTO is about to rush out the door as he has a meeting with a potential investor. As he/ she leaves
he/ she mutters something about a library he read about on *Stack Overflow* called *SHIMRR* that might help.

    "Oh and by the way, can you pick up the story to remove the lead-time field ASAP please?"
  

## Use Case

As a potential customer 
- I no longer want to see the lead time 
- as it's too inaccurate and leading to complaints 
- thus impacting my company's reputation


## You decide that you need to

- Add a new version of the bicycle because you already have a mixture of V1 & V2 in the store
- Create migrations V1 ~> V3 and V2 ~> V3
- Try shimrr, having read through this tutorial 


 



    

