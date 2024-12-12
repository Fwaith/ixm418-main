package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {

    public Get(String item) {
        this.commandType = CommandType.GET;
        this.value = item;
    }
    //Creates a new Get command for the specified item.
    //Parameters: item - the name of the item to be picked up

    @Override
    public String toString() {
        return "Get command: get " + value;
    }
    //Returns a string representation of the get command, including its type and the target item.
    //Overrides: toString in class Object
    //Returns: a string describing the get command

    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();

        // Check if the item exists in the room
        Item item = currentRoom.getItemByName(value);
        if (item != null) {
            if (player.hasItem(value)) {
                return "You already have " + value;
            }
            player.addItem(item);
            currentRoom.getItems().remove(item);
            gameState.incrementScore(5);
            return "You pick up: " + value;
        }

        // Check if the equipment exists in the room
        Equipment equipment = currentRoom.getEquipmentByName(value);
        if (equipment != null) {
            if (player.hasEquipment(value)) {
                return "You already have " + value;
            }
            player.addEquipment(equipment);
            currentRoom.getEquipments().remove(equipment);
            gameState.incrementScore(5);
            return "You pick up: " + value;
        }

        return "No " + value + " to get.";
    }
    //Executes the get command. If the specified item is present in the current room and the player does not already have it, the item is added to the player's inventory. Otherwise, an appropriate message is returned.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game
    //Returns: a string describing the outcome of the command

}