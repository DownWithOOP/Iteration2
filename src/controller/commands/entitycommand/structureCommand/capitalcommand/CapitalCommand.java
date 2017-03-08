package controller.commands.entitycommand.structureCommand.capitalcommand;

import controller.commands.entitycommand.structureCommand.StructureCommand;
import model.entities.structure.Capital;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public abstract class CapitalCommand extends StructureCommand {
    private Capital capital;

    public CapitalCommand(Capital capital) {
        super(capital);
    }

    public Capital getCapital() {
        return capital;
    }
}
