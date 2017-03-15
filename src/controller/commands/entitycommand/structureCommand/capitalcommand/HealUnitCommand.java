package controller.commands.entitycommand.structureCommand.capitalcommand;

import model.entities.structure.Capital;
import model.entities.unit.FighterUnit;
import model.entities.unit.Unit;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class HealUnitCommand extends CapitalCommand {
    private FighterUnit unitToHeal;

    public HealUnitCommand(Capital capital, FighterUnit unitToHeal) {
        super(capital, 1);
        this.unitToHeal = unitToHeal;
    }

    public FighterUnit getUnitToHeal() {
        return unitToHeal;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getCapital().healUnit(getUnitToHeal());
            return true;
        }
        return false;
    }
}
