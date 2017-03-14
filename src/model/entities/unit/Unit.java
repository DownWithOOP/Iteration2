package model.entities.unit;

import controller.CommandRelay;
import controller.commands.CommandType;
import model.RenderInformation.UnitRenderInformation;
import model.RenderInformation.UnitRenderObject;
import model.common.Location;
import model.entities.Entity;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.ObserverInterfaces.MapObserver;
import utilities.ObserverInterfaces.MapSubject;
import utilities.ObserverInterfaces.UnitObserver;
import utilities.ObserverInterfaces.UnitSubject;
import utilities.id.CustomID;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Unit extends Entity implements UnitSubject, MapSubject {
    private ArrayList<Location> currentPath;

    private UnitObserver unitObserver;
    private MapObserver mapObserver;

    static ArrayList<CommandType> unitCommand= new ArrayList<>();
    static {
        unitCommand.add(CommandType.ABANDON_ARMY);
        unitCommand.add(CommandType.ADVANCE_TO_RALLY_POINT);
        unitCommand.add(CommandType.JOIN_ARMY);
        unitCommand.add(CommandType.MOVE);
    }

    /**
     * @param playerId
     */
    public Unit(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
//        entityCommand.add(CommandType.ABANDON_ARMY);
//        entityCommand.add(CommandType.ADVANCE_TO_RALLY_POINT);
//        entityCommand.add(CommandType.JOIN_ARMY);
//        entityCommand.add(CommandType.MOVE);
        addAllCommands(unitCommand);
    }


    public abstract void abandonArmy();
    public abstract void joinArmy(int armyNumber);

    public void moveUnit(int newX, int newY) {
        double oldX = getLocation().getX();
        double oldY = getLocation().getY();
        int distance = (int)(Math.sqrt(Math.pow(newX-oldX,2) + Math.pow(newY-oldY,2)));

        // check if distance is in the range of the unit's movement's stat
        if (distance <= getUnitMovement()) {
            //TODO: Modify Pathfinder class for hex tiles
            //setCurrentPath();
//            for (Location location : getCurrentPath()) {
//                //TODO: Implement move command to add locations to command queue
//                //this.addToQueue();
//            }
            //Do this to get the current render object
            super.setLocation(newX, newY);
            notifyUnitObservers();
            notifyMapObservers();

        } else {
            System.out.println("Movement distance too far");
        }

    }

    private UnitRenderObject createRenderObject(){
        UnitStats unitStats = this.getUnitStats().clone(); // deep clone so as not to mess anything up
        return new UnitRenderObject(this.getEntityId(), this.getEntityType(), (int)(this.getLocation().getX()), (int)(this.getLocation().getY()), unitStats);
    }

    public void advanceToRallyPoint(int number){

    }

    @Override
    public void registerUnitObserver(UnitObserver o) {
        this.unitObserver = o;
    }

    @Override
    public void unregister(UnitObserver o) {

    }

    @Override
    public void notifyUnitObservers() {
        unitObserver.updateUnit(getEntityId(), createRenderObject());
        System.out.println("Observer notified");
    }

    @Override
    public void registerMapObserver(MapObserver o) {
        this.mapObserver = o;
    }

    @Override
    public void unregister(MapObserver o) {

    }

    @Override
    public void notifyMapObservers() {
        mapObserver.updateUnit(getEntityId(), createRenderObject());
        System.out.println("map observer notified");
    }

    public int getUnitMovement(){
        return ((UnitStats)entityStats).getMovement();
    }
    public int getVisionRadius(){
        return entityStats.getVisionRadius();
    }

    public UnitStats getUnitStats(){
        return ((UnitStats)entityStats).clone();
    }

    public ArrayList<Location> getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(ArrayList<Location> currentPath) {
        this.currentPath = currentPath;
    }
}
