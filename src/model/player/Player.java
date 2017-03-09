package model.player;

import controller.commands.CycleDirection;
import model.Selection;
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
    private Selection currentSelection;
    private CustomID customID;
    private Map playerMap; // this map will contain the map that the specific player can see
    private ArrayList<MapObserver> mapObservers = new ArrayList<MapObserver>(); // will contain observers that get notified of changes
    private ArrayList<UnitObserver> unitObservers = new ArrayList<UnitObserver>(); // will contain observers that get notified of changes
    private ArrayList<StructureObserver> structureObservers = new ArrayList<StructureObserver>(); // will contain observers that get notified of changes
    private ArrayList<StatusObserver> statusObservers = new ArrayList<>(); // will contain observers that get notified of changes

    public Player(Map map, MapObserver observer, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver, int startingX, int startingY){

        //TODO add an id for player in the constructor
        customID=new CustomID(IdType.PLAYER,"newPlayer");
        entities = new EntityOwnership(customID, startingX, startingY); //TODO should entity ownership know Player?
        currentSelection = new Selection(entities.getCurrentInstance()); //TODO rename method
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
        this.notifyMapObservers(); // at the start of the game we want to give the player map to render
        this.notifyStructureObservers(); // we also want to update everyone with all our structure information
        this.notifyUnitObservers(); // and lets not forget the units
        this.notifyStatusObservers(); // yay status viewport
    }

    public void cycleMode(CycleDirection direction){
        currentSelection.updateSelectedCommandable(entities.cycleMode(direction));
        this.notifyStatusObservers(); // yay status viewport
    }

    public void cycleType(CycleDirection direction){
        currentSelection.updateSelectedCommandable(entities.cycleType(direction));
        this.notifyStatusObservers(); // yay status viewport
    }

    public void cycleInstance(CycleDirection direction){
        currentSelection.updateSelectedCommandable(entities.cycleInstance(direction));
        this.notifyStatusObservers(); // yay status viewport
    }

    public void cycleCommand(CycleDirection direction){
        //TODO cycle through actions
        currentSelection.updateSelectedCommand(entities.cycleCommand(direction));
        this.notifyStatusObservers(); // yay status viewport
        //System.out.println("command cycle not hooked up yet :(");
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
            mapObserver.update(playerMap.returnRenderInformation());
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



}
