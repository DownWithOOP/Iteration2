package model.entities.unit;

import model.entities.EntityId;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Melee extends Unit{


    /**
     * @param playerId
     * @param id
     */
    public Melee(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    protected CustomID setId(String id, CustomID playerId) {
        return new EntityId(IdType.melee,id, playerId);
    }

    @Override
    void abandonArmy() {

    }

    @Override
    void joinArmy() {

    }

    @Override
    public void decommission() {
        System.out.println("melee decommission");
    }




}
