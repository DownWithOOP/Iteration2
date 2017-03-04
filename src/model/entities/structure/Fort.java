package model.entities.structure;

import model.entities.EntityType;
import model.entities.UnitFactory;
import model.entities.unit.Unit;

/**
 * Created by jordi on 2/24/2017.
 */
public class Fort extends Structure {
    private UnitFactory unitFactory;

    public Fort() {
        super(EntityType.FORT);
        unitFactory = new UnitFactory();
    }

    // Fort creates soldiers (Melee and Unit)
    public Unit createUnit(EntityType entityType) {
        if (entityType.equals(EntityType.MELEE) || entityType.equals(entityType.RANGED)) {
            return unitFactory.getEntity(entityType);
        }
        return null;
    }
}
