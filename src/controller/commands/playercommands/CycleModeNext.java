package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CycleDirection;
import model.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleModeNext implements Command{

    Player playerToActOn;

    public CycleModeNext(Player player) {
        playerToActOn = player;
    }

    @Override
    public void execute() {
        playerToActOn.cycleMode(CycleDirection.INCREMENT);
    }
}
