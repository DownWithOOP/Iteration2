package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
import controller.commands.Direction;
import model.entities.unit.Army;

/**
 * Created by jordi on 2/28/2017.
 */
public class AttackCommand extends ArmyCommand {

    private Direction direction;

    public AttackCommand(Army army, Direction direction){
        super(army, 1);
        this.direction=direction;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            army.attack(direction);
            return true;
        }
        return false;
    }
}
