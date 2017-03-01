package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
import controller.commands.Direction;
import model.entities.unit.Army;

/**
 * Created by jordi on 2/28/2017.
 */
public class AttackCommand implements Command {
    Army army;
    Direction direction;
    public AttackCommand(Army army, Direction direction){
        this.army=army;
        this.direction=direction;
    }

    @Override
    public void execute() {
        army.attack(direction);
    }
}
