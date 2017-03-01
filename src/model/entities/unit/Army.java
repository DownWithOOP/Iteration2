package model.entities.unit;

import controller.commands.Direction;
import model.entities.Entity;
import model.entities.EntityId;
import model.entities.Fighter;
import model.entities.Stats.Stats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public  class Army extends Entity implements Fighter {
    /**
     * @param playerId
     */
    public Army(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    public void attack(Direction direction) {
        System.out.println("attack "+direction.toString());
    }

    @Override
    public void defend(Direction direction) {
        System.out.println( "defend "+direction.toString());
    }


    @Override
    public void decommission() {
        System.out.println("army decommission");
    }


    //TODO: fix this
    @Override
    protected CustomID setId(String id, CustomID playerId){
       return new EntityId(IdType.army,id, playerId);
    }

    @Override
    protected Stats setEntityStats() {
        return new UnitStats(0,0,0,0,0,0,0,0);
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
