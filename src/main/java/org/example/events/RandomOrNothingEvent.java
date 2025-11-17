package org.example.events;

import org.example.game.GameContext;
import org.example.model.Player;
import org.example.ui.Ui;
import org.example.world.Island;
import org.example.world.Sector;

import java.util.Random;

import static org.example.actions.PlayerActions.heal;

public class RandomOrNothingEvent implements SectorEvent {

    private static final int CHANCE_LIGHTNING = 0;
    private static final int CHANCE_HEAL_FROG = 1;
    private static final int CHANCE_PORTAL = 2;
    private static final int LIGHTNING_DAMAGE = 30;
    private static final int FROG_HEAL = 25;
    private static final int RANDOM_EVENT_CHANCE = 25;

    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        Island island = context.getIsland();
        Ui ui = context.getUi();
        Random rand = new Random();

        int generatedValue = rand.nextInt(RANDOM_EVENT_CHANCE);

        if (generatedValue == CHANCE_LIGHTNING) {
            triggerLightning(player, ui);
        } else if (generatedValue == CHANCE_HEAL_FROG) {
            triggerFrogHealing(player, ui);
        } else if (generatedValue == CHANCE_PORTAL) {
            triggerPortal(player, island, ui, rand, context);
        } else {
            ui.showMessage("Nothing here...");
        }
    }

    private void triggerLightning(Player player, Ui ui) {
        player.takeDamage(LIGHTNING_DAMAGE);
        ui.showMessage("You were hit by a lightning!\nHP LOST - " + LIGHTNING_DAMAGE);
    }

    private void triggerFrogHealing(Player player, Ui ui) {
        heal(player, FROG_HEAL);
        ui.showMessage("You came across a magical toad, it healed you by " + FROG_HEAL + " HP!");
    }

    private void triggerPortal(Player player, Island island, Ui ui, Random rand,
                               GameContext context) {
        ui.showMessage("A mysterious portal appears! You are teleported elsewhere...");
        Sector randomSector = getRandomSafeSector(island, rand);

        if (randomSector != null) {
            player.setCurrentSector(randomSector);
            ui.showMessage("You have been teleported to sector (" +
                    randomSector.getXCoordinate() + "," +
                    randomSector.getYCoordinate() + ")!");
            randomSector.triggerEvent(context);
        } else {
            ui.showMessage("Teleportation failed — no safe sectors found!");
        }
    }

    private Sector getRandomSafeSector(Island island, Random rand) {
        for (int i = 0; i < RANDOM_EVENT_CHANCE; i++) {
            int x = rand.nextInt(island.getWidth());
            int y = rand.nextInt(island.getHeight());
            Sector candidate = island.getSectors()[x][y];
            if (!(candidate.getEvent() instanceof ObstacleEvent)) {
                return candidate;
            }
        }
        return null;
    }
}
