package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.entitycommand.AbstractCommand;
import model.RallyPoint;
import model.common.Location;

/**
 * Created by Jonathen on 3/15/2017.
 */
public class PickUpWorkerCommand extends AbstractCommand {

    private RallyPoint rallyPointToActOn;

    public PickUpWorkerCommand(RallyPoint rallyPoint) {
        super(1);
        rallyPointToActOn = rallyPoint;
    }

    @Override
    public boolean execute() {
        rallyPointToActOn.addWorkersToArmy();
        return true;
    }

    @Override
    public void setCommandType(CommandType commandType) {
        //do nothing
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.PICKUP_WORKERS;
    }
}

