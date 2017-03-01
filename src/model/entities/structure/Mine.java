package model.entities.structure;

import model.entities.EntityId;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 2/24/2017.
 */
public class Mine extends Structure {

    /**
     * @param playerId
     * @param id
     */
    public Mine(CustomID playerId, String id) {
        super(playerId, id);
    }

    @Override
    protected CustomID setId(String id, CustomID playerId) {
        return new EntityId(IdType.mine,id, playerId);
    }

    @Override
    public void decommission() {

    }


}
