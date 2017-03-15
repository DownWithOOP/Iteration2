package controller.commands.playercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import model.common.Location;
import model.player.Player;

/**
 * Created by Jonathen on 3/14/2017.
 */
public class CreateArmy implements Command {

    private Player playerToActOn;
    private CommandType commandType;

    public CreateArmy(Player player) {
        playerToActOn = player;
        this.commandType = CommandType.CREATE_ARMY;
    }

    @Override
    public boolean execute() {
        playerToActOn.createArmy();
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
