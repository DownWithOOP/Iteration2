package model.entities.structure;

import controller.CommandRelay;
import model.entities.EntityType;
import model.entities.Stats.FortStats;
import model.entities.UnitFactory;
import model.entities.unit.Unit;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Fort extends StaffedStructure {
    private UnitFactory unitFactory;

    public Fort(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        unitFactory = new UnitFactory(commandRelay);
    }

    // Fort creates soldiers (Melee and Unit)
    public Unit createUnit(EntityType entityType, CustomID customID) {
        if (entityType.equals(EntityType.MELEE) || entityType.equals(entityType.RANGED)) {
            return unitFactory.getEntity(entityType, customID, (int)(super.getLocation().getX()),(int)(super.getLocation().getY()));
        }
        return null;
    }

    @Override
    protected IdType getIdType() {
        return IdType.FORT;
    }

    @Override
    protected Stats setEntityStats() {
        return new FortStats(0,0,0,0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }

}
