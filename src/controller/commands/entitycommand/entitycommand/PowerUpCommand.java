package controller.commands.entitycommand.entitycommand;

import controller.commands.entitycommand.AbstractCommand;
import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public class PowerUpCommand extends EntityCommand {
    public PowerUpCommand(Entity entity) {
        super(entity, 1);
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            entity.powerUp();
            return true;
        }
        return false;
    }
}
