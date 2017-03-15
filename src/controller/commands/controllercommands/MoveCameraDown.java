package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveCameraDown implements Command{

    Controller controllerToActOn;

    public MoveCameraDown(Controller controller) {
        controllerToActOn = controller;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveDown();
        return true;
    }
}
