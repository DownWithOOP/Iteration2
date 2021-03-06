package controller.commands.entitycommand.unitcommand;

import controller.commands.CommandType;
import controller.commands.entitycommand.AbstractCommand;
import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public abstract class UnitCommand extends AbstractCommand {

    protected Unit unit;

    public UnitCommand(Unit unit, int numTurns){
        super(numTurns);
        this.unit=unit;
    }

    public Unit getUnit() {
        return unit;
    }

}
