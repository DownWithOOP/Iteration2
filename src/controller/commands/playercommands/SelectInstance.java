package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import model.player.Player;

/**
 * Created by Jonathen on 3/15/2017.
 */
public class SelectInstance implements Command {

    Player playerToActOn;
    int indexToSelect;

    public SelectInstance(Player activePlayer, int index) {
        playerToActOn = activePlayer;
        indexToSelect = index;
    }

    @Override
    public boolean execute() {
        playerToActOn.getInstanceFromIndex(indexToSelect);
        return true;
    }

    @Override
    public void setCommandType(CommandType commandType) {

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.SELECT_INSTANCE;
    }
}
