package org.example.game.strategies;

import org.example.model.Creature;
import org.example.model.Player;
import org.example.ui.Ui;

public interface BattleStrategy {

  void execute(Player player, Creature enemy, Ui ui);
}
