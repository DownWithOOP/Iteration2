package model.entities.unit;

import controller.commands.CommandType;
import controller.commands.Direction;
import model.RallyPoint;
import model.entities.Entity;
import model.entities.EntityId;
import model.entities.Fighter;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jordi on 2/24/2017.
 */
public class Army extends Entity implements Fighter {

    HashMap<EntityId, Unit> reinforcements = new HashMap<>();
    HashMap<EntityId, Unit> battleGroup = new HashMap<>();
    private RallyPoint rallyPoint;


    static ArrayList<CommandType> armyCommand = new ArrayList<>();

    static {
        armyCommand.add(CommandType.DEFEND);
        armyCommand.add(CommandType.ATTACK);
        armyCommand.add(CommandType.DISBAND);

    }

    public Army(CustomID playerId, String id) {
        super(playerId, id);
        addAllCommands(armyCommand);
    }

    @Override
    public void attack(Direction direction) {
        System.out.println("attack " + direction.toString());
    }

    @Override
    public void defend(Direction direction) {
        System.out.println("defend " + direction.toString());
    }


    @Override
    public void decommission() {
        System.out.println("army decommission");
    }


    //TODO: change this method
    @Override
    protected IdType getIdType() {
        return IdType.army;
    }

    @Override
    protected Stats setEntityStats() {
        return new UnitStats(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public void disband() {
        for (Unit unit :
                reinforcements.values()) {
            unit.abandonArmy();
        }
        for (Unit unit :
                battleGroup.values()) {
            unit.abandonArmy();
        }

    }


    public void registerUnit(Unit unit) {
        EntityId unitId = unit.getEntityId();
        UnitStats unitStats = unit.getUnitStats();
        int attack = unitStats.getOffensiveDamage();

        int defense = unitStats.getDefensiveDamage();

        int upKeep = unitStats.getUpkeep();

        int health = unitStats.getHealth();

        if (this.playerId.equals(unit.getPlayerId())) {
            if (rallyPoint.getLocation().equals(unit.getLocation())) {
                battleGroup.put(unitId, unit);
                setBattleGroupStats(attack, defense, health, upKeep);
            } else {
                reinforcements.put(unitId, unit);
                //TODO: send the coordinates of the rally point to the unit
            }
        }
    }

    public void removeUnit(Unit unit) {
        EntityId unitId = unit.getEntityId();
        UnitStats unitStats = unit.getUnitStats();

        int attack = unitStats.getOffensiveDamage() * (-1);

        int defense = unitStats.getDefensiveDamage() * (-1);

        int upKeep = unitStats.getUpkeep() * (-1);

        int health = unitStats.getHealth() * (-1);

        if (battleGroup.containsKey(unitId)) {
            battleGroup.remove(unitId);
            setBattleGroupStats(attack, defense, health, upKeep);

        }
        if (reinforcements.containsKey(unitId)) {
            reinforcements.remove(unitId);
        }

    }


    private void setBattleGroupStats(int attack, int defense, int health, int upkeep) {
        setBattleGroupAttackPower(attack);
        setBattleGroupDefensePower(defense);
        setBattleGroupMovementSpeed();
        setBattleGroupHealth(health);
        setBattleGroupUpkeep(upkeep);
        setBattleGroupVisionRadius();
    }

    private void setBattleGroupUpkeep(int upkeep) {
        int currentUpkeep = entityStats.getUpkeep();
        currentUpkeep += upkeep;
        entityStats.setUpkeep(currentUpkeep + upkeep);
    }

    private void setBattleGroupAttackPower(int offensiveDmg) {
        int currentAttackPower = ((UnitStats) entityStats).getOffensiveDamage();
        currentAttackPower += offensiveDmg;
        ((UnitStats) entityStats).setOffensiveDamage(currentAttackPower);
    }

    private void setBattleGroupDefensePower(int defensiveDmg) {
        int currentDefensivePower = ((UnitStats) entityStats).getDefensiveDamage();
        currentDefensivePower += defensiveDmg;
        ((UnitStats) entityStats).setDefensiveDamage(currentDefensivePower);
    }

    private void setBattleGroupHealth(int health) {
        int currentHealth = entityStats.getHealth();
        currentHealth += health;
        entityStats.setHealth(currentHealth);
    }

    private void setBattleGroupMovementSpeed() {
        int slowestSpeed = Integer.MAX_VALUE;
        for (Unit unit :
                battleGroup.values()) {
            int temp = unit.getUnitMovement();
            if (temp < slowestSpeed) {
                ((UnitStats) entityStats).setMovement(temp);
            }
        }
        if (battleGroup.isEmpty()) {
            ((UnitStats) entityStats).setMovement(0);
        }
    }

    private void setBattleGroupVisionRadius() {
        int currentVisionRadius = 0;
        for (Unit unit :
                battleGroup.values()) {
            int temp = unit.getVisionRadius();
            if (temp > currentVisionRadius) {
                entityStats.setVisionRadius(temp);
            }
        }
        if (battleGroup.isEmpty()) {
            entityStats.setVisionRadius(0);
        }
    }


    public void waitTurn() {

    }

    private void moveBattleGroup() {

    }

    public void changeTargetLocation() {

    }

    private void changeReinforcementsLocation() {

    }





}
