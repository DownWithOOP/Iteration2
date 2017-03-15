package model.entities.structure;

import controller.CommandRelay;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import model.map.tile.resources.Resource;
import model.entities.unit.Worker;
import model.map.tile.resources.ResourceType;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class StaffedStructure extends Structure {
    private ArrayList<Worker> workers;  // keeps a list of all workers staffing a structure object

    private Resource energyResource;
    private Resource foodResource;
    private Resource oreResource;

    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public StaffedStructure(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        this.workers = new ArrayList<Worker>();
        energyResource = new Resource(ResourceType.ENERGY, 0);
        foodResource = new Resource(ResourceType.FOOD, 0);
        oreResource = new Resource(ResourceType.ORE, 0);
    }

    /**
     * Resource consumption
     */

    @Override
    public void receiveResource(Resource resource) {
        switch(resource.getResourceType()){
            case ENERGY:
                energyResource.addResource(resource.getLevel());
                break;
            case FOOD:
                foodResource.addResource(resource.getLevel());
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
        energyResource.consumeResource(0.10);
        foodResource.consumeResource(0.10);
        oreResource.consumeResource(0.10);
    }

    public void addWorkers(List<Worker> workerList) {
        for (Worker worker : workerList) {
            addWorker(worker);
        }
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public Worker getWorker(int index) {
        return workers.get(index);
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    @Override
    protected IdType getIdType() {
        return null;
    }

    @Override
    protected Stats setEntityStats() {
        return new StructureStats(0,0,0,0,0);
    }

    @Override
    public void decommission() {

    }
}
