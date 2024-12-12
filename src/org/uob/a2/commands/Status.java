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

    @Override
    public String toString() {
        return "Status command: status " + value;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();

        if ("inventory".equalsIgnoreCase(value)) {
            StringBuilder result = new StringBuilder("Inventory:\n");
            for (Item item : player.getInventory()) {
                result.append("- ").append(item.getName()).append("\n");
            }
            for (Equipment equipment : player.getEquipment()) {
                result.append("- ").append(equipment.getName()).append("\n");
            }
            return result.toString();
        } else if ("map".equalsIgnoreCase(value)) {
            return gameState.getMap().renderMap(player);
        } else if ("score".equalsIgnoreCase(value)) {
            return "Your score is: " + player.getScore();
        } else if ("player".equalsIgnoreCase(value)) {
            return player.toString();
        }

        Item item = player.getItem(value);
        Equipment equipment = player.getEquipment(value);

        if (item != null) {
            return item.getDescription();
        }
        if (equipment != null) {
            return equipment.getDescription();
        }

        return "No such topic or object in your inventory.";
    }

    //Executes the status command. Retrieves and displays information based on the specified topic.
    //If the topic is "inventory", it lists all items in the player's inventory.
    //If the topic matches an item name, it displays the item's description.
    //If the topic is "player", it displays the player's general status.
    //Specified by: execute in class Command
    //Parameters:gameState - the current state of the game
    //Returns: a string describing the requested status information

}