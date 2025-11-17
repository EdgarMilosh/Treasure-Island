package org.example.game.strategies;

import org.example.model.Creature;
import org.example.model.Player;
import org.example.ui.Ui;

public class PlayerAttackStrategy implements BattleStrategy {

    @Override
    public void execute(Player player, Creature enemy, Ui ui) {
        enemy.takeDamage(player.getAttackPower());
        ui.showMessage(
                "You attacked " + enemy.getName() + " for " + player.getAttackPower() + " damage!");
    }
}