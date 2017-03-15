package model;

import controller.CommandRelay;
import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import controller.commands.Direction;
import model.common.Location;
import model.entities.unit.Army;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Jonathen on 2/25/2017.
 */
public class RallyPoint extends Commandable {
    Queue<Location> path= new ArrayDeque<>();
    Location currentLocation;
    Army army;

    static ArrayList<CommandType> rallyPointCommand= new ArrayList<>();
    static {
        rallyPointCommand.add(CommandType.FOCUS);
    }

    public RallyPoint(CommandRelay commandRelay, Location location, Army army) {
        super(commandRelay);
        currentLocation = location;
        //addAllCommands(rallyPointCommand);
        this.army=army;
    }

    public void move(Direction direction){
        System.out.print("rally point moves "+direction.toString()+" and then ");
        //super.move(direction);
        //path.add(location);
    }

    public void focus(){
        System.out.println("FOCUSED RALLY POINT!!!");
        army.updatePathQueue(path);
        //TODO:check if this will delete the queue on the army side
        path.clear();
    }

    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public String toString() {
        return "RALLY POINT " + army.getEntityId().getId();
    }
}
