package model.entities.unit;


import controller.commands.CommandType;
import model.entities.Fighter;
import model.entities.Stats.FighterUnitStats;

import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jordi on 2/24/2017.
 */
public class Explorer extends FighterUnit {

    static ArrayList<CommandType> explorerCommand = new ArrayList<>();

    static {
        explorerCommand.add(CommandType.PROSPECT);
    }

    /**
     * @param playerId
     * @param id
     */
    public Explorer(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
        addAllCommands(explorerCommand);
    }

    public void prospect() {

    }

    @Override
    protected IdType getIdType() {
        return IdType.EXPLORER;
    }

    @Override
    protected Stats setEntityStats() {
        return new FighterUnitStats(0, 0, 0, 0, 0, 0, 0, 0);
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
