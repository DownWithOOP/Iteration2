package model.entities.unit;

import model.entities.EntityId;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Worker extends Unit {

    /**
     * @param playerId
     * @param id
     */
    public Worker(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    protected CustomID setId(String id, CustomID playerId) {
        return new EntityId(IdType.worker,id, playerId);
    }

    @Override
    void abandonArmy() {

    }

    @Override
    void joinArmy() {

    }


    @Override
    public void decommission() {

    }

    @Override
    public void decomission() {

    }
}
