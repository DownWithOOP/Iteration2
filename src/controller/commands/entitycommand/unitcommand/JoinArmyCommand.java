package controller.commands.entitycommand.unitcommand;

import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class JoinArmyCommand extends UnitCommand{
    int number;

    public JoinArmyCommand(Unit unit, int number) {
        super(unit, 1);
        this.number=number;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            unit.joinArmy(number);
            return true;
        }
        return false;
    }
}
