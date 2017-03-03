package controller.commands.entitycommand.armycommand;

import model.entities.unit.Army;

/**
 * Created by jordi on 3/1/2017.
 */
public class DisbandCommand extends ArmyCommand{

    public DisbandCommand(Army army) {
        super(army);
    }

    @Override
    public void execute() {
        army.disband();
    }
}
