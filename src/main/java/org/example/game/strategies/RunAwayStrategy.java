package org.example.game.strategies;

import java.util.Random;
import lombok.Getter;
import org.example.model.Creature;
import org.example.model.Player;
import org.example.ui.Ui;

@Getter
public class RunAwayStrategy implements BattleStrategy {

  private boolean success;

  @Override
  public void execute(Player player, Creature enemy, Ui ui) {
    Random rand = new Random();
    success = rand.nextBoolean();
    if (success) {
      ui.showMessage("You successfully ran away!");
    } else {
      ui.showMessage("You failed to run away!");
    }
  }
}