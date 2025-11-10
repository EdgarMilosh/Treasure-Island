package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.events.ObstacleEvent;
import org.example.game.GameContext;
import org.example.world.Island;
import org.example.world.Sector;

@Getter
@Setter
public class Player extends Creature {

  private static final int BASIC_PLAYER_MAX_HEALTH = 100;
  private static final int BASIC_PLAYER_ATTACK_POWER = 40;
  private static final int BASIC_SUPPLY_NUMBER = 3;
  private int supplies;
  private boolean foundTreasure;
  private Sector currentSector;

  public Player() {

    super("Player", BASIC_PLAYER_MAX_HEALTH, BASIC_PLAYER_ATTACK_POWER);
    foundTreasure = false;
    supplies = BASIC_SUPPLY_NUMBER;
  }

  public void move(String direction, GameContext context) {
    Island island = context.getIsland();
    int x = currentSector.getXCoordinate();
    int y = currentSector.getYCoordinate();
    int newX = x, newY = y;

    switch (direction.toLowerCase()) {
      case "n" -> newX = x - 1;
      case "s" -> newX = x + 1;
      case "w" -> newY = y - 1;
      case "e" -> newY = y + 1;
      default -> {
        System.out.println("Invalid direction!");
        return;
      }
    }

    if (newX < 0 || newX >= island.getWidth() || newY < 0 || newY >= island.getHeight()) {
      System.out.println("Cannot move outside the island!");
      return;
    }

    Sector next = island.getSectors()[newX][newY];

    if (next.getEvent() instanceof ObstacleEvent) {
      next.triggerEvent(context);
      return;
    }

    currentSector = next;
    next.triggerEvent(context);
  }

  public void heal(int heal) {
    currentHealth += heal;
    if (currentHealth > maxHealth) {
      currentHealth = maxHealth;
    }
  }

  public boolean useSupplyToHeal() {
    if (supplies > 0) {
      final int HEAL_AMOUNT_FOR_SUPPLY = 50;
      heal(HEAL_AMOUNT_FOR_SUPPLY);
      supplies--;
      return true;
    } else {
      return false;
    }
  }


}
