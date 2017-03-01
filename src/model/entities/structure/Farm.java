package model.entities.structure;

import model.entities.EntityId;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Farm extends Structure {


    /**
     * @param playerId
     * @param id
     */
    public Farm(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    protected IdType getIdType() {
        return IdType.farm;
    }

    @Override
    protected Stats setEntityStats() {
        return new StructureStats(0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }

}
