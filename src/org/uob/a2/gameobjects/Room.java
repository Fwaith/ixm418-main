package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in the game, containing items, equipment, features, and exits.
 */
public class Room extends GameObject {

    private final ArrayList<Item> items;
    private final ArrayList<Equipment> equipment;
    private final ArrayList<Feature> features;
    private final ArrayList<Exit> exits;

    public Room(String id, String name, String description, boolean hidden) {
        super(id, name, description, hidden);
        items = new ArrayList<>();
        equipment = new ArrayList<>();
        features = new ArrayList<>();
        exits = new ArrayList<>();
    }

    public Room() {
        super();
        items = new ArrayList<>();
        equipment = new ArrayList<>();
        features = new ArrayList<>();
        exits = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipment;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addEquipment(Equipment eq) {
        equipment.add(eq);
    }

    public void addFeature(Feature feature) {
        features.add(feature);
    }

    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Equipment getEquipmentByName(String name) {
        for (Equipment eq : equipment) {
            if (eq.getName().equalsIgnoreCase(name)) {
                return eq;
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name) {
        for (Feature feature : features) {
            if (feature.getName().equalsIgnoreCase(name)) {
                return feature;
            }
        }
        return null;
    }

    public Exit getExit(String name) {
        for (Exit exit : exits) {
            if (exit.getName().equalsIgnoreCase(name)) {
                return exit;
            }
        }
        return null; // No matching exit found
    }    

    // New methods to fix the errors in the tests
    public boolean hasItem(String itemName) {
        return getItemByName(itemName) != null;
    }

    public boolean hasEquipment(String equipmentName) {
        return getEquipmentByName(equipmentName) != null;
    }

    public Item getItem(String id) {
        for (Item item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null; // Item not found
    }

    public Feature getFeature(String id) {
        for (Feature feature : features) {
            if (feature.getId().equalsIgnoreCase(id)) {
                return feature;
            }
        }
        return null; // Feature not found
    }

    public ArrayList<GameObject> getVisibleObjects() {
        ArrayList<GameObject> visibleObjects = new ArrayList<>();

        for (Item item : items) {
            if (!item.isHidden()) {
                visibleObjects.add(item);
            }
        }
        for (Equipment eq : equipment) {
            if (!eq.isHidden()) {
                visibleObjects.add(eq);
            }
        }
        for (Feature feature : features) {
            if (!feature.isHidden()) {
                visibleObjects.add(feature);
            }
        }
        for (Exit exit : exits) {
            if (!exit.isHidden()) {
                visibleObjects.add(exit);
            }
        }

        return visibleObjects;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[").append(id).append("] Room: ").append(name).append("\n");
        output.append("Description: ").append(description).append("\n");
        output.append("In the room you see:\n");

        for (Item item : items) {
            if (!item.isHidden()) {
                output.append("- ").append(item.getDescription()).append("\n");
            }
        }
        for (Equipment eq : equipment) {
            if (!eq.isHidden()) {
                output.append("- ").append(eq.getDescription()).append("\n");
            }
        }
        for (Feature feature : features) {
            if (!feature.isHidden()) {
                output.append("- ").append(feature.getDescription()).append("\n");
            }
        }
        for (Exit exit : exits) {
            if (!exit.isHidden()) {
                output.append("- ").append(exit.getDescription()).append("\n");
            }
        }
        return output.toString();
    }
}