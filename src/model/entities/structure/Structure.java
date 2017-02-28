package model.entities.structure;

import model.entities.Entity;
import model.entities.EntityType;
import utilities.id.CustomID;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Structure extends Entity {

    /**
     * @param playerId
     */
    public Structure(CustomID playerId, String id) {
        super(playerId, id);
    }
}
