package model.entities.unit;

import model.entities.EntityType;
import utilities.id.CustomID;

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
    protected CustomID setId(String id) {
        return null;
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
