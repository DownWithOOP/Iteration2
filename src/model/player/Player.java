package model.player;

import controller.CommandRelay;
import controller.Observers.PlayerObservator;
import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import controller.commands.CycleDirection;
import javafx.scene.input.KeyCode;
import model.ActiveState;
import model.RallyPoint;
import model.RenderInformation.StatusRenderInformation;
import model.common.Location;
import model.entities.EntityId;
import model.entities.structure.Structure;
import model.entities.unit.Army;
import model.entities.unit.FighterUnit;
import model.entities.unit.Unit;
import model.map.tile.resources.Resource;
import model.map.tile.resources.ResourceType;
import utilities.ObserverInterfaces.*;
import model.map.Map;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Player implements MapSubject, UnitSubject, StructureSubject, StatusSubject, PlayerSubject {


    private EntityOwnership entities;
    private ResourceOwnership resources;
    private CustomID customID;
    private Map playerMap; // this map will contain the map that the specific player can see
    private ArrayList<MapObserver> mapObservers = new ArrayList<MapObserver>(); // will contain observers that get notified of changes
    private ArrayList<UnitObserver> unitObservers = new ArrayList<UnitObserver>(); // will contain observers that get notified of changes
    private ArrayList<StructureObserver> structureObservers = new ArrayList<StructureObserver>(); // will contain observers that get notified of changes
    private ArrayList<StatusObserver> statusObservers = new ArrayList<>(); // will contain observers that get notified of changes
    private ArrayList<PlayerObservator> playerObservators = new ArrayList<>();
    private HashMap<KeyCode,String> keymap;
    private int playerNumber; // players should know what # they are

    public Player(int playerNumber, Map map, CommandRelay commandRelay, MapObserver observer, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver, PlayerObservator playerObservator, int startingX, int startingY){

        this.playerNumber = playerNumber;
        customID=new CustomID(IdType.PLAYER, String.valueOf(playerNumber));

//        entities = new EntityOwnership(customID, commandRelay, startingX, startingY); //TODO should entity ownership know Player?

        entities = new EntityOwnership(customID, commandRelay,startingX, startingY); //TODO should entity ownership know Player?
        entities.setMapObserver(observer);
        entities.setUnitObserver(unitObserver);

        keymap = new HashMap<KeyCode, String>();

        // Defualt keys
        keymap.put(KeyCode.NUMPAD2, "SOUTH");
        keymap.put(KeyCode.NUMPAD8, "NORTH");
        keymap.put(KeyCode.NUMPAD7, "NW");
        keymap.put(KeyCode.NUMPAD1, "SW");
        keymap.put(KeyCode.NUMPAD9, "NE");
        keymap.put(KeyCode.NUMPAD3, "SE");



        resources = new ResourceOwnership(customID);
        resources.addResource(new Resource(ResourceType.ENERGY, 100));
        resources.addResource(new Resource(ResourceType.ORE, 100));
        resources.addResource(new Resource(ResourceType.FOOD, 100));
        this.playerMap = map; // TODO for the moment global map is shared, later each player will have own map
        this.registerMapObserver(observer);
        this.registerUnitObserver(unitObserver);
        this.registerStructureObserver(structureObserver);
        this.registerStatusObserver(statusObserver);
        this.registerPlayerObserver(playerObservator);
    }
    public void endTurn(){
        System.out.println(this.toString() + " is ending their turn");
        entities.consumeResources();

    }
    public void startTurn(){
        System.out.println(this.toString() + " is starting their turn");

        entities.executeCommands(); //execute all commands in each entity's queue
        this.notifyStructureObservers(); // we also want to update everyone with all our structure information
        this.notifyUnitObservers(); // and lets not forget the units
        this.notifyMapObservers(); // at the start of the game we want to give the player map to render
        this.notifyStatusObservers(); // yay status viewport
        this.notifyObservers(); // for the key mapping
    }

    /**
     * Resource allocation and distribution
     */
    public void giveResourcesToPlayer(Resource resource){
        resources.addResource(resource);
    }

    public void distributeEnergyResource(EntityId entityId){
        entities.distributeResource(entityId, resources.allocateEnergyResource());
    }

    public void distributeOreResource(EntityId entityId){
        entities.distributeResource(entityId, resources.allocateOreResource());
    }

    public void distributeFoodResource(EntityId entityId){
        entities.distributeResource(entityId, resources.allocateFoodResource());
    }

    public void cycleMode(CycleDirection direction){
        entities.cycleMode(direction);
        this.notifyStatusObservers(); // yay status viewport
    }

    public void cycleType(CycleDirection direction){
        entities.cycleType(direction);
        this.notifyStatusObservers(); // yay status viewport
    }

    public void cycleInstance(CycleDirection direction){
        entities.cycleInstance(direction);
        this.notifyStatusObservers(); // yay status viewport
        this.notifyUnitObservers();
    }

    public void cycleCommand(CycleDirection direction){
        entities.cycleCommand(direction);
        this.notifyStatusObservers(); // yay status viewport
        //System.out.println("command cycle not hooked up yet :(");
    }

    public Commandable getCurrentInstance() {
        return entities.getCurrentInstance();
    }

    public CommandType getCurrentCommandType() {
        return entities.getCurrentCommand();
    }


    @Override
    public void registerMapObserver(MapObserver o) { mapObservers.add(o); }
    @Override
    public void registerUnitObserver(UnitObserver o) {unitObservers.add(o);}
    @Override
    public void registerStructureObserver(StructureObserver o) {structureObservers.add(o);}
    @Override
    public void registerStatusObserver(StatusObserver o) { statusObservers.add(o); }

    @Override
    public void unregister(MapObserver o) {mapObservers.remove(o);}
    @Override
    public void unregister(UnitObserver o) {unitObservers.remove(o);}
    @Override
    public void unregister(StructureObserver o) { structureObservers.remove(o);}
    @Override
    public void unregister(StatusObserver o) { statusObservers.remove(o);}
    @Override
    public void registerPlayerObserver(PlayerObservator o) {
        playerObservators.add(o);
    }
    @Override
    public void unregister(MiniMapObserver o) {
        playerObservators.remove(o);
    }
    @Override
    public void notifyObservers() {
        for(PlayerObservator playerObservator : playerObservators){
           playerObservator.update(keymap);
        }
    }

    @Override
    public void notifyMapObservers() { // IMPORTANT!! CALL THIS WHENEVER THE MAP IS UPDATED SO THE VIEW REFRESHES
        for(MapObserver mapObserver : mapObservers){
            // needs all the renderInformation to calculate fogOfWar
            System.out.println("player map: " +playerMap);
            mapObserver.update(this.playerNumber, playerMap.returnRenderInformation(), entities.returnUnitRenderInformation(), entities.returnStructureRenderInformation());
            entities.setMapObserver(mapObserver);
        }
    }
    @Override
    public void notifyUnitObservers() { // IMPORTANT!! CALL THIS WHENEVER ANY ENTITIES/UNITS ARE UPDATED SO THE VIEW REFRESHES
        for(UnitObserver unitObserver : unitObservers){
            unitObserver.update(entities.returnUnitRenderInformation());
            entities.setUnitObserver(unitObserver);
        }
    }
    @Override
    public void notifyStructureObservers() { // IMPORTANT!! CALL THIS WHENEVER ANY ENTITIES/STRUCTURES ARE UPDATED SO THE VIEW REFRESHES
        for(StructureObserver structureObserver : structureObservers){
            structureObserver.update(entities.returnStructureRenderInformation());
        }
    }
    @Override
    public void notifyStatusObservers() { // IMPORTANT!! CALL THIS WHENEVER ENTITY OWNERSHIP IS UPDATED SO THE VIEW REFRESHES
        for(StatusObserver statusObserver : statusObservers){
            //TODO maybe get status render info from current selection instead of entities. This would remove the double calls to functions in EntityOwnership
            StatusRenderInformation temp = entities.returnStatusRenderInformation();
            statusObserver.update(entities.returnStatusRenderInformation());
        }
    }

    public CustomID getCustomID() { return customID; }


    public void applyDamageToEntitiesOnLocation(Location location, int damage) {
        System.out.println("player tellig entities to take dmg");
        entities.applyDamageToEntitiesOnLocation(location, damage);
    }

    public void createArmy() {
        entities.createArmy();
        notifyUnitObservers();
    }

    public Commandable getEntity(EntityId commandableId) {
        return entities.getEntity(commandableId);
    }

    public void addFighterUnitToArmy(FighterUnit fighterUnit, int armyNumber) {
        entities.addExistingFighterUnitToArmy(fighterUnit, armyNumber);
        notifyUnitObservers();
    }

    public void addRallyPoint(RallyPoint rallyPoint, int armyNumber) {
        entities.addRallyPoint(rallyPoint, armyNumber);
        notifyUnitObservers();
    }


    public void addUnit(Unit unit) {
        entities.addEntity(unit);
        notifyUnitObservers();
    }

    public void removeUnit(Unit unit) {
        entities.removeEntity(unit);
        notifyUnitObservers();
    }

    public int getEnergyResourceLevel() {
        return resources.getEnergyResources().getLevel();
    }

    public int getOreResourceLevel() {
        return resources.getOreResources().getLevel();
    }

    public int getFoodResourceLevel() {
        return resources.getFoodResources().getLevel();
    }

    public void addStructure(Structure structure) {
        entities.addEntity(structure);
        notifyStructureObservers();
    }

    public void removeStructure(Structure structure) {
        entities.removeEntity(structure);
        notifyStructureObservers();
    }

    public Commandable getCurrentInstanceToCommand() {
        return entities.getCurrentInstanceToCommand();
    }

    public void getInstanceFromIndex(int indexToSelect) {
        entities.getInstance(indexToSelect);
        notifyStatusObservers();
        notifyUnitObservers();
    }

    public void addWorkersToArmy(Location location, EntityId armyId) {
        entities.addWorkersToArmy(location, armyId);
    }
}
