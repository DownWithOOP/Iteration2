package model.entities.unit;

import controller.commands.CommandType;
import model.entities.EntityType;
import model.entities.Stats.FighterUnitStats;
import model.entities.StructureFactory;
import model.entities.structure.Structure;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by jordi on 2/24/2017.
 */
public class Colonist extends FighterUnit {
    private StructureFactory capitalFactory;
    private static ArrayList<CommandType> colonistCommand = new ArrayList<>();

    static {
        colonistCommand.add(CommandType.BUILD_CAPITAL);
    }

    public Colonist(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
//        entityCommand.add(CommandType.BUILD_CAPITAL);
        addAllCommands(colonistCommand);
        this.capitalFactory = new StructureFactory();
    }

    @Override
    protected IdType getIdType() {
        return IdType.COLONIST;
    }

    @Override
    protected Stats setEntityStats() {
        return new FighterUnitStats(0, 0, 0, 0, 0, 0, 0, 0);
    }


    @Override
    public void abandonArmy() {
        System.out.println("abandon army");
    }

    @Override
    public void joinArmy(int armyNumber) {
        System.out.println("joined army " + armyNumber);
    }

    public Structure buildCapital(CustomID customId, String id) {
        return capitalFactory.getStructure(EntityType.CAPITAL, customId, id, (int) (super.getLocation().getX()), (int) (super.getLocation().getY()));
    }

    @Override
    public void decommission() {
        System.out.println("colonist decommission");
    }

}
