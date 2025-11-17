package org.example.events;

import org.example.game.GameContext;
import org.example.ui.Ui;


public class ObstacleEvent implements SectorEvent {

  @Override
  public void execute(GameContext context) {
    Ui ui = context.getUi();
    ui.showMessage("This sector is blocked! You cannot enter. Choose another direction.");
  }
}
