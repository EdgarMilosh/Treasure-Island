package org.example.events;

import org.example.game.GameContext;
import org.example.model.Player;
import org.example.ui.Ui;

public class TreasureEvent implements SectorEvent {

    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        Ui ui = context.getUi();

        triggerTreasure(player, ui);
    }

    private void triggerTreasure(Player player, Ui ui) {
        player.setFoundTreasure(true);
        ui.showMessage("You found a treasure! Now return to the starting sector!");
    }
}
