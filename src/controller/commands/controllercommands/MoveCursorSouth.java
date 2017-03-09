package controller.commands.controllercommands;

import controller.commands.Command;
import controller.ingamecontrollertypes.MainViewController;

/**
 * Created by Jonathen on 3/9/2017.
 */
public class MoveCursorSouth implements Command {

    MainViewController controllerToActOn;

    public MoveCursorSouth(MainViewController controller) {
        controllerToActOn = controller;
    }

    @Override
    public boolean execute() {
        controllerToActOn.moveCursorSouth();
        return true;
    }

}
