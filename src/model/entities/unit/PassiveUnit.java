package model.entities.unit;

import controller.CommandRelay;
import model.entities.Stats.FighterUnitStats;
import model.entities.Stats.PassiveUnitStats;
import model.entities.Stats.Stats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class PassiveUnit extends Unit {
    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public PassiveUnit(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
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
