package model.entities.structure;

import model.entities.EntityType;
import model.entities.UnitFactory;
import model.entities.unit.Unit;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Fort extends Structure {
    private UnitFactory unitFactory;

    public Fort(CustomID playerId, String id) {
        super(playerId, id);
        unitFactory = new UnitFactory();
    }

    // Fort creates soldiers (Melee and Unit)
    public Unit createUnit(EntityType entityType, CustomID customID, String id) {
        if (entityType.equals(EntityType.MELEE) || entityType.equals(entityType.RANGED)) {
            return unitFactory.getEntity(entityType, customID, id);
        }
        return null;
    }

    @Override
    protected IdType getIdType() {
        return IdType.fort;
    }

    @Override
    protected Stats setEntityStats() {
        return new StructureStats(0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }

}
