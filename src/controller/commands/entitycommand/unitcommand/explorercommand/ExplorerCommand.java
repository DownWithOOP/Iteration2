package controller.commands.entitycommand.unitcommand.explorercommand;

import controller.commands.entitycommand.unitcommand.UnitCommand;
import model.entities.unit.Explorer;
import model.entities.unit.Unit;

/**
 * Created by LesliesLaptop on 3/10/17.
 */
public class ExplorerCommand extends UnitCommand {
    private Explorer explorer;

    public ExplorerCommand(Explorer explorer, int numTurns) {
        super(explorer, numTurns);
        this.explorer = explorer;
    }

    public Explorer getExplorer() {
        return explorer;
    }
}
