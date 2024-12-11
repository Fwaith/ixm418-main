package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {

    public Drop(String item) {
        this.commandType = CommandType.DROP;
        this.value = item;
    }
    //Creates a new Drop command for the specified item.
    //Parameters: item - the name of the item to be dropped

    @Override
    public String toString() {
        return "Drop command: drop " + value;
    }
    //Returns a string representation of the drop command, including its type and the target item.
    //Overrides: toString in class Object
    //Returns: a string describing the drop command
    
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();

        // Check if the player has the item
        Item item = player.getItem(value);
        if (item != null) {
            player.getInventory().remove(item);
            currentRoom.addItem(item);
            return "You drop: " + value;
        }

        // Check if the player has the equipment
        Equipment equipment = player.getEquipment(value);
        if (equipment != null) {
            player.getEquipment().remove(equipment);
            currentRoom.addEquipment(equipment);
            return "You drop: " + value;
        }

        return "You cannot drop " + value;
    }
    //Executes the drop command. If the player possesses the specified item, it is removed from their inventory and added to the current room. Otherwise, an error message is returned.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game
    //Returns: a string describing the outcome of the command

}