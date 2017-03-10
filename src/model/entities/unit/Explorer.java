package model.entities.unit;

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
    public Explorer(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
    }

    @Override
    protected IdType getIdType() {
        return IdType.EXPLORER;
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
