package controller.commands.entitycommand.entitycommand;

import controller.commands.CommandType;
import controller.commands.entitycommand.AbstractCommand;
import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public class PowerUpCommand extends EntityCommand {
    public PowerUpCommand(Entity entity) {
        super(entity, 1);
        super.setCommandType(CommandType.POWER_UP);
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getEntity().powerUp();
            return true;
        }
        return false;
    }
}
