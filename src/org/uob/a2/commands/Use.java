package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
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

        // Using the pickaxe on the wall
        if (value.equalsIgnoreCase("pickaxe") && target.equalsIgnoreCase("wall")) {
            Container wall = (Container) currentRoom.getFeatureByName("wall");
            Exit hiddenExit = currentRoom.getExit("east");

            if (wall != null) {
                wall.setHidden(true); // Hide the wall
                if (hiddenExit != null) {
                    hiddenExit.setHidden(false); // Reveal the hidden exit
                }
                player.getEquipment().remove(equipment); // Remove the pickaxe from the player's inventory
                player.updateScore(25); // Add 25 points for using equipment
                return "You mine away at the wall with the pickaxe, revealing a new path!";
            }
            return "The wall is already mined or does not exist.";
        }

        // Using a key to open the box
        if (value.equalsIgnoreCase("key") && target.equalsIgnoreCase("box")) {
            Container box = (Container) currentRoom.getFeatureByName("box");
            if (box != null && !box.isHidden()) {
                player.getEquipment().remove(equipment); // Remove the key from the player's inventory
                box.setHidden(true); // Hide the box
                currentRoom.setId("r11"); // Set the room ID to r11
                player.updateScore(25); // Add 25 points for using equipment
                return "You use the key to open the box. The box explodes and kills you. Game over.";
            }
            return "There is no box to open here.";
        }

        // Using TNT to blow up the boulder
        if (value.equalsIgnoreCase("tnt") && target.equalsIgnoreCase("boulder")) {
            Container boulder = (Container) currentRoom.getFeatureByName("boulder");
            Exit hiddenExit = currentRoom.getExit("north");

            if (boulder != null) {
                boulder.setHidden(true); // Hide the boulder
                if (hiddenExit != null) {
                    hiddenExit.setHidden(false); // Reveal the hidden exit
                }
                player.getEquipment().remove(equipment); // Remove the TNT from the player's inventory
                player.updateScore(25); // Add 25 points for using equipment
                return "You blow up the boulder, revealing a new path!";
            }
            return "The boulder does not exist.";
        }

        // Using a pearl to escape and win the game
        if (value.equalsIgnoreCase("pearl") && target.equalsIgnoreCase("portal")) {
            Exit hiddenExit = currentRoom.getExit("e18");
            if (hiddenExit != null && hiddenExit.isHidden()) {
                hiddenExit.setHidden(false);
                player.getEquipment().remove(equipment); // Remove the pearl from the player's inventory
                currentRoom.setId("r11"); // Set the room ID to r11
                return "You use the pearl to activate the portal. You escape and win the game!";
            }
            return "There is no portal here.";
        }

        // Using a pick to open the chest and reveal the iron
        if (value.equalsIgnoreCase("pick") && target.equalsIgnoreCase("chest")) {
            Container chest = (Container) currentRoom.getFeatureByName("chest");
            if (chest != null && !chest.isHidden()) {
                chest.setHidden(true); // Hide the chest after opening
                Item iron = currentRoom.getItemByName("iron");
                if (iron != null) {
                    iron.setHidden(false); // Reveal the iron
                    player.removeEquipment(equipment); // Remove the pick from the player's inventory
                    player.updateScore(25); // Add 25 points for using equipment
                    return "You pick open the chest.";
                }
                return "The chest is empty.";
            }
            return "There is no chest to open here.";
        }

        return "Invalid use target.";
    }
}