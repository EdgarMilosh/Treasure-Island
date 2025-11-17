package org.example.game.factories;

import org.example.model.Guard;

public class BasicEnemyFactory implements EnemyFactory {

  private static final int BASIC_GUARD_MAX_HP = 50;
  private static final int BASIC_GUARD_DAMAGE = 10;

  @Override
  public Guard createGuard() {
    return new Guard("Island Guard", BASIC_GUARD_MAX_HP, BASIC_GUARD_DAMAGE);
  }
}
