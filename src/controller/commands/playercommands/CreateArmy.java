package controller.commands.playercommands;

import controller.commands.Command;
import model.common.Location;
import model.player.Player;

/**
 * Created by Jonathen on 3/14/2017.
 */
public class CreateArmy implements Command {

    private Player playerToActOn;

    public CreateArmy(Player player) {
        playerToActOn = player;
    }

    @Override
    public boolean execute() {
        playerToActOn.createArmy();
        return true;
    }
}
