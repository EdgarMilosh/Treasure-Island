package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Creature {

  protected String name;
  protected int currentHealth;
  protected int maxHealth;
  protected int attackPower;
  protected boolean dead;

  public Creature(String name, int maxHealth, int attackPower) {
    this.name = name;
    this.maxHealth = maxHealth;
    this.currentHealth = maxHealth;
    this.attackPower = attackPower;
    dead = false;
  }

  public void takeDamage(int damage) {
    currentHealth -= damage;
    if (currentHealth <= 0) {
      dead = true;
    }
  }

}
