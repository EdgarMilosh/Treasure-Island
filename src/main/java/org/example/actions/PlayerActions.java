package org.example.actions;

import org.example.events.ObstacleEvent;
import org.example.game.GameContext;
import org.example.model.Player;
import org.example.ui.Ui;
import org.example.world.Island;
import org.example.world.Sector;

public class PlayerActions {

    private static final int STEP = 1;
    private static final int HEAL_AMOUNT_FOR_SUPPLY = 50;

    public static void move(Player player, String direction, GameContext context) {
        Island island = context.getIsland();
        Ui ui = context.getUi();

        int x = player.getCurrentSector().getXCoordinate();
        int y = player.getCurrentSector().getYCoordinate();
        int newX = x, newY = y;

        switch (direction.toLowerCase()) {
            case "n" -> newX = x - STEP;
            case "s" -> newX = x + STEP;
            case "w" -> newY = y - STEP;
            case "e" -> newY = y + STEP;
            default -> {
                ui.showMessage("Invalid direction!");
                return;
            }
        }

        if (newX < 0 || newX >= island.getWidth() || newY < 0 || newY >= island.getHeight()) {
            ui.showMessage("Cannot move outside the island!");
            return;
        }

        Sector next = island.getSectors()[newX][newY];

        if (next.getEvent() instanceof ObstacleEvent obstacle) {
            obstacle.execute(context);
            return;
        }

        player.setCurrentSector(next);
        next.triggerEvent(context);
    }

    public static void heal(Player player, int amount) {
        player.setCurrentHealth(Math.min(player.getCurrentHealth() + amount, player.getMaxHealth()));
    }

    public static boolean useSupplyToHeal(Player player) {
        if (player.getSupplies() > 0) {
            heal(player, HEAL_AMOUNT_FOR_SUPPLY);
            player.setSupplies(player.getSupplies() - 1);
            return true;
        } else {
            return false;
        }
    }
}
