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
    private String id;

    public BuildStructureCommand(Worker worker, EntityType entityType, CustomID customID, String id) {
        super(worker, 5);
        super.setCommandType(CommandType.BUILD_STRUCTURE);
        this.entityType = entityType;
        this.customID = customID;
        this.id = id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public CustomID getCustomID() {
        return customID;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getWorker().buildStructure(getEntityType(), getCustomID(), getId());
            return true;
        }
        return false;
    }
}
