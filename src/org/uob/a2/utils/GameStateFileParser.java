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
        Room currentRoom = null;
        HashMap<String, Room> roomMap = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length < 2) continue;

            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "player" -> player = new Player(value);

                case "map" -> {
                    // Set the map's current room, deferred until rooms are processed
                    map.setCurrentRoom(value);
                }

                case "room" -> {
                    String[] roomParts = value.split(",", 4);
                    String roomId = roomParts[0].trim();
                    String roomName = roomParts[1].trim();
                    String roomDescription = roomParts[2].trim();
                    boolean roomHidden = Boolean.parseBoolean(roomParts[3].trim());
                    Room room = new Room(roomId, roomName, roomDescription, roomHidden);
                    roomMap.put(roomId, room);
                }

                case "item" -> {
                    String[] itemParts = value.split(",", 4);
                    String itemId = itemParts[0].trim();
                    String itemName = itemParts[1].trim();
                    String itemDescription = itemParts[2].trim();
                    boolean itemHidden = Boolean.parseBoolean(itemParts[3].trim());
                    Item item = new Item(itemId, itemName, itemDescription, itemHidden);
                    if (currentRoom != null) {
                        currentRoom.addItem(item);
                    }
                }

                case "equipment" -> {
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
                    if (currentRoom != null) {
                        currentRoom.addEquipment(equipment);
                    }
                }

                case "container" -> {
                    String[] containerParts = value.split(",", 4);
                    String containerId = containerParts[0].trim();
                    String containerName = containerParts[1].trim();
                    String containerDescription = containerParts[2].trim();
                    boolean containerHidden = Boolean.parseBoolean(containerParts[3].trim());
                    Container container = new Container(containerId, containerName, containerDescription, containerHidden);
                    if (currentRoom != null) {
                        currentRoom.addFeature(container);
                    }
                }

                case "exit" -> {
                    String[] exitParts = value.split(",", 5);
                    String exitId = exitParts[0].trim();
                    String exitName = exitParts[1].trim();
                    String exitDescription = exitParts[2].trim();
                    String nextRoom = exitParts[3].trim();
                    boolean exitHidden = Boolean.parseBoolean(exitParts[4].trim());
                    Exit exit = new Exit(exitId, exitName, exitDescription, nextRoom, exitHidden);
                    if (currentRoom != null) {
                        currentRoom.addExit(exit);
                    }
                }
            }
        }

        // Add all rooms to the map
        for (Room room : roomMap.values()) {
            map.addRoom(room);
        }

        // Set the current room
        if (map.getCurrentRoom() == null && !roomMap.isEmpty()) {
            map.setCurrentRoom(roomMap.keySet().iterator().next());
        }

        reader.close();
        return new GameState(map, player);
    }
   
}