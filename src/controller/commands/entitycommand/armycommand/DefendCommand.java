package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.Direction;
import model.entities.unit.Army;

/**
 * Created by jordi on 2/28/2017.
 */
public class DefendCommand extends ArmyCommand{

    private Direction direction;

    public DefendCommand(Army army, Direction direction){
        super(army, 1);
        super.setCommandType(CommandType.DEFEND);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getArmy().attack(getDirection());
            return true;
        }
        return false;
    }
}
