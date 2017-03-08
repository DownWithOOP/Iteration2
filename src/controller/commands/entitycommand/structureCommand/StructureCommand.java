package controller.commands.entitycommand.structureCommand;

import controller.commands.entitycommand.AbstractCommand;
import model.entities.structure.Structure;

/**
 * Created by jordi on 3/1/2017.
 */
public abstract class StructureCommand extends AbstractCommand {
    Structure structure;

    public StructureCommand(Structure structure, int numTurns) {
        super(numTurns);
        this.structure = structure;
    }
}
