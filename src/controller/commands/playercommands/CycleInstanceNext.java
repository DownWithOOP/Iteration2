package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CycleDirection;
import model.player.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleInstanceNext implements Command {

    Player playerToActOn;

    public CycleInstanceNext(Player player) {
        playerToActOn = player;
    }

    @Override
    public boolean execute() {
        playerToActOn.cycleInstance(CycleDirection.INCREMENT);
        return true;
    }

}
