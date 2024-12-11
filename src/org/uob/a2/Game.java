package org.uob.a2;

import java.util.Scanner;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {

    private static GameState gameState;
    private static Scanner scanner;
    private static Parser parser;
    private static Tokeniser tokeniser;

    /**
     * The main entry point for the game. Sets up the game and starts it.
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        setup();
        start();
    }

    /**
     * Sets up the game by initializing the game state, scanner, parser, and tokeniser.
     * Loads the game data from a file and prepares the initial state.
     */
    public static void setup() {
        try {
            System.out.println("Welcome to the Game!");
            System.out.println("Loading game state...");
            
            // Load the game state from a file
            gameState = GameStateFileParser.parse("data/game_state.txt");

            // Initialize input handling components
            scanner = new Scanner(System.in);
            parser = new Parser();
            tokeniser = new Tokeniser();

            System.out.println("Game setup complete. Type 'help' for a list of commands.");
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

        while (running) {
            try {
                // Display the current room and prompt for input
                System.out.println("\n" + gameState.getMap().getCurrentRoom());
                System.out.print("> ");

                // Read and tokenise input
                String input = scanner.nextLine();
                tokeniser.tokenise(input);
                var tokens = tokeniser.getTokens();

                // Parse tokens into a command
                Command command = parser.parse(tokens);

                // Execute the command and process the turn
                if (command instanceof Quit) {
                    running = false; // End the game loop if 'quit' is executed
                } else {
                    turn(command);
                }
            } catch (CommandErrorException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        System.out.println("Thank you for playing. Goodbye!");
    }

    /**
     * Processes a single game turn based on the provided command.
     *
     * @param command the command to execute during the turn
     */
    public static void turn(Command command) {
        String result = command.execute(gameState);
        System.out.println(result);
    }

}