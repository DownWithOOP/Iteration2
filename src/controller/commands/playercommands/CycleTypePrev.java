package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.CycleDirection;
import model.player.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleTypePrev implements Command {

    Player playerToActOn;
    private CommandType commandType;

    public CycleTypePrev(Player player) {
        playerToActOn = player;
        this.commandType = CommandType.CYCLE_TYPE_PREV;
    }

    @Override
    public boolean execute() {
        playerToActOn.cycleType(CycleDirection.INCREMENT);
        return true;
    }

    @Override
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public CommandType getCommandType() {
        return this.commandType;
    }
}
