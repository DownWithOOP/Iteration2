package controller.commands.entitycommand.armycommand;

import controller.commands.entitycommand.AbstractCommand;
import model.entities.unit.Army;

/**
 * Created by jordi on 3/1/2017.
 */
public abstract class ArmyCommand extends AbstractCommand {

    protected Army army;

    public ArmyCommand(Army army, int numTurns) {
        super(numTurns);
        this.army=army;
    }

}
