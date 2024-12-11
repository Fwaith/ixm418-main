package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

    public Quit() {
        this.commandType = CommandType.QUIT;
        this.value = null; // Quit command does not require a value
    }
    //Creates a new Quit command.
    
    public String execute(GameState gameState) {
        StringBuilder result = new StringBuilder("Game over:\n");

        // Append player status
        result.append("Player: ").append(gameState.getPlayer().getName()).append("\n");
        result.append("Inventory:\n");
        if (gameState.getPlayer().getInventory().isEmpty()) {
            result.append(" - No items\n");
        } 
        else {
            for (var item : gameState.getPlayer().getInventory()) {
                result.append(" - ").append(item.getDescription().toLowerCase()).append("\n");
            }
        }

        // Return the game-over message
        return result.toString();
    }
    //Executes the quit command. This implementation returns a game-over message along with the player's current status.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game
    //Returns:a string containing the game-over message and the player's status

}