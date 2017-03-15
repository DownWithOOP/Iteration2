package model.entities.structure;

import controller.CommandRelay;
import controller.commands.CommandType;
import model.entities.EntityType;
import model.entities.Stats.StaffedStructureStats;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import model.entities.UnitFactory;
import model.entities.unit.*;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by jordi on 2/24/2017.
 */
public class Capital extends StaffedStructure {
    private UnitFactory unitFactory;
    private StructureStats capitalStats;

    static ArrayList<CommandType> capitalCommand = new ArrayList<>();

    static {
        capitalCommand.add(CommandType.CREATE_UNIT);
    }

    public Capital(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        this.unitFactory = new UnitFactory(commandRelay);
        this.capitalStats = new StructureStats(10,100,3,2,2);
        addAllCommands(capitalCommand);
    }

    // Capital produces explorers and workers
    public void createUnit(EntityType entityType, CustomID customID) {
        if (entityType.equals(EntityType.EXPLORER) || entityType.equals(EntityType.WORKER)) {
            Unit unit = unitFactory.getEntity(entityType, customID, (int)(super.getLocation().getX()),(int) (super.getLocation().getY()));
            commandRelay.notifyModelOfUnitCreation(unit);
        }
        return;
    }

    public void healUnit(FighterUnit unitToHeal) {
        int offset = (int)Math.random()*unitToHeal.getMaxHealth();
        unitToHeal.heal(offset);
    }

    @Override
    protected IdType getIdType() {
        return IdType.CAPITAL;
    }

    @Override
    protected Stats setEntityStats() {
        return new StaffedStructureStats(0,0,0,0,0,0);
    }


    public StructureStats getCapitalStats() {
        return capitalStats;
    }


    @Override
    public void decommission() {

    }

}
