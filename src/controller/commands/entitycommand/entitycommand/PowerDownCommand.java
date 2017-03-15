package controller.commands.entitycommand.entitycommand;

import controller.commands.CommandType;
import controller.commands.entitycommand.AbstractCommand;
import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public class PowerDownCommand extends EntityCommand {
    public PowerDownCommand(Entity entity) {
        super(entity, 1);
        super.setCommandType(CommandType.POWER_DOWN);
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getEntity().powerDown();
            return true;
        }
        return false;
    }
}
