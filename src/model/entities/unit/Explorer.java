package model.entities.unit;

import model.entities.EntityId;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Explorer extends Unit{


    /**
     * @param playerId
     * @param id
     */
    public Explorer(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    protected IdType getIdType() {
        return IdType.explorer;
    }

    @Override
    protected Stats setEntityStats() {
        return new UnitStats(0,0,0,0,0,0,0,0);
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
