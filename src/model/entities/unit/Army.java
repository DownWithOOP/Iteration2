package model.entities.unit;

import controller.CommandRelay;
import controller.commands.CommandType;
import controller.commands.Direction;
import model.RallyPoint;
import model.common.Location;
import model.entities.Entity;
import model.entities.EntityId;
import model.entities.Fighter;
import model.entities.Stats.FighterUnitStats;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jordi on 2/24/2017.
 */
//TODO: Tailor some of these methods for when a worker unit joins an army
public class Army extends Entity implements Fighter {

    HashMap<EntityId, Unit> reinforcements = new HashMap<>();
    HashMap<EntityId, Unit> battleGroup = new HashMap<>();

    //TODO do we need this?
    Queue<Location> pathQueue = new LinkedBlockingQueue<>();

    private RallyPoint rallyPoint;

    static ArrayList<CommandType> armyCommand = new ArrayList<>();

    static {
        armyCommand.add(CommandType.DEFEND);
        armyCommand.add(CommandType.ATTACK);
        armyCommand.add(CommandType.DISBAND);

    }

    //TODO initialize rally point
    public Army(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        addAllCommands(armyCommand);
        rallyPoint = new RallyPoint(commandRelay, getLocation(),this);
    }

    @Override
    public void attack(Direction direction) {
       System.out.println("attack " + direction.toString());

       commandRelay.notifyModelOfAttack(getLocation().move(direction), ((FighterUnitStats)entityStats).getOffensiveDamage());
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
        return IdType.ARMY;
    }

    @Override
    protected Stats setEntityStats() {
        return new FighterUnitStats(0, 0, 0, 0, 0, 0, 0, 0);
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

    //TODO maeby make different register functions for fighters and workers
    public void registerUnit(Unit unit) {
        EntityId unitId = unit.getEntityId();
        FighterUnitStats unitStats = (FighterUnitStats) unit.getUnitStats();
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
        FighterUnitStats unitStats = (FighterUnitStats) unit.getUnitStats();

        int attack = unitStats.getOffensiveDamage() * (-1);

        int defense = unitStats.getDefensiveDamage() * (-1);

        int upKeep = unitStats.getUpkeep() * (-1);

        int health = unitStats.getHealth() * (-1);

        if (battleGroup.containsKey(unitId)) {
            battleGroup.remove(unitId);
            setBattleGroupStats(attack, defense, health, upKeep);

        }
        else if (reinforcements.containsKey(unitId)) {
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
        int currentAttackPower = ((FighterUnitStats) entityStats).getOffensiveDamage();
        currentAttackPower += offensiveDmg;
        ((FighterUnitStats) entityStats).setOffensiveDamage(currentAttackPower);
    }

    private void setBattleGroupDefensePower(int defensiveDmg) {
        int currentDefensivePower = ((FighterUnitStats) entityStats).getDefensiveDamage();
        currentDefensivePower += defensiveDmg;
        ((FighterUnitStats) entityStats).setDefensiveDamage(currentDefensivePower);
    }

    private void setBattleGroupHealth(int health) {
       // int currentHealth = entityStats.getHealth();
       // currentHealth += health;
       // entityStats.setHealth(currentHealth);
    }

    private void setBattleGroupMovementSpeed() {
        int slowestSpeed = Integer.MAX_VALUE;
        for (Unit unit : battleGroup.values()) {
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
        for (Unit unit : battleGroup.values()) {
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

    //Move each unit one tile
    private void moveBattleGroup(Location nextTile) {
        for (Unit battleGroupMember : battleGroup.values()) {
            battleGroupMember.moveUnit(nextTile.getXCoord(),nextTile.getYCoord());
        }

    }

    //TODO do we need this to be private if we're using updatePathQueue? if not, make it private and make movement controlled by path queue
    public void changeTargetLocation() {

    }

    private void changeReinforcementsLocation(Queue<Location> pathToRallyPoint) {
        Queue<Location> copyOfPath = new LinkedBlockingQueue<>(pathToRallyPoint);
        Location locationToMoveTo = null;

        //Keep polling until locationToMoveTo holds the last location in queue--that location is the location we want to move to
        while (!copyOfPath.isEmpty()){
            locationToMoveTo = copyOfPath.poll();
        }
        moveReinforcements(locationToMoveTo);

    }

    //Called by RallyPoint to let army know that its RallyPoint has moved
    public void updatePathQueue(Queue<Location> newPathQueue) {
        pathQueue = newPathQueue;
        //TODO changeTargetLocation() too?
        //Notify reinforcements that the rally point location has changed
        changeReinforcementsLocation(newPathQueue);
    }

    private void moveReinforcements(Location newLocation) {
        for (Unit reinforcementUnit : reinforcements.values()) {
            reinforcementUnit.moveUnit(newLocation.getXCoord(),newLocation.getYCoord());
        }
    }

    //TODO is this input arg right?
    public void arrivedRallyPoint(FighterUnit fighterUnit){
        if (this.playerId == fighterUnit.getPlayerId()) {
            reinforcements.remove(fighterUnit.getEntityId());
            battleGroup.put(fighterUnit.getEntityId(),fighterUnit);
            setBattleGroupStats(fighterUnit.getFighterUnitStats().getOffensiveDamage(),
                    fighterUnit.getFighterUnitStats().getDefensiveDamage(),
                    fighterUnit.getFighterUnitStats().getHealth(),
                    fighterUnit.getUnitStats().getUpkeep());
        }
    }


}
