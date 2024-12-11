package org.uob.a2.parser;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {

    //public Parser()
    
    public Command parse(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.isEmpty()) {
            throw new CommandErrorException("No tokens to parse.");
        }

        Token firstToken = tokens.get(0);
        switch (firstToken.getTokenType()) {
            case MOVE -> {
                if (tokens.size() > 1 && tokens.get(1).getTokenType() == TokenType.VAR) {
                    return new Move(tokens.get(1).getValue());
                }
                throw new CommandErrorException("MOVE command requires a direction.");
            }
            case GET -> {
                if (tokens.size() > 1 && tokens.get(1).getTokenType() == TokenType.VAR) {
                    return new Get(tokens.get(1).getValue());
                }
                throw new CommandErrorException("GET command requires an item.");
            }
            case DROP -> {
                if (tokens.size() > 1 && tokens.get(1).getTokenType() == TokenType.VAR) {
                    return new Drop(tokens.get(1).getValue());
                }
                throw new CommandErrorException("DROP command requires an item.");
            }
            case LOOK -> {
                String target = tokens.size() > 1 && tokens.get(1).getTokenType() == TokenType.VAR
                        ? tokens.get(1).getValue()
                        : "room";
                return new Look(target);
            }
            case STATUS -> {
                String topic = tokens.size() > 1 && tokens.get(1).getTokenType() == TokenType.VAR
                        ? tokens.get(1).getValue()
                        : "player";
                return new Status(topic);
            }
            case HELP -> {
                String topic = tokens.size() > 1 && tokens.get(1).getTokenType() == TokenType.VAR
                        ? tokens.get(1).getValue()
                        : null;
                return new Help(topic);
            }
            case USE -> {
                if (tokens.size() > 3
                        && tokens.get(1).getTokenType() == TokenType.VAR
                        && tokens.get(2).getTokenType() == TokenType.PREPOSITION
                        && tokens.get(3).getTokenType() == TokenType.VAR) {
                    return new Use(tokens.get(1).getValue(), tokens.get(3).getValue());
                }
                throw new CommandErrorException("USE command requires 'use [item] on [target]'.");
            }
            case QUIT -> {
                return new Quit();
            }
            default -> throw new CommandErrorException("Invalid command.");
        }
    }
    //Parses a list of tokens into a Command object.
    //Parameters: tokens - the list of tokens to parse
    //Returns: a Command object representing the parsed command
    //Throws: CommandErrorException - if the command cannot be parsed or is invalid

}