package model;

import controller.AbstractObserver;
import controller.commands.CycleDirection;
import model.map.Map;
import utilities.Renderer;
import utilities.Subject;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Player implements MapSubject{

    EntityOwnership entities;
    Selection currentSelection;
    CustomID customID;
    Map playerMap; // this map will contain the map that the specific player can see
    private ArrayList<MapObserver> observers = new ArrayList<MapObserver>(); // will contain observers that get notified of changes

    public Player(Map map, AbstractObserver observer){

        //TODO add an id for player in the constructor
        customID=new CustomID(IdType.player,"newPlayer");
        entities = new EntityOwnership(customID); //TODO should entity ownership know Player?
        currentSelection = new Selection(entities.getCurrentInstance()); //TODO rename method
        this.playerMap = map; // TODO for the moment global map is shared, later each player will have own map
        this.register(observer); // used to communicate
    }
    public void endTurn(){

    }
    public void startTurn(){
        this.notifyObservers();
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
    public void register(MapObserver o) {
        observers.add(o);
    }
    @Override
    public void unregister(MapObserver o) {
        observers.remove(o);
    }
    @Override
    public void notifyObservers() {
        for(MapObserver mapObserver : observers){
            mapObserver.update(this.playerMap);
        }
    }

    public void draw(Renderer r) {
        draw(r);
    }

}
