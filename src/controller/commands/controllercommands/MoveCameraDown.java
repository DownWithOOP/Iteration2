package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;
import controller.commands.CommandType;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveCameraDown implements Command{

    Controller controllerToActOn;
    private CommandType commandType;

    public MoveCameraDown(Controller controller) {
        controllerToActOn = controller;
        this.commandType = CommandType.MOVE_CAMERA_DOWN;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveDown();
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
