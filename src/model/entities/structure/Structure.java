package model.entities.structure;

import controller.CommandRelay;
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

    public Structure(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        //TODO: addAllCommands to be placed here after all the structure actions are defined
    }
}
