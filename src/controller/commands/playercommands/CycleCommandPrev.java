package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.CycleDirection;
import model.player.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleCommandPrev implements Command {

    Player playerToActOn;
    private CommandType commandType;

    public CycleCommandPrev(Player player) {
        playerToActOn = player;
        this.commandType = CommandType.CYCLE_COMMAND_PREV;
    }

    @Override
    public boolean execute() {
        playerToActOn.cycleCommand(CycleDirection.DECREMENT);
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
