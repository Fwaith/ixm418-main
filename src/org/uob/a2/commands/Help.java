package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;

import java.util.Map;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {

    private static final Map<String, String> HELP_TOPICS = Map.of(
        "move", "MOVE Command: Use the 'move' command followed by a direction to navigate to another room.",
        "look", "LOOK Command: Use the 'look' command to examine your surroundings or specific objects.",
        "get", "GET Command: Use the 'get' command followed by an item or equipment name to pick it up.",
        "drop", "DROP Command: Use the 'drop' command followed by an item or equipment name to leave it in the current room.",
        "use", "USE Command: Use an item or equipment on its own or with a feature or item (e.g., 'use key on chest').",
        "combine", "COMBINE Command: Combine two items into a new item or equipment.",
        "status", "STATUS Command: Check your inventory, player status, map, or score.",
        "help", "HELP Command: Use the 'help' command to view all commands or get detailed help on a specific topic.",
        "quit", "QUIT Command: Exit the game."
    );

    public Help(String topic) {
        this.commandType = CommandType.HELP;
        this.value = topic;
    }

    @Override
    public String toString() {
        return "HELP Command: " + (value != null ? "help " + value : "help null");
    }

    @Override
    public String execute(GameState gameState) {
        if (value == null || value.isEmpty()) {
            return """
                Welcome to the dungeon!
                Available commands:
                - MOVE: Navigate to another room.
                - LOOK: Examine your surroundings or specific objects.
                - GET: Pick up an item or equipment.
                - DROP: Leave an item or equipment in the current room.
                - USE: Use an item or equipment.
                - COMBINE: Combine two items into a new item or equipment.
                - STATUS: View inventory, player status, map, or score.
                - HELP: Get help on a specific topic or general help.
                - QUIT: Exit the game.
                """;
        }
        return HELP_TOPICS.getOrDefault(value.toLowerCase(), "No help available for the topic: " + value);
    }
    //Executes the help command. Provides detailed help information based on the specified topic or general game help if no specific topic is provided.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game (not used for help commands)
    //Returns: a string containing help information for the player

}