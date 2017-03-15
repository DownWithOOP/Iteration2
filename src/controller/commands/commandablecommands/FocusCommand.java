package controller.commands.commandablecommands;

import controller.commands.Command;
import controller.commands.CommandType;
import model.RallyPoint;

/**
 * Created by jordi on 3/13/2017.
 */
public class FocusCommand implements Command {
    RallyPoint rallyPoint;
    private CommandType commandType;

    public FocusCommand(RallyPoint rallyPoint){
        this.rallyPoint=rallyPoint;
        this.commandType = CommandType.FOCUS;
    }

    @Override
    public boolean execute() {
        rallyPoint.focus();
        return false;
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
