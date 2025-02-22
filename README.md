## MUDController – Methods talk() and open()
1.talk() Method
This method allows the player to interact with NPCs (Non-Player Characters) in the current room.

## Functionality:

If an NPC is present in the room, the method prints the NPC’s name and their dialogue.
If no NPC is found, it informs the player that there is no one to talk to.

Example Usage:
> talk

You talk to Merchant: "Welcome, traveler! Care to trade?"
If no NPC is present:

> talk

There is no one to talk to here.

2.open(String object) Method
This method enables the player to open interactive objects in the room, such as doors, chests, or containers.

## Functionality:
If no object is specified, it prompts the player to provide an object to open.
If the specified object exists in the room, the method prints the contents found inside.
If the object is not present in the room, it notifies the player that nothing can be opened.

Example Usage:
> open chest

You open the chest and find: a golden key, 20 coins.
If the object is missing:

> open door

There is no door here to open.

## Summary
These methods improve player interaction within the game, allowing communication with NPCs and opening objects for potential discoveries. 🚀