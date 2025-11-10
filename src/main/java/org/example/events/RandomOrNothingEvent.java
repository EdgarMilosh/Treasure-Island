package org.example.events;

import java.util.Random;
import org.example.game.GameContext;
import org.example.model.Player;
import org.example.world.Island;
import org.example.world.Sector;


public class RandomOrNothingEvent implements SectorEvent {


  @Override
  public void execute(GameContext context) {
    Player player = context.getPlayer();
    Island island = context.getIsland();
    Random rand = new Random();
    int generatedValue = rand.nextInt(40);
    if (generatedValue == 0) {
      final int LIGHTNING_DAMAGE = 10;
      System.out.println("You were hit by a lightning!\n HP LOST - " + LIGHTNING_DAMAGE);
      player.takeDamage(LIGHTNING_DAMAGE);
    } else if (generatedValue == 1) {
      final int FROG_HEALING_HEALTH = 25;
      System.out.println("You came across a magical toad,\n it healed you by " + FROG_HEALING_HEALTH);
      player.heal(FROG_HEALING_HEALTH);
    } else if (generatedValue == 2) {
      System.out.println("A mysterious portal appears! You are teleported elsewhere...");

      Sector randomSector = getRandomSafeSector(island, rand);
      if (randomSector != null) {
        player.setCurrentSector(randomSector);
        System.out.println("You have been teleported to sector ("
            + randomSector.getXCoordinate() + ","
            + randomSector.getYCoordinate() + ")!");
        randomSector.triggerEvent(context);
      } else {
        System.out.println("Teleportation failed — no safe sectors found!");
      }

    }
    System.out.println("Nothing here...");
  }

  private Sector getRandomSafeSector(Island island, Random rand) {
    for (int i = 0; i < 50; i++) {
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
