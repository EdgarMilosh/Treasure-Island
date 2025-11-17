package org.example.game;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Player;
import org.example.ui.Ui;
import org.example.world.Island;

@Getter
@Setter
public class GameContext {

  private Player player;
  private Island island;
  private Ui ui;

  public GameContext(Player player, Island island, Ui ui) {
    this.player = player;
    this.island = island;
    this.ui = ui;
  }

}
