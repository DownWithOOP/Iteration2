package controller.commands.commandablecommands;

import controller.commands.Command;
import model.RallyPoint;

/**
 * Created by jordi on 3/13/2017.
 */
public class FocusCommand implements Command {
    RallyPoint rallyPoint;

    public FocusCommand(RallyPoint rallyPoint){
        this.rallyPoint=rallyPoint;
    }

    @Override
    public boolean execute() {
        rallyPoint.focus();
        return false;
    }
}
