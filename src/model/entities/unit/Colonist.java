package model.entities.unit;

import controller.CommandRelay;
import controller.commands.Command;
import controller.commands.CommandType;
import model.entities.EntityType;
import model.entities.Stats.FighterUnitStats;
import model.entities.StructureFactory;
import model.entities.structure.Capital;
import model.entities.structure.Structure;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jordi on 2/24/2017.
 */
public class Colonist extends FighterUnit {
    private StructureFactory capitalFactory;

    private static ArrayList<CommandType> colonistCommand= new ArrayList<>();
    static {
        colonistCommand.add(CommandType.BUILD_CAPITAL);
    }

    public Colonist(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
//        entityCommand.add(CommandType.BUILD_CAPITAL);
        addAllCommands(colonistCommand);
        this.capitalFactory = new StructureFactory(commandRelay);
    }

    @Override
    protected IdType getIdType() {
        return IdType.COLONIST;
    }

    @Override
    protected Stats setEntityStats() {
        return new FighterUnitStats(1,0,0,100,0,5,0,0);
    }


    @Override
    public void abandonArmy() {
        System.out.println("abandon army");
    }

    public void buildCapital(CustomID customId, String id) {
        System.out.println("BUILDING CAPITAL...");
        Structure capital = capitalFactory.getStructure(EntityType.CAPITAL, customId, id,(int)(super.getLocation().getX()),(int)(super.getLocation().getY()));
        commandRelay.notifyModelOfStructureCreation(capital);
    }

    @Override
    public void decommission() {
        System.out.println("colonist decommission");
    }

}
