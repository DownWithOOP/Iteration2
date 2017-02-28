package model.entities.unit;

import model.entities.Entity;
import model.entities.EntityType;
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

    abstract void abandonArmy();
    abstract void joinArmy();

    public void moveUnit(){

    }


}
