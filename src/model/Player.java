package model;

import controller.commands.DirectionType;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Player {

    EntityOwnership entities;
    Selection currentSelection;

    public Player(){

        entities = new EntityOwnership(); //TODO should entity ownership know Player?
        currentSelection = new Selection(entities.getCurrentInstance()); //TODO rename method?

    }
    public void endTurn(){

    }
    public void startTurn(){

    }

    public void cycleMode(DirectionType direction){
        currentSelection.updateSelectedCommandable(entities.cycleMode(direction));
    }

    public void cycleType(DirectionType direction){
        currentSelection.updateSelectedCommandable(entities.cycleType(direction));
    }

    public void cycleInstance(DirectionType direction){
        currentSelection.updateSelectedCommandable(entities.cycleInstance(direction));
    }

    public void cycleAction(DirectionType direction){
        //TODO cycle through actions
    }
}
