package model.entities.unit;

import model.entities.Entity;
import model.entities.EntityId;
import model.entities.Fighter;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Army extends Entity implements Fighter {
    /**
     * @param playerId
     */
    public Army(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    public void attack() {

    }

    @Override
    public void defend() {

    }

    @Override
    public void decommission() {

    }

    //TODO: fix this
    @Override
    protected CustomID setId(String id, CustomID playerId){
       return new EntityId(IdType.army,id, playerId);
    }

    public void disband(){

    }
    public void registerUnit(){

    }
    public void removeUnit(){

    }
    public void waitTurn(){

    }
    private void moveBattleGroup(){

    }
    public void changeTargetLocation(){

    }
    private void changeReinforcementsLocation(){

    }
    private void setBattleGroupMovementSpeed(){

    }
    private void setBattleGroupAttackPower(){

    }
    private void setBattleGroupDefensePower(){

    }

}
