package controller.commands.commandablecommands;

import controller.availablecommands.Commandable;
import controller.commands.entitycommand.AbstractCommand;
import controller.commands.entitycommand.entitycommand.EntityCommand;
import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public class CancelQueueCommand extends CommandableCommand {
    public CancelQueueCommand(Commandable commandable) {
        super(commandable, 0);
    }

    @Override
    public boolean execute() {
        getCommandable().cancelQueue();
        return true;
    }
}
