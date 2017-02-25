package model.entities.unit;

import model.entities.Entity;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Unit extends Entity {
    abstract void abandonArmy();
    abstract void joinArmy();

    public void moveUnit(){

    }


}
