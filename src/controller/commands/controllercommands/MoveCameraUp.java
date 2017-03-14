package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveCameraUp implements Command {

    Controller controllerToActOn;

    public MoveCameraUp(Controller controller) {
        controllerToActOn = controller;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveUp();
        return true;
    }
}
