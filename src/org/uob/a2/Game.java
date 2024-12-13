package org.uob.a2;

import java.util.Scanner;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

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
            System.out.println("Loading game...");
            gameState = GameStateFileParser.parse("data/game.txt");
            scanner = new Scanner(System.in);
            parser = new Parser();
            tokeniser = new Tokeniser();
            System.out.println("Game loaded successfully.");
        } catch (Exception e) {
            System.err.println("Failed to load game state: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void start() {
        boolean running = true;
        System.out.println(formatRoomDescription(gameState.getMap().getCurrentRoom()));
        while (running) {
            try {
                // Check the game-ending condition
                Room currentRoom = gameState.getMap().getCurrentRoom();
                if (currentRoom.getId().equals("r11")) {
                    int score = gameState.getPlayer().getScore();
                    if (score > 20) {
                        System.out.println("Congratulations! You have successfully completed the game with a score of " + score + "!");
                        running = false;
                    } else {
                        System.out.println("You died... Game Over");
                        running = false;
                    }
                    continue;
                }

                System.out.print(">> ");
                String input = scanner.nextLine().trim();
                tokeniser.tokenise(input);
                var tokens = tokeniser.getTokens();
                Command command = parser.parse(tokens);
                if (command instanceof Quit) {
                    running = false;
                    System.out.println("Goodbye...");
                } else {
                    turn(command);
                }

                // Decrease score by 1 each turn
                gameState.getPlayer().updateScore(-1);

            } catch (CommandErrorException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public static void turn(Command command) {
        String result = command.execute(gameState);
        if (result != null && !result.isEmpty()) {
            System.out.println(result);
        }
    }

    private static String formatRoomDescription(Room room) {
        StringBuilder sb = new StringBuilder();
        sb.append(room.getDescription()).append("\nYou see:");
        for (GameObject obj : room.getVisibleObjects()) {
            sb.append("\n").append(obj.getDescription());
        }
        return sb.toString();
    }
}