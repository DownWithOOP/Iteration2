package model.entities.structure;

import model.entities.EntityId;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Fort extends Structure {

    /**
     * @param playerId
     * @param id
     */
    public Fort(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    protected CustomID setId(String id, CustomID playerId) {
        return new EntityId(IdType.fort,id, playerId);
    }


    @Override
    public void decommission() {

    }

    @Override
    public void decomission() {

    }
}
