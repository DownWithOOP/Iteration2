package controller.commands.controllercommands;

import controller.Controller;
import controller.commands.Command;

/**
 * Created by Jonathen on 3/8/2017.
 */
public class MoveUp implements Command {

    Controller controllerToActOn;

    public MoveUp(Controller controller) {
        controllerToActOn = controller;
    }

    @Override
    public void execute() {
        controllerToActOn.moveUp();
    }
}
