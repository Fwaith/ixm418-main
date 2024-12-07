package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {
    private List<Item> items = new ArrayList<>(); // Items in the room
    private List<Equipment> equipment = new ArrayList<>(); // Equipment in the room
    private List<Feature> features = new ArrayList<>(); // Features in the room
    private List<Exit> exits = new ArrayList<>(); // Exits to other rooms

    // Constructor to initialize the room
    public Room(String id, String name, String description) {
        super(id, name, description, false);
    }

    /**
     * Adds an item to the room.
     * @param item The item to add.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Adds a feature to the room.
     * @param feature The feature to add.
     */
    public void addFeature(Feature feature) {
        features.add(feature);
    }

    /**
     * Adds an exit to the room.
     * @param exit The exit to add.
     */
    public void addExit(Exit exit) {
        exits.add(exit);
    }
    
    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}
