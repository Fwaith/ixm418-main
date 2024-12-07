package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {
    private String name; // Player's name
    private List<Item> inventory = new ArrayList<>(); // Items carried by the player
    private List<Equipment> equipment = new ArrayList<>(); // Equipped items

    // Constructor to initialize the player with a name
    public Player(String name) {
        this.name = name;
    }

    /**
     * Adds an item to the player's inventory.
     * @param item The item to add.
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Adds a piece of equipment to the player's equipment list.
     * @param eq The equipment to add.
     */
    public void addEquipment(Equipment eq) {
        equipment.add(eq);
    }

    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
