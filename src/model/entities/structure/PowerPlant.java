package model.entities.structure;

import model.entities.EntityId;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class PowerPlant extends Structure {

    /**
     * @param playerId
     * @param id
     */
    public PowerPlant(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    protected IdType getIdType() {
        return IdType.powerPlant;
    }

    @Override
    protected Stats setEntityStats() {
        return new StructureStats(0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }

}
