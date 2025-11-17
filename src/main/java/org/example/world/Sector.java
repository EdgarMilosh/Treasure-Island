package org.example.world;

import lombok.Getter;
import lombok.Setter;
import org.example.events.ObstacleEvent;
import org.example.events.SectorEvent;
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

}





