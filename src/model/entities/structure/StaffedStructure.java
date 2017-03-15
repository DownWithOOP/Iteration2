package model.entities.structure;

import controller.CommandRelay;
import model.entities.Stats.Stats;
import model.entities.Stats.StructureStats;
import model.map.tile.resources.Resource;
import model.entities.unit.Worker;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class StaffedStructure extends Structure {
    private ArrayList<Worker> workers;  // keeps a list of all workers staffing a structure object

    private Resource foodResource;

    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public StaffedStructure(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
        this.workers = new ArrayList<Worker>();
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
