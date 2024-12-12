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
            return "You do not have " + value;
        }

        UseInformation useInfo = equipment.getUseInformation();

        // Check if the equipment has already been used
        if (useInfo.isUsed()) {
            return "You have already used " + value;
        }

        Room currentRoom = gameState.getMap().getCurrentRoom();

        // Handle specific cases for equipment use
        if ("key".equalsIgnoreCase(value)) {
            if ("box".equalsIgnoreCase(target)) {
                return "You use the key to open the box. The box explodes and kills you. Game Over.";
            }
        } else if ("pickaxe".equalsIgnoreCase(value)) {
            Exit hiddenExit = currentRoom.getExit("e3");
            if (hiddenExit != null && hiddenExit.isHidden()) {
                hiddenExit.setHidden(false);
                useInfo.setUsed(true);
                return "You mine away at the wall revealing a new path!";
            }
        } else if ("tnt".equalsIgnoreCase(value)) {
            Exit hiddenExit = currentRoom.getExit("e9");
            if (hiddenExit != null && hiddenExit.isHidden()) {
                hiddenExit.setHidden(false);
                useInfo.setUsed(true);
                return "You use the TNT. It blows up the boulder and reveals a new path!";
            }
        } else if ("pearl".equalsIgnoreCase(value)) {
            return "You use the pearl and escape the dungeon. You win!";
        }

        // Handle container interaction
        GameObject targetObject = currentRoom.getFeatureByName(target);
        if (targetObject instanceof Container container) {
            if (useInfo.getTarget().equals(container.getId())) {
                useInfo.setUsed(true);
                for (GameObject obj : container.open()) {
                    obj.setHidden(false);
                }
                return useInfo.getMessage();
            }
        }

        return "Invalid use target.";
    }
}