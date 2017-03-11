package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveCameraLeft implements Command {

    Controller controllerToActOn;

    public MoveCameraLeft(Controller controller) {
        controllerToActOn = controller;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveLeft();
        return true;
    }
}
