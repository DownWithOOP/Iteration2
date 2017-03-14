package model.player;

import controller.CommandRelay;
import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import controller.commands.CycleDirection;
import model.common.Location;
import model.entities.unit.Army;
import utilities.ObserverInterfaces.*;
import model.map.Map;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Player implements MapSubject, UnitSubject, StructureSubject, StatusSubject {


    private EntityOwnership entities;
    private ResourceOwnership resources;
    private CustomID customID;
    private Map playerMap; // this map will contain the map that the specific player can see
    private ArrayList<MapObserver> mapObservers = new ArrayList<MapObserver>(); // will contain observers that get notified of changes
    private ArrayList<UnitObserver> unitObservers = new ArrayList<UnitObserver>(); // will contain observers that get notified of changes
    private ArrayList<StructureObserver> structureObservers = new ArrayList<StructureObserver>(); // will contain observers that get notified of changes
    private ArrayList<StatusObserver> statusObservers = new ArrayList<>(); // will contain observers that get notified of changes
    private int playerNumber; // players should know what # they are

    public Player(int playerNumber, Map map, CommandRelay commandRelay, MapObserver observer, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver, int startingX, int startingY){

        this.playerNumber = playerNumber;
        customID=new CustomID(IdType.PLAYER,"newPlayer");
        entities = new EntityOwnership(customID, commandRelay, startingX, startingY); //TODO should entity ownership know Player?
        resources = new ResourceOwnership(customID);
        this.playerMap = map; // TODO for the moment global map is shared, later each player will have own map
        this.registerMapObserver(observer);
        this.registerUnitObserver(unitObserver);
        this.registerStructureObserver(structureObserver);
        this.registerStatusObserver(statusObserver);
    }
    public void endTurn(){
        System.out.println(this.toString() + " is ending their turn");

    }
    public void startTurn(){
        System.out.println(this.toString() + " is starting their turn");

        this.notifyStructureObservers(); // we also want to update everyone with all our structure information
        this.notifyUnitObservers(); // and lets not forget the units
        this.notifyMapObservers(); // at the start of the game we want to give the player map to render
        this.notifyStatusObservers(); // yay status viewport
        entities.executeCommands(); //execute all commands in each entity's queue
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
    public void notifyMapObservers() { // IMPORTANT!! CALL THIS WHENEVER THE MAP IS UPDATED SO THE VIEW REFRESHES
        for(MapObserver mapObserver : mapObservers){
            // needs all the renderInformation to calculate fogOfWar
            System.out.println("player map: " +playerMap);
            mapObserver.update(this.playerNumber, playerMap.returnRenderInformation(), entities.returnUnitRenderInformation(), entities.returnStructureRenderInformation());
        }
    }
    @Override
    public void notifyUnitObservers() { // IMPORTANT!! CALL THIS WHENEVER ANY ENTITIES/UNITS ARE UPDATED SO THE VIEW REFRESHES
        for(UnitObserver unitObserver : unitObservers){
            unitObserver.update(entities.returnUnitRenderInformation());
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
            statusObserver.update(entities.returnStatusRenderInformation());
        }
    }

    public CustomID getCustomID() { return customID; }


    public void applyDamageToEntitiesOnLocation(Location location, int damage) {
        entities.applyDamageToEntitiesOnLocation(location, damage);
    }

    public void createArmy() {
        entities.createArmy();
    }
}
