package org.example.events;

import org.example.game.GameContext;


public class ObstacleEvent implements SectorEvent {

  @Override
  public void execute(GameContext context) {
    System.out.println("This sector is blocked! You cannot enter. Choose another direction.");
  }
}
