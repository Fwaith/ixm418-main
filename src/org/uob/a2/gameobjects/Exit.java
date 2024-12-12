package org.uob.a2.gameobjects;

/**
 * Represents an exit in the game, allowing the player to move from one room to another.
 * 
 * <p>
 * Exits have a destination (next room), a description, and can be hidden or visible based on game logic.
 * </p>
 */
public class Exit extends GameObject {
    
    private String nextRoom;

    public Exit(String id, String name, String description, String nextRoom, boolean hidden) {
        super(id, name, description, hidden);
        this.nextRoom = nextRoom;
    }

    /**
     * Retrieves the ID of the next room this exit leads to.
     * 
     * @return the next room's ID
     */
    public String getNextRoom() {
        return nextRoom;
    }

    /**
     * Sets the visibility of this exit.
     * 
     * @param hidden true to hide the exit, false to make it visible
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Checks whether the exit is currently hidden.
     * 
     * @return true if the exit is hidden, false otherwise
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Returns a string representation of the exit, including its attributes and next room information.
     *
     * @return a string describing the exit
     */
    @Override
    public String toString() {
        return super.toString() + ", nextRoom=" + nextRoom;
    }
}