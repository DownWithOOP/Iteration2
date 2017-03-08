package controller.commands.entitycommand.unitcommand;

import controller.commands.Command;
import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class AdvanceToRallyPointCommand extends UnitCommand{

    private int number;

    public AdvanceToRallyPointCommand(Unit unit, int number) {
        super(unit, 1);
        this.number=number;
    }


    @Override
    public boolean execute() {
        if(super.execute()) {
            unit.advanceToRallyPoint(number);
            return true;
        }
        return false;
    }
}
