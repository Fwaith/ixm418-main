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
    private String name; // The player's name
    private ArrayList<Item> inventory; // The player's inventory of items
    private ArrayList<Equipment> equipment; // The player's collection of equipment
    private int score; // The player's current score

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.score = 0; // Initialize score to 0
    }
    // Constructs a new Player with the specified name and initializes their inventory, equipment, and score.

    public Player() {
        this.name = "";
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.score = 0; // Initialize score to 0
    }
    // Default constructor for Player.

    public String getName() {
        return name;
    }
    // Retrieves the name of the player.
    // Returns: the player's name

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    // Retrieves the player's inventory.
    // Returns: an ArrayList of Item objects in the player's inventory

    public boolean hasItem(String itemName) {
        return getItem(itemName) != null;
    }
    // Checks if the player has an item with the specified name in their inventory.
    // Parameters: itemName - the name of the item to check
    // Returns: true if the item is found, false otherwise

    public Item getItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Item not found
    }
    // Retrieves an item from the player's inventory by its name.
    // Parameters: itemName - the name of the item to retrieve
    // Returns: the Item object if found, or null if not found

    public void addItem(Item item) {
        inventory.add(item);
    }
    // Adds an item to the player's inventory.
    // Parameters: item - the Item to add

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }
    // Retrieves the player's equipment.
    // Returns: an ArrayList of Equipment objects in the player's possession

    public boolean hasEquipment(String equipmentName) {
        return getEquipment(equipmentName) != null;
    }
    // Checks if the player has equipment with the specified name.
    // Parameters: equipmentName - the name of the equipment to check
    // Returns: true if the equipment is found, false otherwise

    public Equipment getEquipment(String equipmentName) {
        for (Equipment eq : equipment) {
            if (eq.getName().equalsIgnoreCase(equipmentName)) {
                return eq;
            }
        }
        return null; // Equipment not found
    }
    // Retrieves a piece of equipment by its name.
    // Parameters: equipmentName - the name of the equipment to retrieve
    // Returns: the Equipment object if found, or null if not found

    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    public void removeEquipment(Equipment eq) {
        this.equipment.remove(eq);
    }
    // Adds a piece of equipment to the player's collection.
    // Parameters: equipment - the Equipment to add

    /**
     * Updates the player's score by adding the given value.
     *
     * @param points the number of points to add (can be negative to deduct points)
     */
    public void updateScore(int points) {
        this.score += points;
    }

    /**
     * Retrieves the player's current score.
     *
     * @return the player's current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, equipment, and score.
     *
     * @return a string describing the player, their inventory, equipment, and score
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nScore: " + this.score + "\nInventory:\n");
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