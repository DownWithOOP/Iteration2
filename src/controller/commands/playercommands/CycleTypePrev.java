package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CycleDirection;
import model.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleTypePrev implements Command {

    Player playerToActOn;

    public CycleTypePrev(Player player) {
        playerToActOn = player;
    }

    @Override
    public boolean execute() {
        playerToActOn.cycleType(CycleDirection.INCREMENT);
        return true;
    }
}
