package model.entities.structure;

import model.entities.EntityType;
import utilities.id.CustomID;

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
    protected CustomID setId(String id) {
        return null;
    }


    @Override
    public void decommission() {

    }

    @Override
    public void decomission() {

    }
}
