package org.example.game.strategies;

import org.example.model.Creature;
import org.example.model.Player;
import org.example.ui.Ui;

public class EnemyAttackStrategy implements BattleStrategy {

    @Override
    public void execute(Player player, Creature enemy, Ui ui) {
        player.takeDamage(enemy.getAttackPower());
        ui.showMessage(enemy.getName() + " attacks you for " + enemy.getAttackPower() + " damage!");
    }
}