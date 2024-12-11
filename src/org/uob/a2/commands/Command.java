package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents an abstract command that can be executed within the game.
 * 
 * <p>
 * Subclasses should define specific types of commands and their behavior by 
 * implementing the {@link #execute(GameState)} method.
 * </p>
 */
public abstract class Command {
    
    public CommandType commandType;
    //The type of the command (e.g., MOVE, GET, DROP).

    public String value;
    //An optional value associated with the command, such as a target item or direction.
    
    public Command() {
        this.commandType = null; // Default type is undefined
        this.value = "";         // Default value is empty
    }

    public Command(CommandType commandType, String value) {
        this.commandType = commandType;
        this.value = value;
    }

    public abstract String execute(GameState gameState);
    //Executes the command using the provided game state.
    //Parameters: gameState - the current state of the game
    //Returns: a string describing the outcome of the command execution

}