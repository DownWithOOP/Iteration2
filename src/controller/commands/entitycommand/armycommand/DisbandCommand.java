package controller.commands.entitycommand.armycommand;

import controller.commands.CommandType;
import model.entities.unit.Army;

/**
 * Created by jordi on 3/1/2017.
 */
public class DisbandCommand extends ArmyCommand{

    public DisbandCommand(Army army) {
        super(army, 1);
        super.setCommandType(CommandType.DISBAND);
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getArmy().disband();
            return true;
        }
        return false;
    }
}
