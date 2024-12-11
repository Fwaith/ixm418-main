package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {

    public Look(String target) {
        this.commandType = CommandType.LOOK;
        this.value = target;
    }
    //Creates a new Look command for the specified target.
    //Parameters: target - the object or category to examine, such as "room", "exits", "features", or the name of a specific item
    
    @Override
    public String toString() {
        return "Look command: look " + value;
    }
    //Returns a string representation of the look command, including its type and target.
    //Overrides: toString in class Object
    //Returns: a string describing the look command

    public String execute(GameState gameState) {
        Room currentRoom = gameState.getMap().getCurrentRoom();

        switch (value.toLowerCase()) {
            case "room":
                return currentRoom.getDescription() + "\n" + currentRoom.toString();
            case "exits":
                return "The available exits are:\n" + currentRoom.getExits();
            case "features":
                return "You also see:\n" + currentRoom.getFeatures();
            default:
                GameObject object = currentRoom.getItemByName(value);
                if (object == null) {
                    object = currentRoom.getFeatureByName(value);
                }
                return object != null ? object.getDescription() : "";
        }
    }
    //Executes the look command. Provides descriptions based on the specified target:
    //If the target is "room", it displays the room's description and all visible objects.
    //If the target is "exits", it lists the visible exits in the room.
    //If the target is "features", it lists additional visible features in the room.
    //If the target matches an item, feature or equipment name, it displays the description of that object.
    //Hidden objects are not included unless they are explicitly revealed in the game state.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game
    //Returns: a string describing the requested details about the room, exits, features, or specific object

}