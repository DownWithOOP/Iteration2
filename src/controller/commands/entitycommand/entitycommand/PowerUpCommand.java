package controller.commands.entitycommand.entitycommand;

import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public class PowerUpCommand extends EntityCommand {
    public PowerUpCommand(Entity entity) {
        super(entity);
    }

    @Override
    public void execute() {
        entity.powerUp();
    }
}
