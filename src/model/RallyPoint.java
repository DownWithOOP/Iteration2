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
        rallyPointCommand.add(CommandType.MOVE_RALLY_POINT);
        rallyPointCommand.add(CommandType.PICKUP_WORKERS);
        rallyPointCommand.add(CommandType.DROP_OFF_WORKERS);
        rallyPointCommand.add(CommandType.BUILD);
    }

    public RallyPoint(CommandRelay commandRelay, Location location, Army army) {
        super(commandRelay);
        currentLocation = location;
        addAllCommands(rallyPointCommand);
        this.army=army;
    }

    public void move(Location newLocation){
        System.out.println("rally point moving location to " + newLocation);
        this.currentLocation = newLocation;
        army.move(newLocation);
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

    public void addWorkersToArmy() {
        commandRelay.addWorkersToArmy(getLocation(), army.getEntityId());
    }
}
