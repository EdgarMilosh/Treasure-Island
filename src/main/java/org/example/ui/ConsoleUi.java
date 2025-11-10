package org.example.ui;

import java.util.Scanner;
import org.example.game.GameContext;
import org.example.model.Player;
import org.example.world.Island;

public class ConsoleUi {

  private final Scanner scanner;
  private final Player player;
  private final Island island;
  private final GameContext gameContext;

  public ConsoleUi(GameContext context) {
    this.player = context.getPlayer();
    this.island = context.getIsland();
    this.gameContext = context;
    this.scanner = context.getScanner();
  }


  public void printInterface() {
    System.out.println("================================================================");
    System.out.println("HP:" + player.getCurrentHealth() + "/" + player.getMaxHealth());
    System.out.println("Supplies: " + player.getSupplies());
    System.out.println("Island map: ");
    printMap();

    System.out.println("Actions: ");
    System.out.println("M - move to the next sector\nH - use supplies to heal (50 hp)");
    scanMove();

  }

  public void printMap() {
    for (int x = 0; x < island.getWidth(); x++) {
      for (int y = 0; y < island.getHeight(); y++) {
        if (island.getPlayer().getCurrentSector().equals(island.getSectors()[x][y])) {
          System.out.print("P ");
        } else {
          island.getSectors()[x][y].printOnMap();
        }
      }
      System.out.println();
    }
  }

  public void scanMove() {
    String move = scanner.nextLine();
    switch (move.toUpperCase()) {
      case "M":
        System.out.println("Select a direction to move!\nN - north\nE - east\nW - west\nS - south");
        String direction = scanner.nextLine();
        player.move(direction, gameContext);
        break;
      case "H":
        if (player.useSupplyToHeal()) {
          System.out.println("You used 1 supply to heal for 50 HP!");
        } else {
          System.out.println("Can't heal - no supplies left");
        }
        break;
      default:
        System.out.println("Invalid action! Try again!");
    }
  }
}
