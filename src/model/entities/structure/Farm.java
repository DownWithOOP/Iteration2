package model.entities.structure;

import controller.CommandRelay;
import model.entities.Stats.StaffedStructureStats;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Farm extends StaffedStructure {


    /**
     * @param playerId
     * @param id
     */
    public Farm(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
    }

    @Override
    protected IdType getIdType() {
        return IdType.FARM;
    }

    @Override
    protected Stats setEntityStats() {
        return new StaffedStructureStats(0,0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }

}
