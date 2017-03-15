package controller.commands.controllercommands;

import controller.commands.Command;
import controller.ingamecontrollertypes.MainViewController;

/**
 * Created by Jonathen on 3/9/2017.
 */
public class MoveCursorNW implements Command {

    MainViewController controllerToActOn;

    public MoveCursorNW(MainViewController controller) {
        controllerToActOn = controller;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveCursorNW();
        return true;
    }

}
