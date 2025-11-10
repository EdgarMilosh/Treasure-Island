package org.example.world;

import lombok.Getter;
import lombok.Setter;
import org.example.events.GuardEvent;
import org.example.events.RandomOrNothingEvent;
import org.example.events.ObstacleEvent;
import org.example.events.SectorEvent;
import org.example.events.TrapEvent;
import org.example.events.TreasureEvent;
import org.example.game.GameContext;

@Getter
@Setter
public class Sector {

  private int xCoordinate;
  private int yCoordinate;
  private boolean visited;
  private SectorEvent event;

  public Sector(int x, int y, SectorEvent event) {
    this.xCoordinate = x;
    this.yCoordinate = y;
    this.event = event;
    this.visited = false;
  }


  public void triggerEvent(GameContext context) {
    if (!visited && !(event instanceof ObstacleEvent)) {
      event.execute(context);
      visited = true;
    }
  }

  public void printOnMap() {
    if (visited) {
      System.out.print("V ");
    } else if (event instanceof TrapEvent) {
      System.out.print("T ");
    } else if (event instanceof TreasureEvent) {
      System.out.print("W ");
    } else if (event instanceof GuardEvent) {
      System.out.print("G ");
    } else if (event instanceof ObstacleEvent) {
      System.out.print("X ");
    } else if (event instanceof RandomOrNothingEvent) {
      System.out.print(". ");
    } else {
      System.out.print("? ");
    }
  }
}





