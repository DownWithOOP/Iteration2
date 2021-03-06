package controller.commands.entitycommand.entitycommand;

import controller.commands.CommandType;
import controller.commands.entitycommand.AbstractCommand;
import model.entities.Entity;

/**
 * Created by jordi on 2/28/2017.
 */
public class DecommissionCommand extends EntityCommand {

    public DecommissionCommand(Entity entity) {
        super(entity, 0);
        super.setCommandType(CommandType.DECOMMISSION);
    }

    @Override
    public boolean execute() {
        getEntity().decommission();
        return true;
    }
}
