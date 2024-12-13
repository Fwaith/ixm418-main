package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;


/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 */
public class Use extends Command {

    private final String target;

    public Use(String equipmentName, String target) {
        this.commandType = CommandType.USE;
        this.value = equipmentName;
        this.target = target;
    }

    @Override
    public String toString() {
        return "Use command: use " + value + " on " + target;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Equipment equipment = player.getEquipment(value);

        if (equipment == null) {
            return "You do not have " + value + ".";
        }

        Room currentRoom = gameState.getMap().getCurrentRoom();

        // Debugging: Check the current room contents
        System.out.println("Debug: Current room: " + currentRoom.getName());
        System.out.println("Debug: Equipment: " + equipment.getName());

        // Using pickaxe on wall
        if (value.equalsIgnoreCase("pickaxe") && target.equalsIgnoreCase("wall")) {
            // Retrieve wall container and exit
            Container wall = (Container) currentRoom.getFeatureByName("wall");
            Exit hiddenExit = currentRoom.getExit("e3");

            // Debugging: Verify the wall and exit
            System.out.println("Debug: Wall found: " + (wall != null));
            System.out.println("Debug: Wall hidden: " + (wall != null && wall.isHidden()));
            System.out.println("Debug: Exit e3 found: " + (hiddenExit != null));
            System.out.println("Debug: Exit e3 hidden: " + (hiddenExit != null && hiddenExit.isHidden()));

            // Check if wall exists and exit is hidden
            if (wall != null && !wall.isHidden() && hiddenExit != null && hiddenExit.isHidden()) {
                wall.setHidden(true); // Hide the wall
                hiddenExit.setHidden(false); // Reveal the exit
                player.removeEquipment(equipment); // Remove the pickaxe
                return "You mine away at the wall, revealing a new path!";
            }
            return "There is nothing to mine here.";
        }

        // Other use cases (key, pick, TNT, etc.)

        return "Invalid use target.";
    }
}