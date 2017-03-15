package model.entities.structure;

import controller.CommandRelay;
import model.entities.Stats.NonStaffedStructureStats;
import model.entities.Stats.Stats;
import model.map.tile.resources.Resource;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class NonStaffedStructure extends Structure {
    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */

    Resource energyResource;
    Resource oreResource;

    public NonStaffedStructure(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
    }

    /**
     * Resource consumption
     */

    @Override
    public void receiveResource(Resource resource) {
        switch (resource.getResourceType()){
            case ENERGY:
                energyResource.addResource(resource.getLevel());
                break;
            case ORE:
                oreResource.addResource(resource.getLevel());
                break;
            default:
                break;
        }
    }

    @Override
    public void consumeResources() {
        //TODO
        energyResource.consumeResource(0.10);
        oreResource.consumeResource(0.10);
    }

    @Override
    protected IdType getIdType() {
        return null;
    }

    @Override
    protected Stats setEntityStats() {
        return new NonStaffedStructureStats(0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }
}
