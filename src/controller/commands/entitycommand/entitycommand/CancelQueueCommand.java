package controller.commands.entitycommand.entitycommand;

import controller.commands.entitycommand.AbstractCommand;
import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public class CancelQueueCommand extends EntityCommand {
    public CancelQueueCommand(Entity entity) {
        super(entity, 0);
    }

    @Override
    public boolean execute() {
        getEntity().cancelQueue();
        return true;
    }
}
