package controller.commands.entitycommand.entitycommand;

import controller.commands.Command;
import model.entities.Entity;

/**
 * Created by jordi on 2/28/2017.
 */
public class DecommissionCommand extends EntityCommand {

    public DecommissionCommand(Entity entity) {
        super(entity);
    }

    @Override
    public void execute() {
        entity.decommission();
    }
}
