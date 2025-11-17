package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.world.Sector;

@Getter
@Setter
public class Player extends Creature {

    private static final int BASIC_PLAYER_MAX_HEALTH = 100;
    private static final int BASIC_PLAYER_ATTACK_POWER = 40;
    private static final int BASIC_SUPPLY_NUMBER = 3;

    private int supplies;
    private boolean foundTreasure;
    private Sector currentSector;

    public Player() {
        super("Player", BASIC_PLAYER_MAX_HEALTH, BASIC_PLAYER_ATTACK_POWER);
        this.supplies = BASIC_SUPPLY_NUMBER;
        this.foundTreasure = false;
    }

}
