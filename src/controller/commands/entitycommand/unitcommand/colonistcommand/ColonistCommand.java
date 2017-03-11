package controller.commands.entitycommand.unitcommand.colonistcommand;

import controller.commands.entitycommand.unitcommand.UnitCommand;
import model.entities.unit.Colonist;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public abstract class ColonistCommand extends UnitCommand {
    private Colonist colonist;

    public ColonistCommand(Colonist colonist, int numTurns) {
        super(colonist, numTurns);
        this.colonist = colonist;
    }

    public Colonist getColonist() {
        return colonist;
    }
}
