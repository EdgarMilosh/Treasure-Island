package org.example.game;

import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Player;
import org.example.ui.ConsoleUi;
import org.example.world.Island;

@Getter
@Setter
public class GameContext {
  private Player player;
  private Island island;
  private ConsoleUi ui;
  private Scanner scanner;

  public GameContext(Player player, Island island, ConsoleUi ui) {
    this.player = player;
    this.island = island;
    this.ui = ui;
    this.scanner = new Scanner(System.in);
  }

}
