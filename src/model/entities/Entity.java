package model.entities;

import controller.availablecommands.Commandable;
import controller.commands.Command;
import model.entities.Stats.Stats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Entity extends Commandable {
    CustomID entityId, playerId;
    Queue<Command> commandQueue= new ArrayDeque<>();
    private boolean isPoweredDown;

   //  TODO:TAKE IN THE MOCK UP MAP, TAKE IN THE PLAYER ID
    /**
     *
     * @param playerId
     */
    public Entity(CustomID playerId, String id){
        entityId=setId(id, playerId);
        this.playerId=playerId;
        this.isPoweredDown=false;
    }

    protected abstract CustomID setId(String id, CustomID playerId);

    public IdType getEntityType(){
        return this.entityId.getIdType();
    }

    public void getLocation(){

    }

    public abstract void decommission();

    public void addToQueue(){

    }
    public void cancelQueue(){

    }
    public void powerUp(Stats entityStats) {
        if (isPoweredDown()) {
            int originalUpkeep = entityStats.getDefaultUpkeep();
            entityStats.setUpkeep(originalUpkeep);
            setPoweredDown(false);
        }
    }
    public void powerDown(Stats entityStats) {
        if (!isPoweredDown()) {
            int upkeep = entityStats.getUpkeep();
            int loweredUpkeep = Math.round((int)(upkeep * .25));
            entityStats.setUpkeep(loweredUpkeep);
            setPoweredDown(true);
        }

    }

    public boolean isPoweredDown() {
        return isPoweredDown;
    }

    public void setPoweredDown(boolean isPoweredDown) {
        this.isPoweredDown = isPoweredDown;
    }

    public abstract void decomission();
}
