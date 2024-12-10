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

    ArrayList<Item> items;           // List of items in the room
    ArrayList<Equipment> equipment; // List of equipment in the room
    ArrayList<Feature> features;    // List of features in the room
    ArrayList<Exit> exits;          // List of exits in the room
    
    public Room(String id, String name, String description, boolean hidden) {
        super(id, name, description, hidden);
        items = new ArrayList<>();
        equipment = new ArrayList<>();
        features = new ArrayList<>();
        exits = new ArrayList<>();
    }

    // Default constructor
    public Room() {
        super(); // Calls the default constructor of GameObject
        this.items = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.features = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    public ArrayList<Exit> getExits() {
        return exits;
    }
    //Retrieves the exits of the room.
    //Returns: a list of exits in the room

    public void addExit(Exit exit) {
        exits.add(exit);
    }
    //Adds an exit to the room.
    //Parameters: exit - the exit to add

    public ArrayList<Item> getItems() {
        return items;
    }
    //Retrieves the items in the room.
    //Returns: a list of items in the room

    public Item getItem(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null; // Item not found
    }
    //Retrieves an item by its ID.
    //Parameters: id - the ID of the item
    //Returns: the item if found, otherwise null

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // Item not found
    }
    //Retrieves an item by its name.
    //Parameters: name - the name of the item
    //Returns: the item if found, otherwise null

    public Feature getFeatureByName(String name) {
        for (Feature feature : features) {
            if (feature.getName().equalsIgnoreCase(name)) {
                return feature;
            }
        }
        return null; // Feature not found
    }
    //Retrieves a feature by its name.
    //Parameters: name - the name of the feature
    //Returns: the feature if found, otherwise null

    public ArrayList<Equipment> getEquipments() {
        return equipment;
    }
    //Retrieves the equipment in the room.
    //Returns: a list of equipment in the room

    public Equipment getEquipmentByName(String name) {
        for (Equipment eq : equipment) {
            if (eq.getName().equalsIgnoreCase(name)) {
                return eq;
            }
        }
        return null; // Equipment not found
    }
    //Retrieves equipment by its name.
    //Parameters: name - the name of the equipment
    //Returns: the equipment if found, otherwise null

    public Equipment getEquipment(String id) {
        for (Equipment eq : equipment) {
            if (eq.getId().equals(id)) {
                return eq;
            }
        }
        return null; // Equipment not found
    }
    //Retrieves equipment by its ID.
    //Parameters: id - the ID of the equipment
    //Returns: the equipment if found, otherwise null

    public Exit getExit(String id) {
        for (Exit exit : exits) {
            if (exit.getId().equals(id)) {
                return exit;
            }
        }
        return null; // Exit not found
    }
    //Retrieves an exit by its ID.
    //Parameters: id - the ID of the exit
    //Returns: the exit if found, otherwise null

    public void addEquipment(Equipment eq) {
        equipment.add(eq);
    }
    //Adds equipment to the room.
    //Parameters: equipment - the equipment to add

    public Feature getFeature(String id) {
        for (Feature feature : features) {
            if (feature.getId().equals(id)) {
                return feature;
            }
        }
        return null; // Feature not found
    }
    //Retrieves a feature by its id.
    //Parameters: id - the id of the feature
    //Returns: the feature if found, otherwise null

    public void addItem(Item item) {
        items.add(item);
    }
    //Adds an item to the room.
    //Parameters: item - the item to add

    public ArrayList<Feature> getFeatures() {
        return features;
    }
    //Retrieves the features in the room.
    //Returns: a list of features in the room

    public ArrayList<GameObject> getAll() {
        ArrayList<GameObject> allObjects = new ArrayList<>();
        allObjects.addAll(items);
        allObjects.addAll(equipment);
        allObjects.addAll(features);
        allObjects.addAll(exits);
        return allObjects;
    }
    //Retrieves all game objects in the room.
    //Returns: a list of all game objects in the room

    public void addFeature(Feature feature) {
        features.add(feature);
    }
    //Adds a feature to the room.
    //Parameters: feature - the feature to add

    public boolean hasItem(String itemName) {
        return getItemByName(itemName) != null;
    }
    //Checks if the room contains an item with the specified name.
    //Parameters: itemName - the name of the item
    //Returns: true if the item is found, false otherwise

    public boolean hasEquipment(String name) {
        return getEquipmentByName(name) != null;
    }
    //Checks if the room contains equipment with the specified name.
    //Parameters:name - the name of the equipment
    //Returns:true if the equipment is found, false otherwise

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
