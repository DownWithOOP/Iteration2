package controller.commands.controllercommands;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.ingamecontrollertypes.MainViewController;

/**
 * Created by Jonathen on 3/9/2017.
 */
public class MoveCursorNE implements Command {

    MainViewController controllerToActOn;
    private CommandType commandType;

    public MoveCursorNE(MainViewController controller) {
        controllerToActOn = controller;
        this.commandType = CommandType.MOVE_CURSOR_NORTH_EAST;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveCursorNE();
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
