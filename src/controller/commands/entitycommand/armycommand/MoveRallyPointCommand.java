package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
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
}
