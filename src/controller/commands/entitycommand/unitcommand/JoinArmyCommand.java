package controller.commands.entitycommand.unitcommand;

import controller.commands.CommandType;
import model.entities.unit.Unit;

/**
 * Created by jordi on 3/1/2017.
 */
public class JoinArmyCommand extends UnitCommand{

    private int number;

    public JoinArmyCommand(Unit unit, int number) {
        super(unit, 1);
        super.setCommandType(CommandType.JOIN_ARMY);
        this.number=number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            System.out.println("join army executing");
            getUnit().joinArmy(getNumber());
            return true;
        }
        return false;
    }
}
