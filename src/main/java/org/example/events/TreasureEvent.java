package org.example.events;

import org.example.game.GameContext;
import org.example.model.Player;

public class TreasureEvent implements SectorEvent {

  @Override
  public void execute(GameContext context) {
    Player player = context.getPlayer();
    player.setFoundTreasure(true);
    System.out.println("You found a treasure! \nNow return to the starting sector!");
  }
}
