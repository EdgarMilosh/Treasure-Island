package org.example.game;

import org.example.model.Player;
import org.example.ui.ConsoleUi;
import org.example.world.Island;

public class Game {

  private final Player player;
  private final Island island;
  private final ConsoleUi ui;

  private boolean gameOver;

  public Game() {
    this.player = new Player();
    this.island = new Island(player);
    GameContext gameContext = new GameContext(player, island, null);
    this.ui = new ConsoleUi(gameContext);
    gameContext.setUi(ui);

    gameOver = false;
  }

  public void play() {
    while (!gameOver) {
      ui.printInterface();
      if (player.isDead()) {
        gameOver = true;
        System.out.println("You lost,\ngame over!");
      } else if (player.isFoundTreasure() && player.getCurrentSector() == island.getStartSector()) {
        gameOver = true;
        System.out.println("==============================");
        System.out.println("Congratulations!\nYou won!");
        System.out.println("==============================");
      }


    }
  }

}
