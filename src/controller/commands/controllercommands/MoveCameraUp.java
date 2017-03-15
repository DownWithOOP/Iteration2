package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;
import controller.commands.CommandType;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveCameraUp implements Command {

    Controller controllerToActOn;
    private CommandType commandType;

    public MoveCameraUp(Controller controller) {
        controllerToActOn = controller;
        this.commandType = CommandType.MOVE_CAMERA_UP;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveUp();
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
