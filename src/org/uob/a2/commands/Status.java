package org.uob.a2.commands;

import java.lang.reflect.Constructor;
import org.uob.a2.gameobjects.*;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
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
            return item.getDescription(); // Ensure description is returned
        }
        if (equipment != null) {
            return equipment.getDescription(); // Ensure description is returned
        }

        return "No such topic or object in your inventory.";
    }
}