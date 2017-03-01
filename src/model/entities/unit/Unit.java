package model.entities.unit;

import model.entities.Entity;
import utilities.id.CustomID;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Unit extends Entity {



    /**
     * @param playerId
     */
    public Unit(CustomID playerId,String id) {
        super(playerId, id);
    }


    public abstract void abandonArmy();
    public abstract void joinArmy(int armyNumber);

    public void moveUnit(){

    }

    public void advanceToRallyPoint(int number){

    }


}
