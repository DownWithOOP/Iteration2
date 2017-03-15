package model.entities.unit;

import controller.CommandRelay;
import model.entities.Stats.FighterUnitStats;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Melee extends FighterUnit{


    /**
     * @param playerId
     * @param id
     */
    public Melee(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
    }

    @Override
    protected IdType getIdType() {
        return IdType.MELEE;
    }

    @Override
    protected Stats setEntityStats() {
        return new FighterUnitStats(20,0,0,100,0,5,0,0);
    }

    @Override
    public void abandonArmy() {

    }

    @Override
    public void decommission() {
        System.out.println("melee decommission");
    }




}
