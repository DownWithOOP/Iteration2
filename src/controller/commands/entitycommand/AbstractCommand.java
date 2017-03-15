package controller.commands.entitycommand;

import controller.commands.Command;
import controller.commands.CommandType;
import model.entities.Entity;

/**
 * Created by cristian
 */
public abstract class AbstractCommand implements Command {

    private int numTurns;
    protected CommandType commandType;

    public AbstractCommand(int numTurns) {
        this.numTurns = numTurns;
    }

    @Override
    public boolean execute() {

        if(--numTurns == 0){
            return true;
        }
        //numTurns--;
        return false;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }


}
