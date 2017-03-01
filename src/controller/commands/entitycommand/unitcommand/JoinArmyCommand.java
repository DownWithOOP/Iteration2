package controller.commands.entitycommand.unitcommand;

import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class JoinArmyCommand extends UnitCommand{
    int number;

    public JoinArmyCommand(Unit unit, int number) {
        super(unit);
        this.number=number;
    }

    @Override
    public void execute() {
        unit.joinArmy(number);
    }
}
