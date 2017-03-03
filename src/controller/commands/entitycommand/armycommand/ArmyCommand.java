package controller.commands.entitycommand.armycommand;

import controller.commands.Command;
import model.entities.unit.Army;

/**
 * Created by jordi on 3/1/2017.
 */
public abstract class ArmyCommand implements Command{
    protected Army army;

    public ArmyCommand(Army army){
        this.army=army;
    }

}
