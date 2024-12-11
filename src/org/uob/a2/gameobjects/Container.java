package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a conatiner in the game, which is a type of feature that may contain items
 * or serve as an interactable object within a room.
 * 
 * <p>
 * Conatiner can have a name, description, and visibility state, which determines if they
 * are initially hidden or visible to the player.
 * </p>
 */
public class Container extends Feature {
    private List<GameObject> contents = new ArrayList<>(); // List of objects contained within the container

    // Constructor to initialize the container
    public Container(String id, String name, String description, boolean hidden) {
        super(id, name, description, hidden);
    }

    /**
     * Adds a game object to the container's contents.
     * @param item The game object to add.
     */
    public void addItem(GameObject item) {
        contents.add(item);
    }

    /**
     * Reveals the contents of the container by making it visible and returning the items.
     * @return A list of game objects contained in the container.
     */
    public List<GameObject> open() {
        setHidden(false); // Make the container visible
        return contents; // Return the list of contained items
    }
    /**
     * Returns a string representation of the container.
     *
     * @return a string containing the container's id, name, description, and hidden status
     */
    @Override
    public String toString() {
        return "Container {" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", hidden=" + getHidden() +
                '}';
    
    }
 
}