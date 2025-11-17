package org.example.events;

import org.example.game.GameContext;
import org.example.ui.Ui;

public class StartEvent implements SectorEvent {
    @Override
    public void execute(GameContext context) {
        Ui ui = context.getUi();
        ui.showMessage("This is your starting location.");
    }
}
