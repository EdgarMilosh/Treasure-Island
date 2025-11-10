package org.example.events;

import org.example.game.GameContext;
import org.example.model.Player;

public class TrapEvent implements SectorEvent {

  @Override
  public void execute(GameContext context) {
    Player player = context.getPlayer();
    int TRAP_DAMAGE = 30;
    player.takeDamage(TRAP_DAMAGE);
    System.out.println("You triggered a trap - LOST" + TRAP_DAMAGE + " HP!");
  }
}
