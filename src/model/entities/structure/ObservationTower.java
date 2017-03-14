package model.entities.structure;

import controller.CommandRelay;
import model.entities.Stats.NonStaffedStructureStats;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class ObservationTower extends NonStaffedStructure {

    /**
     * @param playerId
     * @param id
     */
    public ObservationTower(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
    }

    @Override
    protected IdType getIdType() {
        return IdType.OBSERVATION_TOWER;
    }

    @Override
    protected Stats setEntityStats() {
        return new NonStaffedStructureStats(0,0,0,0,0);

    }


    @Override
    public void decommission() {

    }


}
