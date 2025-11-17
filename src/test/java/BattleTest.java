import org.example.game.factories.BasicEnemyFactory;
import org.example.model.Guard;
import org.example.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleTest {

    private Player player;
    private Guard guard;
    private BasicEnemyFactory factory = new BasicEnemyFactory();


    @BeforeEach
    public void setup() {
        player = new Player();
        guard = factory.createGuard();
    }

    @Test
    public void testPlayerAttack() {
        int guardHP = guard.getCurrentHealth();
        guard.takeDamage(player.getAttackPower());
        assertEquals(guardHP - player.getAttackPower(), guard.getCurrentHealth());
    }

    @Test
    public void testGuardAttack() {
        int playerHP = player.getCurrentHealth();
        player.takeDamage(guard.getAttackPower());
        assertEquals(playerHP - guard.getAttackPower(), player.getCurrentHealth());
    }

    @Test
    public void testCreatureDeath() {
        guard.takeDamage(1000);
        assertTrue(guard.isDead());
    }
}
