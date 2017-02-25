package model.entities.unit;

import model.entities.Entity;
import utilities.id.CustomID;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Unit extends Entity {
    Army army;

    /**
     * @param playerId
     */
    public Unit(CustomID playerId) {
        super(playerId);
    }

    abstract void abandonArmy();
    abstract void joinArmy();

    public void moveUnit(){

    }

    @Override
    public void decommission(){
        abandonArmy();
    }

}
