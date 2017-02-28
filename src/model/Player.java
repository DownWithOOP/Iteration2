package model;

import controller.commands.CycleDirection;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Player {

    EntityOwnership entities;
    Selection currentSelection;
    CustomID customID;
    public Player(){

        //TODO add an id for player in the constructor
        customID=new CustomID(IdType.player,"newPlayer");
        entities = new EntityOwnership(customID); //TODO should entity ownership know Player?
        currentSelection = new Selection(entities.getCurrentInstance()); //TODO rename method?


    }
    public void endTurn(){

    }
    public void startTurn(){

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
}
