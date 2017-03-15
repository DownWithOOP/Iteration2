package controller.commands.controllercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.ingamecontrollertypes.MainViewController;

/**
 * Created by Jonathen on 3/9/2017.
 */
public class MoveCursorSE implements Command {

    MainViewController controllerToActOn;
    private CommandType commandType;

    public MoveCursorSE(MainViewController controller) {
        controllerToActOn = controller;
        this.commandType = CommandType.MOVE_CURSOR_SOUTH_EAST;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveCursorSE();
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
