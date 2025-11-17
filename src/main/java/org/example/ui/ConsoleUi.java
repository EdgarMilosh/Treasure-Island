package org.example.ui;

import static org.example.actions.PlayerActions.move;
import static org.example.actions.PlayerActions.useSupplyToHeal;

import java.util.Scanner;
import org.example.game.GameContext;
import org.example.model.Creature;
import org.example.model.Player;
import org.example.world.Island;
import org.example.world.Sector;

public class ConsoleUi implements Ui {

  private static final int HEAL_AMOUNT_FOR_SUPPLY = 50;
  private final Scanner scanner;
  private final Player player;
  private final Island island;
  private final GameContext gameContext;

  public ConsoleUi(GameContext context) {
    this.player = context.getPlayer();
    this.island = context.getIsland();
    this.gameContext = context;
    this.scanner = new Scanner(System.in);
  }

  public void printInterface() {
    showMessage("================================================================");
    showPlayerStatus(player);
    showMap();
    showActions();
    scanMove();
  }

  @Override
  public void showPlayerStatus(Player player) {
    showMessage("HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
    showMessage("Supplies: " + player.getSupplies());
  }

  @Override
  public void showMap() {
    showMessage("Island map: ");
    for (int x = 0; x < island.getWidth(); x++) {
      StringBuilder line = new StringBuilder();
      for (int y = 0; y < island.getHeight(); y++) {
        Sector sector = island.getSectors()[x][y];
        if (player.getCurrentSector().equals(sector)) {
          line.append("P ");
        } else {
          line.append(getSectorSymbol(sector));
        }
      }
      showMessage(line.toString());
    }
  }


  private String getSectorSymbol(Sector sector) {
    if (sector.isVisited()) {
      return "V ";
    }
    switch (sector.getEvent().getClass().getSimpleName()) {
      case "TrapEvent" -> {
        return "T ";
      }
      case "TreasureEvent" -> {
        return "W ";
      }
      case "GuardEvent" -> {
        return "G ";
      }
      case "ObstacleEvent" -> {
        return "X ";
      }
      case "RandomOrNothingEvent" -> {
        return ". ";
      }
      default -> {
        return "? ";
      }
    }
  }

  @Override
  public void showActions() {
    showMessage("Actions: M - move to the next sector\n"
        + "H - use supplies to heal (" + HEAL_AMOUNT_FOR_SUPPLY + " HP)");
  }

  @Override
  public void scanMove() {
    String move = getInput();
    switch (move.toUpperCase()) {
      case "M" -> handleMove();
      case "H" -> handleHeal();
      default -> showMessage("Invalid action! Try again!");
    }
  }

  private void handleMove() {
    showMessage("Select a direction to move:\nN - north\nE - east\nW - west\nS - south");
    String direction = getInput().trim();
    move(player, direction, gameContext);
  }

  private void handleHeal() {
    if (useSupplyToHeal(player)) {
      showMessage("You used 1 supply to heal for " + HEAL_AMOUNT_FOR_SUPPLY + " HP!");
    } else {
      showMessage("Can't heal - no supplies left");
    }
  }

  @Override
  public String getInput() {
    return scanner.nextLine().toLowerCase();
  }

  @Override
  public void showMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void showBattleStatus(Player player, Creature enemy) {
    showMessage(player.getName() + " " + player.getCurrentHealth() + "/" + player.getMaxHealth());
    showMessage(enemy.getName() + " HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
  }
}
