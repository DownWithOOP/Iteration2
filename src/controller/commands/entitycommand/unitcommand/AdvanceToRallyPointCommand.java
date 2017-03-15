package controller.commands.entitycommand.unitcommand;

import controller.commands.Command;
import controller.commands.CommandType;
import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class AdvanceToRallyPointCommand extends UnitCommand{

    private int number;

    public AdvanceToRallyPointCommand(Unit unit, int number) {
        super(unit, 1);
        super.setCommandType(CommandType.ADVANCE_TO_RALLY_POINT);
        this.number=number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getUnit().advanceToRallyPoint(getNumber());
            return true;
        }
        return false;
    }
}
