import org.example.actions.PlayerActions;
import org.example.events.EventType;
import org.example.events.ObstacleEvent;
import org.example.game.GameContext;
import org.example.game.factories.EventFactory;
import org.example.model.Player;
import org.example.ui.ConsoleUi;
import org.example.world.Island;
import org.example.world.Sector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private Player player;
    private Island island;
    private GameContext context;
    private ConsoleUi ui;
    private EventFactory factory = new EventFactory();

    @BeforeEach
    public void setup() {
        player = new Player();
        island = new Island(player);
        context = new GameContext(player, island, null);
        ui = new ConsoleUi(context);
        context.setUi(ui);

        Sector[][] sectors = new Sector[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sectors[i][j] = new Sector(i, j, factory.create(EventType.TRAP));
            }
        }
        island.setSectors(sectors);

        player.setCurrentSector(sectors[1][1]);
    }

    @Test
    public void testTakeDamage() {
        int initialHP = player.getCurrentHealth();
        player.takeDamage(30);
        assertEquals(initialHP - 30, player.getCurrentHealth());
    }

    @Test
    public void testHeal() {
        player.takeDamage(50);
        PlayerActions.heal(player, 30);
        assertEquals(player.getMaxHealth() - 20, player.getCurrentHealth());
    }

    @Test
    public void testUseSupplyToHeal() {
        player.takeDamage(60);
        boolean used = PlayerActions.useSupplyToHeal(player);
        assertTrue(used);
        assertEquals(player.getMaxHealth() - 10, player.getCurrentHealth());
        assertEquals(2, player.getSupplies());
    }

    @Test
    void testMoveNorth() {
        PlayerActions.move(player, "n", context);
        assertEquals(0, player.getCurrentSector().getXCoordinate());
        assertEquals(1, player.getCurrentSector().getYCoordinate());
    }

    @Test
    void testMoveSouth() {
        PlayerActions.move(player, "s", context);
        assertEquals(2, player.getCurrentSector().getXCoordinate());
        assertEquals(1, player.getCurrentSector().getYCoordinate());
    }

    @Test
    void testMoveWest() {
        PlayerActions.move(player, "w", context);
        assertEquals(1, player.getCurrentSector().getXCoordinate());
        assertEquals(0, player.getCurrentSector().getYCoordinate());
    }

    @Test
    void testMoveEast() {
        PlayerActions.move(player, "e", context);
        assertEquals(1, player.getCurrentSector().getXCoordinate());
        assertEquals(2, player.getCurrentSector().getYCoordinate());
    }

    @Test
    void testMoveBlockedByObstacle() {
        Sector obstacleSector = new Sector(0, 1, new ObstacleEvent());
        island.getSectors()[0][1] = obstacleSector;
        player.setCurrentSector(island.getSectors()[0][0]);

        PlayerActions.move(player, "e", context);

        assertEquals(island.getSectors()[0][0], player.getCurrentSector());
    }

    @Test
    void testMoveOutOfBounds() {
        player.setCurrentSector(island.getSectors()[0][0]);
        PlayerActions.move(player, "n", context);
        assertEquals(0, player.getCurrentSector().getXCoordinate());
        assertEquals(0, player.getCurrentSector().getYCoordinate());
    }
}
