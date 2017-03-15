package model.entities.unit;

import controller.CommandRelay;
import controller.commands.CommandType;
import model.common.Location;
import model.entities.Stats.FighterUnitStats;
import model.entities.Stats.PassiveUnitStats;
import utilities.id.CustomID;
import model.entities.EntityType;
import model.entities.StructureFactory;
import model.entities.structure.Structure;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by jordi on 2/24/2017.
 */
public class Worker extends PassiveUnit {
    private StructureFactory structureFactory;

    static ArrayList<CommandType> workerCommand= new ArrayList<>();

    static {
        workerCommand.add(CommandType.BUILD_STRUCTURE);
    }

    public Worker(CommandRelay commandRelay, CustomID playerId, String id, int startingX, int startingY) {
        super(commandRelay, playerId, id, startingX, startingY);
        addAllCommands(workerCommand);
        this.structureFactory = new StructureFactory(commandRelay);
    }

    @Override
    protected IdType getIdType() {
        return IdType.WORKER;
    }

    //todo:this should not have attacking, defensive damage, range, only fighters have these
    @Override
    protected Stats setEntityStats() {
        return new PassiveUnitStats(5,0,0);
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

    //Army needs to update the location of workers
    public void updateLocation(Location location) {
        setLocation(location.getXCoord(), location.getYCoord());

    }

}
