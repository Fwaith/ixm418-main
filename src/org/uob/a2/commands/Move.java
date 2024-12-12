package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 */
public class Move extends Command {

    public Move(String direction) {
        this.commandType = CommandType.MOVE;
        this.value = direction; // The direction to move (e.g., "north")
    }

    @Override
    public String toString() {
        return "Move command: move " + value;
    }

    @Override
    public String execute(GameState gameState) {
        Room currentRoom = gameState.getMap().getCurrentRoom();

        // Attempt to retrieve the exit by the given direction name
        Exit exit = currentRoom.getExit(value);

        if (exit != null && !exit.isHidden()) {
            // Update the player's current room
            gameState.getMap().setCurrentRoom(exit.getNextRoom());
            return "Moving towards " + value + "\n";
        }

        return "No exit found in that direction.";
    }
}