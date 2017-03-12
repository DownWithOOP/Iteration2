package model.entities.structure;

import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import model.map.tile.resources.Resource;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class StaffedStructure extends Structure {

    private Resource foodResource;

    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public StaffedStructure(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
    }

    @Override
    protected IdType getIdType() {
        return null;
    }

    @Override
    protected Stats setEntityStats() {
        return new StructureStats(0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }
}
