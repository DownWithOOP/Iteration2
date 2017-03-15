package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;
import controller.commands.CommandType;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveCameraRight implements Command {


    Controller controllerToActOn;
    private CommandType commandType;

    public MoveCameraRight(Controller controller) {
        controllerToActOn = controller;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveRight();
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
