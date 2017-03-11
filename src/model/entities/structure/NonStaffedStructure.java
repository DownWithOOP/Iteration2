package model.entities.structure;

import model.entities.Stats.NonStaffedStructureStats;
import model.entities.Stats.Stats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class NonStaffedStructure extends Structure {
    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public NonStaffedStructure(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
    }

    @Override
    protected IdType getIdType() {
        return null;
    }

    @Override
    protected Stats setEntityStats() {
        return new NonStaffedStructureStats(0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }
}
