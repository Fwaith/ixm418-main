package org.uob.a2.commands;

import java.lang.reflect.Constructor;

import org.uob.a2.gameobjects.*;
import java.util.ArrayList;

public class Combine extends Command {
    private String item1;
    private String item2;

    public Combine(String item1, String item2) {
        this.commandType = CommandType.COMBINE;
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public String toString() {
        return "Combine command: combine " + item1 + " and " + item2;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Item firstItem = player.getItem(item1);
        Item secondItem = player.getItem(item2);

        if (firstItem == null || secondItem == null) {
            return "You don't have both items to combine.";
        }

        // Combination logic: Iron + Sticks -> Pickaxe
        if ((item1.equals("iron") && item2.equals("sticks")) || (item1.equals("sticks") && item2.equals("iron"))) {
            Equipment pickaxe = new Equipment("k2", "pickaxe", "A trusty pickaxe.", false, 
                    new UseInformation(false, "reveal", "r2", "e3", "You mine away at the wall revealing a new path."));
            player.addEquipment(pickaxe);
            player.getInventory().remove(firstItem);
            player.getInventory().remove(secondItem);
            gameState.incrementScore(5);
            return "You combine the iron and sticks to create a Pickaxe!";
        }

        // Combination logic: Gunpowder + Sand -> TNT
        if ((item1.equals("gunpowder") && item2.equals("sand")) || (item1.equals("sand") && item2.equals("gunpowder"))) {
            Equipment tnt = new Equipment("k3", "tnt", "Thingy that goes boom.", false, 
                    new UseInformation(false, "use", "r5", "e9", "You use the TNT. It blows up the boulder."));
            player.addEquipment(tnt);
            player.getInventory().remove(firstItem);
            player.getInventory().remove(secondItem);
            gameState.incrementScore(5);
            return "You combine the gunpowder and sand to create TNT!";
        }

        return "These items cannot be combined.";
    }
}