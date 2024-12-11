package org.uob.a2.gameobjects;

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState {

    Map map;        
    Player player;
    
    public GameState(Map map, Player player) {
        this.map = map;
        this.player = player;
    }
    //Constructs a new GameState with the specified map and player.
    //Parameters: map - the map representing the game world player - the player in the game
    
    public GameState() {
        this.map = null;        
        this.player = null;
    }
    //Default constructor for GameState.
    
    public Map getMap() {
        return map;
    }
    //Retrieves the map associated with the current game state.
    //Returns: the map representing the game world
    
    public Player getPlayer() {
        return player;
    }
    //Retrieves the player associated with the current game state.
    //Returns: the player in the game
    
    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}