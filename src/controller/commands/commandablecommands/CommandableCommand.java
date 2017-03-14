package controller.commands.commandablecommands;

import controller.availablecommands.Commandable;
import controller.commands.entitycommand.AbstractCommand;

/**
 * Created by LesliesLaptop on 3/13/17.
 */
public class CommandableCommand extends AbstractCommand {
    protected Commandable commandable;

    public CommandableCommand(Commandable commandable, int numTurns) {
        super(numTurns);
        this.commandable = commandable;
    }

    public Commandable getCommandable() {
        return commandable;
    }
}
