package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guard extends Creature {

    public Guard(String name, int maxHealth, int attackPower) {
        super(name, maxHealth, attackPower);
    }

}
