package org.example.events;

import org.example.game.GameContext;
import org.example.game.factories.BasicEnemyFactory;
import org.example.game.factories.EnemyFactory;
import org.example.game.strategies.BattleStrategy;
import org.example.game.strategies.EnemyAttackStrategy;
import org.example.game.strategies.PlayerAttackStrategy;
import org.example.game.strategies.RunAwayStrategy;
import org.example.model.Creature;
import org.example.model.Guard;
import org.example.model.Player;
import org.example.ui.Ui;

public class GuardEvent implements SectorEvent {

  private final EnemyFactory enemyFactory = new BasicEnemyFactory();

  @Override
  public void execute(GameContext context) {
    Player player = context.getPlayer();
    Ui ui = context.getUi();
    Guard guard = enemyFactory.createGuard();

    ui.showMessage("======================================");
    ui.showMessage(guard.getName() + " blocks your path!");

    startBattle(player, guard, ui);
  }

  private void startBattle(Player player, Guard guard, Ui ui) {
    BattleStrategy playerAttack = new PlayerAttackStrategy();
    BattleStrategy enemyAttack = new EnemyAttackStrategy();
    RunAwayStrategy runAway = new RunAwayStrategy();

    while (!guard.isDead() && !player.isDead()) {
      ui.showBattleStatus(player, guard);

      ui.showMessage("Choose action (a - attack / r - run): ");
      String action = ui.getInput().trim().toLowerCase();

      switch (action) {
        case "a", "attack" -> playerAttack.execute(player, guard, ui);
        case "r", "run" -> {
          runAway.execute(player, guard, ui);
          if (runAway.isSuccess()) {
            return;
          }
        }
        default -> ui.showMessage("Not a valid action! You skipped your turn");
      }

      if (!guard.isDead()) {
        enemyAttack.execute(player, guard, ui);
      }
    }

    showBattleOutcome(guard, ui);
  }

  private void showBattleOutcome(Creature enemy, Ui ui) {
    ui.showMessage("============================");
    if (enemy.isDead()) {
      ui.showMessage("You defeated the " + enemy.getName() + "!");
    } else {
      ui.showMessage("You have been defeated...");
    }
    ui.showMessage("============================");
  }
}
