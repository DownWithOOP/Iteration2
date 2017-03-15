package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.CycleDirection;
import model.player.Player;

/**
 * Created by Jonathen on 2/26/2017.
 */
public class CycleTypeNext implements Command {

    Player playerToActOn;
    private CommandType commandType;

    public CycleTypeNext(Player player) {
        playerToActOn = player;
        this.commandType = CommandType.CYCLE_TYPE_NEXT;
    }

    @Override
    public boolean execute() {
        playerToActOn.cycleType(CycleDirection.DECREMENT);
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
