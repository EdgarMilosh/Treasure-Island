package org.example.events;

import java.util.Random;
import java.util.Scanner;
import org.example.game.GameContext;
import org.example.model.Guard;
import org.example.model.Player;

public class GuardEvent implements SectorEvent {

  @Override
  public void execute(GameContext context) {
    Player player = context.getPlayer();

    final int BASIC_GUARD_MAX_HP = 50;
    final int BASIC_GUARD_DAMAGE = 10;
    Guard guard = new Guard("Island Guard", BASIC_GUARD_MAX_HP, BASIC_GUARD_DAMAGE);
    System.out.println("======================================");
    System.out.println("An " + guard.getName() + "blocks your path!");

    Scanner scanner = context.getScanner();
    while (!guard.isDead() && !player.isDead()) {
      System.out.println("================================================");
      System.out.println(player.getName() + " " + player.getCurrentHealth() + "/" + player.getMaxHealth());
      System.out.println(
          guard.getName() + " HP: " + guard.getCurrentHealth() + "/" + guard.getMaxHealth());

      System.out.print("Choose action (a - attack/ r - run): ");
      String action = scanner.nextLine().toLowerCase();

      switch (action) {
        case "attack" , "a" -> {
          guard.takeDamage(player.getAttackPower());
          System.out.println("You attacked the guard for " + player.getAttackPower() + " damage!");
        }
        case "run" , "r" -> {
          Random rand = new Random();
          if (rand.nextBoolean()) {
            System.out.println("============================");
            System.out.println("You successfully ran away!");
            return;
          } else {
            System.out.println("You failed to run away!");
          }

        } default -> System.out.println("Not a valid action!\n You skipped your turn");
      }

      if (!guard.isDead()) {
        player.takeDamage(guard.getAttackPower());
        System.out.println("The guard attacks you for " + guard.getAttackPower() + " damage!");
      }
    }

    if (guard.isDead()) {
      System.out.println("============================");
      System.out.println("You defeated the guard!");
      System.out.println("============================");
    } else {
      System.out.println("============================");
      System.out.println("You have been defeated...");
      System.out.println("============================");
    }
  }
}

