package model.cycling.type;

import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import model.entities.unit.Army;
import utilities.id.CustomID;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class ArmyType extends Type{

    private Map<CustomID, Army> armies = new TreeMap<>();

    @Override
    public Commandable getCurrentInstance() {
        return null;
    }

    @Override
    public CommandType getCurrentCommand() {
        return null;
    }

    public void addArmy(Army newArmy) {
        armies.put(newArmy.getPlayerId(), newArmy);
    }
}
