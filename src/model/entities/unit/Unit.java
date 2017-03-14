package model.entities.unit;

import controller.commands.CommandType;
import model.common.Location;
import model.entities.Entity;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;

import java.util.ArrayList;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Unit extends Entity {
    private ArrayList<Location> currentPath;

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
    public Unit(CustomID playerId,String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
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
            for (Location location : getCurrentPath()) {
                //TODO: Implement move command to add locations to command queue
                //this.addToQueue();
            }
        }
    }

    public void advanceToRallyPoint(int number){

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
