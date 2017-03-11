package controller.commands.entitycommand.unitcommand;

import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class AbandonArmyCommand extends UnitCommand{

    public AbandonArmyCommand(Unit unit) {
        super(unit, 1);
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getUnit().abandonArmy();
            return true;
        }
        return false;
    }
}
