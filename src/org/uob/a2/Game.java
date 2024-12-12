package org.uob.a2;

import java.util.Scanner;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 */
public class Game {

    private static GameState gameState;
    private static Scanner scanner;
    private static Parser parser;
    private static Tokeniser tokeniser;

    public static void main(String[] args) {
        setup();
        start();
    }

    /**
     * Sets up the game by initializing the game state, scanner, parser, and tokeniser.
     */
    public static void setup() {
        try {
            System.out.println("Loading game...");

            // Load the game state from a file
            gameState = GameStateFileParser.parse("data/game.txt");

            // Initialize input handling components
            scanner = new Scanner(System.in);
            parser = new Parser();
            tokeniser = new Tokeniser();

            System.out.println("Game loaded successfully.");
        } catch (Exception e) {
            System.err.println("Failed to load game state: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Starts the game loop. Continuously reads input, tokenises it, and processes commands until the user decides to quit.
     */
    public static void start() {
        boolean running = true;

        // Show the initial room description
        System.out.println(formatRoomDescription(gameState.getMap().getCurrentRoom()));

        while (running) {
            try {
                // Prompt for input
                System.out.print(">> ");

                String input = scanner.nextLine().trim();

                // Tokenise and parse input
                tokeniser.tokenise(input);
                var tokens = tokeniser.getTokens();

                Command command = parser.parse(tokens);

                // Execute the command
                if (command instanceof Quit) {
                    running = false;
                    System.out.println("Goodbye...");
                } else {
                    turn(command);
                }
            } catch (CommandErrorException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Processes a single game turn based on the provided command.
     *
     * @param command the command to execute during the turn
     */
    public static void turn(Command command) {
        String result = command.execute(gameState);
        if (result != null && !result.isEmpty()) {
            System.out.println(result);
        }
    }

    /**
     * Formats the room description to match the desired output format.
     *
     * @param room the current room
     * @return the formatted room description
     */
    private static String formatRoomDescription(Room room) {
        StringBuilder sb = new StringBuilder();
        sb.append(room.getDescription()).append("\nYou see:");

        for (GameObject obj : room.getVisibleObjects()) {
            sb.append("\n").append(obj.getName());
        }

        return sb.toString();
    }
}