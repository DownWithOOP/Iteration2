package controller.commands.entitycommand.structureCommand.capitalcommand;

import model.entities.structure.Capital;
import model.entities.unit.Unit;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class HealUnitCommand extends CapitalCommand {
    private Unit unitToHeal;

    public HealUnitCommand(Capital capital, Unit unitToHeal) {
        super(capital);
        this.unitToHeal = unitToHeal;
    }

    @Override
    public void execute() {
        getCapital().healUnit(unitToHeal);
    }
}
