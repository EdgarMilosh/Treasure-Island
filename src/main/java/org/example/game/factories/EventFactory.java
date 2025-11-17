package org.example.game.factories;

import org.example.events.EventType;
import org.example.events.GuardEvent;
import org.example.events.ObstacleEvent;
import org.example.events.RandomOrNothingEvent;
import org.example.events.SectorEvent;
import org.example.events.TrapEvent;
import org.example.events.TreasureEvent;

public class EventFactory {

  public SectorEvent create(EventType type) {
    return switch (type) {
      case TREASURE -> new TreasureEvent();
      case TRAP -> new TrapEvent();
      case GUARD -> new GuardEvent();
      case OBSTACLE -> new ObstacleEvent();
      case RANDOM_OR_NOTHING -> new RandomOrNothingEvent();
    };
  }
}