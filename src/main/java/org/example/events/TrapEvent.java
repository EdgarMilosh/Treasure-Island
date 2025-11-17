package org.example.events;

import org.example.game.GameContext;
import org.example.model.Player;
import org.example.ui.Ui;

public class TrapEvent implements SectorEvent {

  private final static int TRAP_DAMAGE = 30;

  @Override
  public void execute(GameContext context) {
    Player player = context.getPlayer();
    Ui ui = context.getUi();

    triggerTrap(player, ui);
  }

  private void triggerTrap(Player player, Ui ui) {
    player.takeDamage(TRAP_DAMAGE);
    ui.showMessage("You triggered a trap! LOST " + TRAP_DAMAGE + " HP!");
  }
}
