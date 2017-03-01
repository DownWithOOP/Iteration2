package model;

import controller.availablecommands.Commandable;
import controller.commands.Direction;
import model.common.Location;

/**
 * Created by Jonathen on 2/25/2017.
 */
public class RallyPoint extends Cursor {


    public RallyPoint(Location location) {
        super(location);
    }

    public void move(Direction direction){
        System.out.print("rally point moves "+direction.toString()+" and then ");
        super.move(direction);
    }
}
