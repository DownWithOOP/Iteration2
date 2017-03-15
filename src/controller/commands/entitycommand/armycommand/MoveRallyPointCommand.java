package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
import controller.commands.CommandType;
import model.RallyPoint;
import model.common.Location;

/**
 * Created by Jonathen on 3/14/2017.
 */
public class MoveRallyPointCommand implements Command {

    private RallyPoint rallyPointToActOn;
    private Location locationToMoveTo;

    public MoveRallyPointCommand(RallyPoint rallyPoint, Location location) {
        rallyPointToActOn = rallyPoint;
        locationToMoveTo = location.clone();
    }

    @Override
    public boolean execute() {
        rallyPointToActOn.move(locationToMoveTo);
        return true;
    }

    @Override
    public void setCommandType(CommandType commandType) {
        //do nothing
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MOVE_RALLY_POINT;
    }
}
