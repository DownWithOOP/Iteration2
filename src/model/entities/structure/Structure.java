package model.entities.structure;

import model.entities.Entity;
import model.entities.EntityType;
import model.map.tile.resources.Resource;
import utilities.id.CustomID;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Structure extends Entity {

    /**
     * @param playerId
     */

    private Resource energyResource;
    private Resource foodResource;

    public Structure(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
        //TODO: addAllCommands to be placed here after all the structure actions are defined
    }

    @Override
    public void receiveResource(Resource resource) {
        //TODO
    }

    @Override
    public void consumeResources() {
        //TODO
    }
}
