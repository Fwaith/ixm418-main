package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {

    public Move(String direction) {
        this.commandType = CommandType.MOVE;
        this.value = direction;
    }
    //Creates a new Move command for the specified direction.
    //Parameters: direction - the direction in which the player wants to move
    
    @Override
    public String toString() {
        return "Move command: move " + value;
    }
    //Returns a string representation of the move command, including its type and direction.
    //Overrides: toString in class Object
    //Returns: a string describing the move command

    public String execute(GameState gameState) {
        Room currentRoom = gameState.getMap().getCurrentRoom();
        Exit exit = currentRoom.getExit(value);

        if (exit != null && !exit.isHidden()) {
            gameState.getMap().setCurrentRoom(exit.getNextRoom());
            return "Moving towards " + value + "\n";
        }
        return "No exit found in that direction.";
    }
    //Executes the move command. If the specified direction corresponds to an available exit in the current room, the player's location is updated to the connected room. Otherwise, no movement occurs.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game
    //Returns: a string describing the result of the move command
    
}