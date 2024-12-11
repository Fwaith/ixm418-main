package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {

    public Status(String topic) {
        this.commandType = CommandType.STATUS;
        this.value = topic;
    }
    //Creates a new Status command for the specified topic.
    //Parameters: topic - the topic to retrieve the status for, such as "inventory", "player", or an item name
    
    @Override
    public String toString() {
        return "Status command: status " + value;
    }
    //Returns a string representation of the status command, including its type and topic.
    //Overrides: toString in class Object
    //Returns: a string describing the status command

    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();

        if ("inventory".equalsIgnoreCase(value)) {
            return player.getInventory().toString();
        } else if ("player".equalsIgnoreCase(value)) {
            return player.toString();
        } else {
            GameObject item = player.getItem(value);
            if (item == null) {
                item = player.getEquipment(value);
            }
            return item != null ? item.getDescription() : "";
        }
    }
    //Executes the status command. Retrieves and displays information based on the specified topic.
    //If the topic is "inventory", it lists all items in the player's inventory.
    //If the topic matches an item name, it displays the item's description.
    //If the topic is "player", it displays the player's general status.
    //Specified by: execute in class Command
    //Parameters:gameState - the current state of the game
    //Returns: a string describing the requested status information

}