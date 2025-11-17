package org.example.game.factories;

import org.example.events.*;

public class EventFactory {

    public SectorEvent create(EventType type) {
        return switch (type) {
            case TREASURE -> new TreasureEvent();
            case TRAP -> new TrapEvent();
            case GUARD -> new GuardEvent();
            case OBSTACLE -> new ObstacleEvent();
            case RANDOM_OR_NOTHING -> new RandomOrNothingEvent();
            case START -> new StartEvent();
        };
    }
}