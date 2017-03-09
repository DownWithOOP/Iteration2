package model.entities.unit;

import controller.commands.CommandType;
import model.entities.Stats.FighterUnitStats;
import utilities.id.CustomID;
import model.entities.EntityType;
import model.entities.StructureFactory;
import model.entities.structure.Structure;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Worker extends PassiveUnit {
    private StructureFactory structureFactory;

//    static {
//        entityCommand.add(CommandType.BUILD_STRUCTURE);
//    }

    public Worker(CustomID playerId, String id, int startingX, int startingY) {
        super(playerId, id, startingX, startingY);
        entityCommand.add(CommandType.BUILD_STRUCTURE);
        this.structureFactory = new StructureFactory();
    }

    @Override
    protected IdType getIdType() {
        return IdType.WORKER;
    }

    //todo:this should not have attacking, defensive damage, range, only fighters have these
    @Override
    protected Stats setEntityStats() {
        return new FighterUnitStats(0,0,0,0,0,0,0,0);
    }

    // Worker can build all structures except capital
    public Structure buildStructure(EntityType entityType, CustomID customID, String id) {
        if (!entityType.equals(EntityType.CAPITAL)) {
            return structureFactory.getStructure(entityType, customID, id, (int)(super.getLocation().getX()),(int)(super.getLocation().getY()));
        }
        return null;
    }

    @Override
    public void abandonArmy() {

    }

    @Override
    public void joinArmy(int armyNumber) {

    }


    @Override
    public void decommission() {

    }

}
