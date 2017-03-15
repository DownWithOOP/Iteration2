package controller.commands.entitycommand.armycommand;

import controller.commands.CommandType;
import controller.commands.entitycommand.AbstractCommand;
import controller.commands.entitycommand.unitcommand.workercommand.WorkerCommand;
import model.RallyPoint;
import model.entities.EntityType;
import model.entities.unit.Worker;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/10/17.
 */
public class BuildStructureCommand extends AbstractCommand {

    private RallyPoint rallyPointToActOn;
    private EntityType entityTypeToBuild;

    public BuildStructureCommand(RallyPoint rallyPoint, EntityType entityType) {
        super(1);
        super.setCommandType(CommandType.BUILD_STRUCTURE);
        this.entityTypeToBuild = entityType;
        rallyPointToActOn = rallyPoint;
        System.out.println("BUILD STRUCTURE COMMAND CREATED");
    }

    public EntityType getEntityType() {
        return entityTypeToBuild;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            System.out.println("build struct command executing");
            this.rallyPointToActOn.buildStructure(getEntityType());
            return true;
        }
        return false;
    }
}
