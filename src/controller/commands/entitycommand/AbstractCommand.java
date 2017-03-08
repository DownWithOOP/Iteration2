package controller.commands.entitycommand;

import controller.commands.Command;
import model.entities.Entity;

/**
 * Created by cristian
 */
public abstract class AbstractCommand implements Command {

    private int numTurns;

    public AbstractCommand(int numTurns) {
        this.numTurns = numTurns;
    }

    @Override
    public boolean execute() {

        if(numTurns == 0){
            return true;
        }
        numTurns--;
        return false;
    }


}
