package model;

import controller.commands.CycleDirection;
import utilities.ObserverInterfaces.*;
import model.map.Map;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Player implements MapSubject, UnitSubject, StructureSubject {

    EntityOwnership entities;
    Selection currentSelection;
    CustomID customID;
    Map playerMap; // this map will contain the map that the specific player can see
    private ArrayList<MapObserver> mapObservers = new ArrayList<MapObserver>(); // will contain observers that get notified of changes
    private ArrayList<UnitObserver> unitObservers = new ArrayList<UnitObserver>(); // will contain observers that get notified of changes
    private ArrayList<StructureObserver> structureObservers = new ArrayList<StructureObserver>(); // will contain observers that get notified of changes

    public Player(Map map, MapObserver observer, UnitObserver unitObserver, StructureObserver structureObserver){

        //TODO add an id for player in the constructor
        customID=new CustomID(IdType.player,"newPlayer");
        entities = new EntityOwnership(customID); //TODO should entity ownership know Player?
        currentSelection = new Selection(entities.getCurrentInstance()); //TODO rename method
        this.playerMap = map; // TODO for the moment global map is shared, later each player will have own map
        this.registerMapObserver(observer);
        this.registerUnitObserver(unitObserver);
        this.registerStructureObserver(structureObserver);
    }
    public void endTurn(){

    }
    public void startTurn(){

        this.notifyMapObservers(); // at the start of the game we want to give the player map to render
    }

    public void cycleMode(CycleDirection direction){
        currentSelection.updateSelectedCommandable(entities.cycleMode(direction));
        System.out.println("current mode " + entities.getCurrentMode());
    }

    public void cycleType(CycleDirection direction){
        currentSelection.updateSelectedCommandable(entities.cycleType(direction));
        System.out.println("current type " + entities.getCurrentType());
    }

    public void cycleInstance(CycleDirection direction){
        currentSelection.updateSelectedCommandable(entities.cycleInstance(direction));
        System.out.println("current instance " + entities.getCurrentInstance());
    }

    public void cycleCommand(CycleDirection direction){
        //TODO cycle through actions
        System.out.println("command cycle not hooked up yet :(");
    }

    @Override
    public void registerMapObserver(MapObserver o) {
        mapObservers.add(o);
    }
    @Override
    public void unregister(MapObserver o) {mapObservers.remove(o);}
    @Override
    public void registerUnitObserver(UnitObserver o) {unitObservers.add(o);}
    @Override
    public void unregister(UnitObserver o) {unitObservers.remove(o);}
    @Override
    public void registerStructureObserver(StructureObserver o) {structureObservers.add(o);}
    @Override
    public void unregister(StructureObserver o) { structureObservers.add(o);}
    @Override
    public void notifyUnitObservers() {
        for(UnitObserver unitObserver : unitObservers){
            unitObserver.update(entities.returnUnitRenderInformation());
        }
    }
    @Override
    public void notifyStructureObservers() {
        for(StructureObserver structureObserver : structureObservers){
            structureObserver.update(entities.returnStructureRenderInformation());
        }
    }
    @Override
    public void notifyMapObservers() {
        for(MapObserver mapObserver : mapObservers){
            mapObserver.update(playerMap.returnRenderInformation());
        }
    }




}
