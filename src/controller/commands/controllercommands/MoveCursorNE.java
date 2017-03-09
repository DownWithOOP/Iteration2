package controller.commands.controllercommands;

import controller.commands.Command;
import controller.ingamecontrollertypes.MainViewController;

/**
 * Created by Jonathen on 3/9/2017.
 */
public class MoveCursorNE implements Command {

    MainViewController controllerToActOn;

    public MoveCursorNE(MainViewController controller) {
        controllerToActOn = controller;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveCursorNE();
        return true;
    }

}
