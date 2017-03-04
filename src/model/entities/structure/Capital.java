package model.entities.structure;

import model.entities.EntityType;
import model.entities.Stats.StructureStats;
import model.entities.UnitFactory;
import model.entities.unit.*;

/**
 * Created by jordi on 2/24/2017.
 */
public class Capital extends Structure {
    private UnitFactory unitFactory;
    private StructureStats capitalStats;

    public Capital() {
        super(EntityType.CAPITAL);
        this.unitFactory = unitFactory;
        this.capitalStats = new StructureStats(10,100,3,2,2);
    }

    // Capital produces explorers and workers
    public Unit createUnit(EntityType entityType) {
        if (entityType.equals(EntityType.EXPLORER) || entityType.equals(EntityType.WORKER)) {
            return unitFactory.getEntity(entityType);
        }
        return null;
    }

    public void healUnit(Unit unitToHeal) {
//        int currentHealth = unitToHeal.getHealth();
//        unitToHeal.heal(currentHealth)
    }

    public StructureStats getCapitalStats() {
        return capitalStats;
    }
}
