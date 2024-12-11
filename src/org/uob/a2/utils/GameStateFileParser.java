package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {

    public static GameState parse(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        Player player = null;
        Map map = new Map();
        HashMap<String, Room> roomMap = new HashMap<>();
        String initialRoomId = null;
        Room currentRoom = null; // Track the currently defined room to add objects to

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(":");
            if (parts.length < 2) continue;

            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "player" -> player = new Player(value);
                case "map" -> initialRoomId = value;
                case "room" -> {
                    String[] roomParts = value.split(",", 4);
                    String roomId = roomParts[0].trim();
                    String roomName = roomParts[1].trim();
                    String roomDescription = roomParts[2].trim();
                    boolean roomHidden = Boolean.parseBoolean(roomParts[3].trim());
                    Room room = new Room(roomId, roomName, roomDescription, roomHidden);
                    roomMap.put(roomId, room);
                    currentRoom = room; // Set current room reference
                }
                case "item" -> {
                    if (currentRoom != null) {
                        String[] itemParts = value.split(",", 4);
                        Item item = new Item(itemParts[0].trim(), itemParts[1].trim(), itemParts[2].trim(),
                                Boolean.parseBoolean(itemParts[3].trim()));
                        currentRoom.addItem(item);
                    }
                }
                case "equipment" -> {
                    if (currentRoom != null) {
                        String[] equipParts = value.split(",", 8);
                        String equipId = equipParts[0].trim();
                        String equipName = equipParts[1].trim();
                        String equipDescription = equipParts[2].trim();
                        boolean equipHidden = Boolean.parseBoolean(equipParts[3].trim());
                        String action = equipParts[4].trim();
                        String target = equipParts[5].trim();
                        String result = equipParts[6].trim();
                        String message = equipParts[7].trim();
                        UseInformation useInfo = new UseInformation(false, action, target, result, message);
                        Equipment equipment = new Equipment(equipId, equipName, equipDescription, equipHidden, useInfo);
                        currentRoom.addEquipment(equipment);
                    }
                }
                case "container" -> {
                    if (currentRoom != null) {
                        String[] containerParts = value.split(",", 4);
                        String containerId = containerParts[0].trim();
                        String containerName = containerParts[1].trim();
                        String containerDescription = containerParts[2].trim();
                        boolean containerHidden = Boolean.parseBoolean(containerParts[3].trim());
                        Container container = new Container(containerId, containerName, containerDescription, containerHidden);
                        currentRoom.addFeature(container);
                    }
                }
                case "exit" -> {
                    if (currentRoom != null) {
                        String[] exitParts = value.split(",", 5);
                        String exitId = exitParts[0].trim();
                        String exitName = exitParts[1].trim();
                        String exitDescription = exitParts[2].trim();
                        String nextRoomId = exitParts[3].trim();
                        boolean exitHidden = Boolean.parseBoolean(exitParts[4].trim());
                        Exit exit = new Exit(exitId, exitName, exitDescription, nextRoomId, exitHidden);
                        currentRoom.addExit(exit);
                    }
                }
            }
        }

        reader.close();

        // Add all rooms to the map
        for (Room r : roomMap.values()) {
            map.addRoom(r);
        }

        // Now set the initial current room if available
        if (initialRoomId != null && roomMap.containsKey(initialRoomId)) {
            map.setCurrentRoom(initialRoomId);
        }

        return new GameState(map, player);
    }
   
}