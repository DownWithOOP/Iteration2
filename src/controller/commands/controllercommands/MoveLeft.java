package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveLeft implements Command {

    Controller controllerToActOn;

    public MoveLeft(Controller controller) {
        controllerToActOn = controller;
    }

    @Override
    public void execute() {
        controllerToActOn.moveLeft();
    }
}
