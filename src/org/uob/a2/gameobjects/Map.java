package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {
    private List<Room> rooms = new ArrayList<>(); // List of all rooms in the map
    private Room currentRoom; // The room the player is currently in

    /**
     * Adds a room to the map.
     * @param room The room to add.
     */
    public void addRoom(Room room) {
        rooms.add(room);
        if (currentRoom == null) currentRoom = room; // Set the first room added as the default current room
    }

    /**
     * Gets the current room the player is in.
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room the player is in.
     * @param room The room to set as current.
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }
    
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }           

}