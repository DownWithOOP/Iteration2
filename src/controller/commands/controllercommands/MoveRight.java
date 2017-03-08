package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveRight implements Command {


    Controller controllerToActOn;

    public MoveRight(Controller controller) {
        controllerToActOn = controller;
    }

    @Override
    public void execute() {
        controllerToActOn.moveRight();
    }
}
