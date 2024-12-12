package org.uob.a2;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.GameStateFileParser;

import java.util.Scanner;

public class Game {

    private static GameState gameState;
    private static Scanner scanner;
    private static Parser parser;
    private static Tokeniser tokeniser;

    public static void main(String[] args) {
        setup();
        start();
    }

    public static void setup() {
        try {
            System.out.println("Welcome to the dungeon!");
            System.out.println("Loading game state...");

            gameState = GameStateFileParser.parse("data/game.txt");
            scanner = new Scanner(System.in);
            parser = new Parser();
            tokeniser = new Tokeniser();

            System.out.println("Game setup complete. Type 'help' for a list of commands.");
        } catch (Exception e) {
            System.err.println("Failed to load game state: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void start() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n" + gameState.getMap().getCurrentRoom());
                System.out.print(">> ");

                String input = scanner.nextLine();
                tokeniser.tokenise(input);
                var tokens = tokeniser.getTokens();
                Command command = parser.parse(tokens);

                if (command instanceof Quit) {
                    running = false;
                    System.out.println("You have exited the dungeon. Goodbye!");
                } else {
                    gameState.getPlayer();
                    turn(command);
                }
            } catch (CommandErrorException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public static void turn(Command command) {
        String result = command.execute(gameState);
        gameState.decrementScore(1); // Deduct 1 point per turn
        System.out.println(result);
    }
}