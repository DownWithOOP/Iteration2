package model.entities.unit;

import model.entities.EntityType;
import model.entities.StructureFactory;
import model.entities.structure.Structure;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Colonist extends Unit{
    private StructureFactory capitalFactory;

    public Colonist(CustomID playerId, String id) {
        super(playerId, id);
        this.capitalFactory = new StructureFactory();
    }

    @Override
    protected IdType getIdType() {
        return IdType.COLONIST;
    }

    @Override
    protected Stats setEntityStats() {
        return new UnitStats(0,0,0,0,0,0,0,0);
    }


    @Override
    public void abandonArmy() {
        System.out.println("abandon army");
    }

    @Override
    public void joinArmy(int armyNumber) {
        System.out.println("joined army "+ armyNumber);
    }

    public Structure buildCapital(CustomID customId, String id) {
        return capitalFactory.getStructure(EntityType.CAPITAL, customId, id);
    }

    @Override
    public void decommission() {
        System.out.println("colonist decommission");
    }

}
