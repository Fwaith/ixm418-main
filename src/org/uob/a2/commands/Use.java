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

        // Using a key to open the box
        if (value.equalsIgnoreCase("key") && target.equalsIgnoreCase("box")) {
            Container box = (Container) currentRoom.getFeatureByName("box");
            if (box != null && !box.isHidden()) {
                player.getEquipment().remove(equipment); // Remove the key from the player's inventory
                box.setHidden(true); // Hide the box
                return "You use the key to open the box. The box explodes and kills you. Game over.";
            }
            return "There is no box to open here.";
        }

        // Using a pickaxe to reveal a new path
        if (value.equalsIgnoreCase("pickaxe") && target.equalsIgnoreCase("wall")) {
            Exit hiddenExit = currentRoom.getExit("e3");
            if (hiddenExit != null && hiddenExit.isHidden()) {
                hiddenExit.setHidden(false);
                return "You mine away at the wall, revealing a new path!";
            }
            return "There is nothing to mine here.";
        }

        // Using TNT to blow up a boulder
        if (value.equalsIgnoreCase("tnt") && target.equalsIgnoreCase("boulder")) {
            Exit hiddenExit = currentRoom.getExit("e9");
            if (hiddenExit != null && hiddenExit.isHidden()) {
                hiddenExit.setHidden(false);
                return "You use the TNT. It blows up the boulder, revealing a new path!";
            }
            return "There is nothing to blow up here.";
        }

        // Using a pearl to escape and win the game
        if (value.equalsIgnoreCase("pearl") && target.equalsIgnoreCase("portal")) {
            Exit hiddenExit = currentRoom.getExit("e18");
            if (hiddenExit != null && hiddenExit.isHidden()) {
                hiddenExit.setHidden(false);
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
                    return "You pick open the chest. The iron is now visible!";
                }
                return "The chest is empty.";
            }
            return "There is no chest to open here.";
        }

        return "Invalid use target.";
    }
}