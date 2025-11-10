package org.example.events;

import org.example.game.GameContext;

public interface SectorEvent {

  void execute(GameContext context);
}
