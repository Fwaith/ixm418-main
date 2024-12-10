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

    ArrayList<Room> rooms;         // Stores rooms in a list for iteration
    HashMap<String, Room> roomMap; // Maps room IDs to Room objects for quick lookup
    Room currentRoom;              // The room the player is currently in

    public Map() {
        this.rooms = new ArrayList<>();
        this.roomMap = new HashMap<>();
        this.currentRoom = null;
    }
    //Constructs a new, empty Map.
    
    public Room getCurrentRoom() {
        return currentRoom;
    }
    //Retrieves the current room the player is in.
    //Returns: the current room
    
    public void addRoom(Room room) {
        // Add the room to the list and map
        rooms.add(room);
        roomMap.put(room.getId(), room);

        // Set the first room added as the current room if no current room is set
        if (currentRoom == null) {
            currentRoom = room;
        }
    }
    //Adds a room to the map.
    //Parameters: room - the room to add to the map
    
    public void setCurrentRoom(String roomId) {
        // Retrieve the room from the map and set it as the current room
        if (roomMap.containsKey(roomId)) {
            currentRoom = roomMap.get(roomId);
        } 
        else {
            throw new IllegalArgumentException("Room ID not found in the map: " + roomId);
        }
    }
    //Sets the current room based on the provided room ID.
    //Parameters: roomId - the ID of the room to set as the current room

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