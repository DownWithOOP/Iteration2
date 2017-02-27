package model.entities.unit;

import model.entities.Entity;
import model.entities.EntityType;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Unit extends Entity {
    public Unit(EntityType entityType) {
        super(entityType);
    }

    abstract void abandonArmy();
    abstract void joinArmy();

    public void moveUnit(){

    }


}
