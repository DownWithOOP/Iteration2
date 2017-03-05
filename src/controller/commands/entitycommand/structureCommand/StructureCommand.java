package controller.commands.entitycommand.structureCommand;

import controller.ControllerManager;
import controller.commands.Command;
import model.entities.structure.Structure;

/**
 * Created by jordi on 3/1/2017.
 */
public abstract class StructureCommand implements Command {
    Structure structure;

    public StructureCommand(Structure structure) {
        this.structure = structure;
    }
}
