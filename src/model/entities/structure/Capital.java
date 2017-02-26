package model.entities.structure;

import model.entities.EntityType;
import model.entities.Stats.StructureStats;
import model.entities.unit.*;

/**
 * Created by jordi on 2/24/2017.
 */
public class Capital extends Structure {
    private StructureStats capitalStats;

    public Capital() {
        super(EntityType.CAPITAL);
        this.capitalStats = new StructureStats(10,100,3,2,2);
    }
//TODO: Consider using a factory to create units
//    public Unit createUnit(EntityType entityType, boolean isReinforcement) {
//        Unit newUnit;
//        if (entityType.equals(EntityType.COLONIST)) {
//            newUnit = new Colonist();
//        }
//        else if (entityType.equals(EntityType.EXPLORER)) {
//            newUnit = new Explorer();
//        }
//        else if (entityType.equals(EntityType.MELEE)) {
//            newUnit = new Melee();
//        }
//        else if (entityType.equals(EntityType.RANGED)) {
//            newUnit = new Colonist();
//        }
//        else if (entityType.equals(EntityType.WORKER)) {
//            newUnit = new Worker();
//        }
//    }

    public void healUnit(Unit unitToHeal) {
//        int currentHealth = unitToHeal.getHealth();
//        unitToHeal.heal(currentHealth)
    }

    public StructureStats getCapitalStats() {
        return capitalStats;
    }
}
