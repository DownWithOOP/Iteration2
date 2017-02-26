package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CycleDirection;
import model.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleCommandNext implements Command {

    Player playerToActOn;

    public CycleCommandNext(Player player) {
        playerToActOn = player;
    }

    @Override
    public void execute() {
        playerToActOn.cycleCommand(CycleDirection.INCREMENT);
    }

}
