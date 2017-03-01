package controller.commands.entitycommand.unitcommand;

import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class AbandonArmy extends UnitCommand{

    public AbandonArmy(Unit unit) {
        super(unit);
    }

    @Override
    public void execute() {
        unit.abandonArmy();
    }
}
