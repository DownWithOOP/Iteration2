package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
import controller.commands.Direction;
import model.entities.unit.Army;

/**
 * Created by jordi on 2/28/2017.
 */
public class DefendCommand extends ArmyCommand{

    Direction direction;

    public DefendCommand(Army army, Direction direction){
        super(army);
        this.direction = direction;
    }

    @Override
    public void execute() {
        army.attack(direction);
    }
}
