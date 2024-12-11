package org.uob.a2.parser;

import java.lang.reflect.Constructor;

/**
 * Represents a token in the parsing process, consisting of a {@code TokenType} and an optional value.
 * 
 * <p>
 * Tokens are used to represent the smallest units of meaning in the command input,
 * such as keywords, or variables.
 * </p>
 */
public class Token {

    private final TokenType tokenType;
    private final String value;

    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }
    //Constructs a new Token with the specified type and value.
    //Parameters:
    //tokenType - the type of the token
    //value - the value associated with the token
    
    public Token(TokenType tokenType) {
        this(tokenType, null);
    }
    //Constructs a new Token with the specified type and no associated value.
    //Parameters: tokenType - the type of the token
    
    public TokenType getTokenType() {
        return tokenType;
    }
    //Retrieves the type of this token.
    //Returns: the TokenType of this token
    
    public String getValue() {
        return value;
    }
    //Retrieves the value of this token, if applicable.
    //Returns: the value of this token, or null if no value is associated
   
}