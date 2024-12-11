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
    //Creates a new Use command for the specified equipment and target.
    //Parameters:
    //equipmentName - the name of the equipment to use
    //target - the name of the target on which the equipment will be used
    
    @Override
    public String toString() {
        return "Use command: use " + value + " on " + target;
    }
    //Returns a string representation of the use command, including its type, equipment, and target.
    //Overrides: toString in class Object
    //Returns: a string describing the use command
    
    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Equipment equipment = player.getEquipment(value);

        if (equipment == null) {
            return "You do not have " + value;
        }

        UseInformation useInfo = equipment.getUseInformation();
        if (useInfo.isUsed()) {
            return "You have already used " + value;
        }

        // Retrieve the target object by feature name
        GameObject targetObject = gameState.getMap().getCurrentRoom().getFeatureByName(target);

        if (targetObject instanceof Container container) {
            if (useInfo.getTarget().equals(container.getId())) {
                useInfo.setUsed(true);
                // Reveal all items inside the container
                for (GameObject obj : container.open()) {
                    obj.setHidden(false);
                }
                return useInfo.getMessage();
            }
        }

        return "Invalid use target";
    }
    //Executes the use command. Checks if the player has the specified equipment and whether the equipment can interact with the target. If valid, the equipment is used on the target.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game
    //Returns: a string describing the result of the command execution
    
}