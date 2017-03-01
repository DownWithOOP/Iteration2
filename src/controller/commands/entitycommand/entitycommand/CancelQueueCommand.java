package controller.commands.entitycommand.entitycommand;

import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public class CancelQueueCommand extends EntityCommand{
    public CancelQueueCommand(Entity entity) {
        super(entity);
    }

    @Override
    public void execute() {
        entity.cancelQueue();
    }
}
