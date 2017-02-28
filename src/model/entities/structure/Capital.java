package model.entities.structure;

import model.entities.EntityType;
import model.entities.Stats.StructureStats;
import model.entities.unit.*;
import utilities.id.CustomID;

/**
 * Created by jordi on 2/24/2017.
 */
public class Capital extends Structure {
    private StructureStats capitalStats;

    /**
     * @param playerId
     */
    public Capital(CustomID playerId, String id) {
        super(playerId, id);
        this.capitalStats = new StructureStats(10,100,3,2,2);
    }

    @Override
    protected CustomID setId(String id) {
        return null;
    }

//    public Capital() {
//        super(EntityType.CAPITAL);
//    }
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



    @Override
    public void decommission() {

    }

    @Override
    public void decomission() {

    }
}
