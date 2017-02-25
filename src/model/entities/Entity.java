package model.entities;

import controller.commands.Command;
import utilities.id.CustomID;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Entity {
    CustomID entityId, playerId;
    Queue<Command> commandQueue= new ArrayDeque<>();

   //  TODO:TAKE IN THE MOCK UP MAP, TAKE IN THE PLAYER ID
    /**
     *
     * @param playerId
     */
    public Entity(CustomID playerId){
        entityId=setId();
        this.playerId=playerId;
    }

    abstract CustomID setId();

    public void getLocation(){

    }

    public abstract void decommission();

    public void addToQueue(){

    }
    public void cancelQueue(){

    }
    public void powerUp(){

    }
    public void powerDown(){

    }
    public void changeStat(){

    }

}
