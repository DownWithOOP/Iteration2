package model.entities.unit;

import controller.CommandRelay;
import controller.commands.CommandType;
import model.entities.Stats.FighterUnitStats;
import model.entities.Stats.Stats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by jordi on 2/24/2017.
 */
public class FighterUnit extends Unit {
    static ArrayList<CommandType> fighterUnitCommand= new ArrayList<>();

    static {
        fighterUnitCommand.add(CommandType.ATTACK);
        fighterUnitCommand.add(CommandType.DEFEND);
    }
    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public FighterUnit(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
//        entityCommand.add(CommandType.ATTACK);
//        entityCommand.add(CommandType.DEFEND);
        addAllCommands(fighterUnitCommand);
    }

    public void takeDamage(int offensiveDamage) {
        System.out.println("taking damage " + offensiveDamage);
        int currentHealth = getFighterUnitStats().getHealth();
        int damageTaken = offensiveDamage - getFighterUnitStats().getArmor();
        if (currentHealth - damageTaken <= 0) {
            //TODO: unit is dead - notify entity ownership?
        }
        else {
            getFighterUnitStats().setHealth(currentHealth - damageTaken);
        }
        System.out.println(getFighterUnitStats().getHealth());
    }


    public void heal(int offset) {
        int currentHealth = getFighterUnitStats().getHealth();
        if(currentHealth + offset > getMaxHealth()) {
            getFighterUnitStats().setHealth(getMaxHealth());
        }
        else {
            getFighterUnitStats().setHealth(getFighterUnitStats().getHealth() + offset);
        }
    }

    @Override
    public void abandonArmy() {

    }

    @Override
    public void joinArmy(int armyNumber) {
        commandRelay.notifyModelOfUnitJoiningArmy(this, armyNumber);
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

    public int getMaxHealth() {
        return getFighterUnitStats().getMaxHealth();
    }

    public FighterUnitStats getFighterUnitStats() { return ((FighterUnitStats)entityStats).clone();}
}
