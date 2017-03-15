package model.entities.structure;

import controller.CommandRelay;
import model.entities.EntityType;
import model.entities.Stats.StaffedStructureStats;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import model.entities.UnitFactory;
import model.entities.unit.*;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Capital extends StaffedStructure {
    private UnitFactory unitFactory;
    private StructureStats capitalStats;

    public Capital(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        this.unitFactory = new UnitFactory(commandRelay);
        this.capitalStats = new StructureStats(10,100,3,2,2);
    }

    // Capital produces explorers and workers
    public Unit createUnit(EntityType entityType, CustomID customID, String id) {
        if (entityType.equals(EntityType.EXPLORER) || entityType.equals(EntityType.WORKER)) {
            return unitFactory.getEntity(entityType, customID, id, (int)(super.getLocation().getX()),(int) (super.getLocation().getY()));
        }
        return null;
    }

    public void healUnit(FighterUnit unitToHeal) {
        int offset = (int)Math.random()*unitToHeal.getMaxHealth();
        unitToHeal.heal(offset);
    }

    @Override
    protected IdType getIdType() {
        return IdType.CAPITAL;
    }

    @Override
    protected Stats setEntityStats() {
        return new StaffedStructureStats(0,0,0,0,0,0);
    }


    public StructureStats getCapitalStats() {
        return capitalStats;
    }


    @Override
    public void decommission() {

    }

}
