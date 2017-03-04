package controller.commands.entitycommand.unitcommand;

import controller.commands.Command;
import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class AdvanceToRallyPointCommand extends UnitCommand{
    int number;

    public AdvanceToRallyPointCommand(Unit unit, int number) {
        super(unit);
        this.number=number;
    }


    @Override
    public void execute() {
        unit.advanceToRallyPoint(number);
    }
}
