package model.entities.structure;

import model.entities.Entity;
import utilities.id.CustomID;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Structure extends Entity {


    /**
     * @param playerId
     */
    public Structure(CustomID playerId) {
        super(playerId);
    }
}
