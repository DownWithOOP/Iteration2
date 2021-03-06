package model.entities.unit;

import controller.CommandRelay;
import controller.commands.CommandType;
import controller.commands.Direction;
import controller.commands.entitycommand.unitcommand.MoveUnitCommand;
import model.RallyPoint;
import model.common.Location;
import model.entities.Entity;
import model.entities.EntityId;
import model.entities.Fighter;
import model.entities.Stats.FighterUnitStats;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import model.map.tile.resources.Resource;
import model.map.tile.resources.ResourceType;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.*;
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

    Resource foodResource;

    private boolean hasWorkers;

    static ArrayList<CommandType> armyCommand = new ArrayList<>();

    static {
        armyCommand.add(CommandType.DEFEND);
        armyCommand.add(CommandType.ATTACK);
        armyCommand.add(CommandType.DISBAND);

    }

    public Army(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        addAllCommands(armyCommand);
        foodResource = new Resource(ResourceType.FOOD, 0);
        hasWorkers = false;
    }

    /**
     * Resource consumption
     */
    @Override
    public void receiveResource(Resource resource) {
        if(resource.getResourceType().equals(ResourceType.FOOD)){
            foodResource.addResource(resource.getLevel());
        }
    }

    @Override
    public void consumeResources() {
        foodResource.consumeResource(0.10);
    }

    /**
     * Army commands
     * @param direction
     */
    @Override
    public void attack(Direction direction) {
        Location attackLocation = new Location(getLocation().getXCoord(), getLocation().getYCoord());
        System.out.println("army init attacking location " + attackLocation);
        switch (direction) {
            case NORTH_EAST:
                attackLocation.moveEast();
                attackLocation.moveNorth();
                break;
            case NORTH:
                attackLocation.moveNorth();
                break;
            case NORTH_WEST:
                attackLocation.moveNorth();
                attackLocation.moveWest();
                break;
            case SOUTH_EAST:
                attackLocation.moveSouth();
                attackLocation.moveEast();
                break;
            case SOUTH:
                attackLocation.moveSouth();
                break;
            case SOUTH_WEST:
                attackLocation.moveSouth();
                attackLocation.moveWest();
                break;
        }
        System.out.println("army attacking location " + attackLocation);
        commandRelay.notifyModelOfAttack(attackLocation, ((FighterUnitStats)entityStats).getOffensiveDamage());
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
        System.out.println("registering unit " + unit.getEntityId());
        EntityId unitId = unit.getEntityId();
        FighterUnitStats unitStats = (FighterUnitStats) unit.getUnitStats();
        int attack = unitStats.getOffensiveDamage();

        int defense = unitStats.getDefensiveDamage();

        int upKeep = unitStats.getUpkeep();

        int health = unitStats.getHealth();

        if (this.playerId.equals(unit.getPlayerId())) {
            //handle the first unit added; that is when rally point is created
            if (battleGroup.isEmpty() && reinforcements.isEmpty()) {
                battleGroup.put(unitId, unit);
                setBattleGroupStats(attack, defense, health, upKeep);
                setLocation(unit.getLocation().getXCoord(), unit.getLocation().getYCoord());
                rallyPoint = new RallyPoint(commandRelay, unit.getLocation(),this);
                commandRelay.notifyModelOfRallyPointCreation(rallyPoint, Integer.parseInt(getEntityId().getId()));
            }
            else if (rallyPoint.getLocation().equals(unit.getLocation())) {
                battleGroup.put(unitId, unit);
                setBattleGroupStats(attack, defense, health, upKeep);
            }
            else {
                reinforcements.put(unitId, unit);
                unit.addToQueue(new MoveUnitCommand(unit, rallyPoint.getLocation().getXCoord(), rallyPoint.getLocation().getYCoord()));
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
        System.out.println("bg off dmg " +((FighterUnitStats) entityStats).getOffensiveDamage());
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
        System.out.println("update bg movement to " + ((UnitStats) entityStats).getMovement());
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
    private void moveBattleGroup(Location newLocation) {
        for (Unit battleGroupMember : battleGroup.values()) {
            battleGroupMember.addToQueue(new MoveUnitCommand(battleGroupMember, newLocation.getXCoord(), newLocation.getYCoord()));
        }

    }

    //TODO do we need this to be private if we're using updatePathQueue? if not, make it private and make movement controlled by path queue
    //TODO do this if we have path finding
    public void changeTargetLocation() {

    }

    //TODO do this if we have path finding
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
            reinforcementUnit.addToQueue(new MoveUnitCommand(reinforcementUnit, newLocation.getXCoord(), newLocation.getYCoord()));
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


    public List<List<Entity>> getSubModeLists() {
        List<List<Entity>> subModeLists = new ArrayList<>();
        subModeLists.add(0,getWholeArmy());
        subModeLists.add(1,getBattleGroup());
        subModeLists.add(2,getReinforcements());

        return subModeLists;
    }

    private List<Entity> getWholeArmy() {
        List<Entity> reinforcements = getReinforcements();
        List<Entity> battleGroup = getBattleGroup();
        battleGroup.addAll(reinforcements);
        return battleGroup;
    }

    public List<Entity> getReinforcements(){
        return getList(reinforcements);
    }

    public List<Entity> getBattleGroup(){
        return getList(battleGroup);
    }

    public List<Entity> getList(HashMap<EntityId, Unit>map){
        List<Entity> temp= new ArrayList<>();
         for (Entity entity: map.values()) {
             temp.add(entity);
         }
       return temp;
     }

    public void move(Location newLocation) {
        if (distanceFromCurrentLocation(newLocation)) {
            moveBattleGroup(newLocation);
            moveReinforcements(newLocation);
            setLocation(newLocation.getXCoord(), newLocation.getYCoord());
        }
    }

    private boolean distanceFromCurrentLocation(Location newLocation) {
        Location oldLocation = getLocation();
        int distance = (int)(Math.sqrt(Math.pow(newLocation.getXCoord()-oldLocation.getXCoord(),2) + Math.pow(newLocation.getYCoord()-oldLocation.getYCoord(),2)));
        return distance < ((UnitStats) entityStats).getMovement();
    }

    public void checkIfRecruitsAtRallyPoint() {
        for (Unit reinforcementUnit : reinforcements.values()) {
            if (reinforcementUnit.getLocation().equals(rallyPoint.getLocation())) {
                arrivedRallyPoint((FighterUnit) reinforcementUnit);
            }
        }
    }

    public void addWorkers(List<Worker> workersOnLocation, Location location) {
        System.out.println("ADDING WORKERS");
        //rp must be on workers' location in order to pick them up
        if (!location.equals(rallyPoint.getLocation())) {
            return;
        }

        //need bg to have soldiers
        if (battleGroup.isEmpty()) {
            return;
        }

        for (Worker worker: workersOnLocation) {
            System.out.println(worker);
            battleGroup.put(worker.getEntityId(), worker);
            hasWorkers = true;
        }
        System.out.println("bg after workers added " +  battleGroup);
    }

    public boolean hasWorkers() {
        return hasWorkers;
    }

    public List<Worker> getWorkers() {
        List<Worker> workers = new ArrayList<>();

        Iterator<Map.Entry<EntityId, Unit>> iter = battleGroup.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<EntityId, Unit> entry = (Map.Entry<EntityId, Unit>) iter.next();
            EntityId id = entry.getKey();
            System.out.println("is this an army worker " + id);
            if (id.getIdType().equals(IdType.WORKER)) {
                workers.add((Worker) battleGroup.get(id));
            }
        }

        for (Worker worker : workers) {
            battleGroup.remove(worker.getEntityId());
        }

        hasWorkers = false;
        return workers;
    }
}
