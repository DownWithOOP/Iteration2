package controller.commands.entitycommand.unitcommand.workercommand;

import controller.commands.CommandType;
import model.entities.EntityType;
import model.entities.unit.Worker;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/10/17.
 */
public class BuildStructureCommand extends WorkerCommand {
    private EntityType entityType;
    private CustomID customID;

    public BuildStructureCommand(Worker worker, EntityType entityType, CustomID customID) {
        super(worker, 5);
        super.setCommandType(CommandType.BUILD_STRUCTURE);
        this.entityType = entityType;
        this.customID = customID;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public CustomID getCustomID() {
        return customID;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getWorker().buildStructure(getEntityType(), getCustomID());
            return true;
        }
        return false;
    }
}
