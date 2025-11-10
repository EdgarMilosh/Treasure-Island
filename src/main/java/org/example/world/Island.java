package org.example.world;

import lombok.Getter;
import lombok.Setter;
import org.example.events.GuardEvent;
import org.example.events.RandomOrNothingEvent;
import org.example.events.ObstacleEvent;
import org.example.events.TrapEvent;
import org.example.events.TreasureEvent;
import org.example.model.Player;

@Getter
@Setter
public class Island {

  private final Player player;
  private Sector[][] sectors;
  private int width;
  private int height;
  private Sector startSector;
  private Sector treasureSector;

  public Island(Player player) {
    this.player = player;
    createManualMap();
  }


  public void createManualMap() {
    this.width = 5;
    this.height = 5;

    this.sectors = new Sector[width][height];

    sectors[1][3] = new Sector(1, 3, new TreasureEvent());
    sectors[0][3] = new Sector(0, 3, new ObstacleEvent());
    sectors[0][4] = new Sector(0, 4, new ObstacleEvent());
    sectors[1][1] = new Sector(1, 1, new ObstacleEvent());
    sectors[2][1] = new Sector(2, 1, new ObstacleEvent());
    sectors[2][2] = new Sector(2, 2, new ObstacleEvent());
    sectors[3][3] = new Sector(3, 3, new ObstacleEvent());
    sectors[4][1] = new Sector(4, 1, new ObstacleEvent());

    sectors[2][0] = new Sector(2, 0, new TrapEvent());
    sectors[3][2] = new Sector(3, 2, new GuardEvent());

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (sectors[x][y] == null) {
          sectors[x][y] = new Sector(x, y, new RandomOrNothingEvent());
        }
      }
    }

    startSector = sectors[4][0];
    player.setCurrentSector(startSector);
    treasureSector = sectors[1][3];
  }
}
