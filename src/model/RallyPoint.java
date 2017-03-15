package model;

import controller.CommandRelay;
import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import controller.commands.Direction;
import model.common.Location;
import model.entities.EntityType;
import model.entities.StructureFactory;
import model.entities.structure.StaffedStructure;
import model.entities.structure.Structure;
import model.entities.unit.Army;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Jonathen on 2/25/2017.
 */
public class RallyPoint extends Commandable {
    private Queue<Location> path= new ArrayDeque<>();
    private Location currentLocation;
    private Army army;
    private StructureFactory structureFactory;

    static ArrayList<CommandType> rallyPointCommand= new ArrayList<>();
    static {
        rallyPointCommand.add(CommandType.FOCUS);
        rallyPointCommand.add(CommandType.MOVE_RALLY_POINT);
        rallyPointCommand.add(CommandType.PICKUP_WORKERS);
        rallyPointCommand.add(CommandType.DROP_OFF_WORKERS);
        rallyPointCommand.add(CommandType.BUILD_STRUCTURE);
    }

    public RallyPoint(CommandRelay commandRelay, Location location, Army army) {
        super(commandRelay);
        currentLocation = location;
        addAllCommands(rallyPointCommand);
        this.army=army;
        this.structureFactory = new StructureFactory(commandRelay);
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

    public void buildStructure(EntityType entityType) {
        System.out.println("rp building structure");
        Structure structureCreated = null;
        if (!entityType.equals(EntityType.CAPITAL) && army.hasWorkers()) {
            structureCreated = structureFactory.getStructure(entityType, army.getPlayerId(), getLocation().getXCoord(),getLocation().getYCoord());
            if (entityType != EntityType.OBSERVATION_TOWER) {
                ((StaffedStructure) structureCreated).addWorkers(army.getWorkers());
                System.out.println("structure's workers " + ((StaffedStructure) structureCreated).getWorkers());
            }
        }
        if (structureCreated != null) {
            commandRelay.notifyModelOfStructureCreation(structureCreated);
        }
    }
}
