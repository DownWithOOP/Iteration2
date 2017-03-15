package controller.commands.controllercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.ingamecontrollertypes.MainViewController;

/**
 * Created by Jonathen on 3/9/2017.
 */
public class MoveCursorSW implements Command {

    MainViewController controllerToActOn;
    private CommandType commandType;

    public MoveCursorSW(MainViewController controller) {
        controllerToActOn = controller;
        this.commandType = CommandType.MOVE_CURSOR_SOUTH_WEST;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveCursorSW();
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
