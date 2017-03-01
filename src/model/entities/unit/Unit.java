package model.entities.unit;

import controller.commands.CommandType;
import model.entities.Entity;
import utilities.id.CustomID;

import java.util.ArrayList;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Unit extends Entity {

    static ArrayList<CommandType> unitCommand= new ArrayList<>();
    static {
        unitCommand.add(CommandType.ABANDON_ARMY);
        unitCommand.add(CommandType.ADVANCE_TO_RALLY_POINT);
        unitCommand.add(CommandType.JOIN_ARMY);
    }

    /**
     * @param playerId
     */
    public Unit(CustomID playerId,String id) {
        super(playerId, id);
        addAllCommands(unitCommand);
    }


    public abstract void abandonArmy();
    public abstract void joinArmy(int armyNumber);

    public void moveUnit(){

    }

    public void advanceToRallyPoint(int number){

    }


}
