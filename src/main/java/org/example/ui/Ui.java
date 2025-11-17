package org.example.ui;

import org.example.model.Creature;
import org.example.model.Player;

public interface Ui {

    void showMessage(String message);

    void showPlayerStatus(Player player);

    void showBattleStatus(Player player, Creature enemy);

    void showMap();

    void showActions();

    void scanMove();

    String getInput();
}
