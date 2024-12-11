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
        "get", "GET Command: Use the 'get' command followed by an item or equipment name to pick it up.",
        "drop", "DROP Command: Use the 'drop' command followed by an item or equipment name to leave it in the current room.",
        "look", "LOOK Command: Use the 'look' command to examine your surroundings or specific objects.",
        "status", "STATUS Command: Use the 'status' command to view your inventory or player status."
    );

    public Help(String topic) {
        this.commandType = CommandType.HELP;
        this.value = topic;
    }
    //Creates a new Help command for the specified topic.
    //Parameters: topic - the topic for which help is requested (optional, can be general help)

    @Override
    public String toString() {
        return "HELP Command: " + (value != null ? "help " + value : "general help");
    }
    //Returns a string representation of the help command, including its type and topic.
    //Overrides: toString in class Object
    //Returns: a string describing the help command

    public String execute(GameState gameState) {
        if (value == null || value.isEmpty()) {
            return """
                Welcome to the game!
                Available commands:
                - MOVE: Navigate to another room.
                - GET: Pick up an item or equipment.
                - DROP: Leave an item or equipment in the current room.
                - LOOK: Examine your surroundings or specific objects.
                - STATUS: View your inventory or player status.
                - HELP: Get help on a specific topic or general help.
                """;
        }

        return HELP_TOPICS.getOrDefault(value.toLowerCase(), "No help available for the topic: " + value);
    }
    //Executes the help command. Provides detailed help information based on the specified topic or general game help if no specific topic is provided.
    //Specified by: execute in class Command
    //Parameters: gameState - the current state of the game (not used for help commands)
    //Returns: a string containing help information for the player

}