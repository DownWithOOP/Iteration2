package model.entities.unit;

import model.entities.Stats.FighterUnitStats;
import model.entities.Stats.Stats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class FighterUnit extends Unit {
    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public FighterUnit(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
    }

    @Override
    public void abandonArmy() {

    }

    @Override
    public void joinArmy(int armyNumber) {

    }

    @Override
    protected IdType getIdType() {
        return null;
    }

    @Override
    protected Stats setEntityStats() {
        return new FighterUnitStats(0,0,0,0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }
}
