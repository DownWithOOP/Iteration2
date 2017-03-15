package controller.commands.entitycommand.armycommand;

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
        System.out.println("attack command build with direction " + direction.toString());
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            System.out.println("attack command executing");
            getArmy().attack(getDirection());
            return true;
        }
        return false;
    }
}
