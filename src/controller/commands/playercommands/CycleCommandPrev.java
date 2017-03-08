package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CycleDirection;
import model.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleCommandPrev implements Command {

    Player playerToActOn;

    public CycleCommandPrev(Player player) {
        playerToActOn = player;
    }

    @Override
    public boolean execute() {
        playerToActOn.cycleCommand(CycleDirection.DECREMENT);
        return true;
    }



}
