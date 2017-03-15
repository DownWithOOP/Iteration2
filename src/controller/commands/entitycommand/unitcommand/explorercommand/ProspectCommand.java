package controller.commands.entitycommand.unitcommand.explorercommand;

import controller.commands.entitycommand.unitcommand.UnitCommand;
import model.entities.unit.Explorer;
import model.entities.unit.Unit;

/**
 * Created by LesliesLaptop on 3/10/17.
 */
public class ProspectCommand extends ExplorerCommand {

    public ProspectCommand(Explorer explorer) {
        super(explorer, 1);
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getExplorer().prospect();
            return true;
        }
        return false;
    }
}
