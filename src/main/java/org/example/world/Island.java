package org.example.world;

import lombok.Getter;
import lombok.Setter;
import org.example.events.EventType;
import org.example.game.factories.EventFactory;
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

        EventFactory factory = new EventFactory();

        sectors[1][3] = new Sector(1, 3, factory.create(EventType.TREASURE));

        sectors[0][3] = new Sector(0, 3, factory.create(EventType.OBSTACLE));
        sectors[0][4] = new Sector(0, 4, factory.create(EventType.OBSTACLE));
        sectors[1][1] = new Sector(1, 1, factory.create(EventType.OBSTACLE));
        sectors[2][1] = new Sector(2, 1, factory.create(EventType.OBSTACLE));
        sectors[2][2] = new Sector(2, 2, factory.create(EventType.OBSTACLE));
        sectors[3][3] = new Sector(3, 3, factory.create(EventType.OBSTACLE));
        sectors[4][1] = new Sector(4, 1, factory.create(EventType.OBSTACLE));

        sectors[2][0] = new Sector(2, 0, factory.create(EventType.TRAP));
        sectors[3][2] = new Sector(3, 2, factory.create(EventType.GUARD));

        startSector = new Sector(4, 0, factory.create(EventType.START));
        sectors[4][0] = startSector;
        player.setCurrentSector(startSector);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (sectors[x][y] == null) {
                    sectors[x][y] = new Sector(x, y, factory.create(EventType.RANDOM_OR_NOTHING));
                }
            }
        }


        treasureSector = sectors[1][3];
    }
}
