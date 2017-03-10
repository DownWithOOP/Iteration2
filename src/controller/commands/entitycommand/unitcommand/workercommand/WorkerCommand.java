package controller.commands.entitycommand.unitcommand.workercommand;

import controller.commands.entitycommand.unitcommand.UnitCommand;
import model.entities.unit.Unit;
import model.entities.unit.Worker;

/**
 * Created by LesliesLaptop on 3/10/17.
 */
public class WorkerCommand extends UnitCommand {
    private Worker worker;

    public WorkerCommand(Worker worker, int numTurns) {
        super(worker, numTurns);
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }
}
