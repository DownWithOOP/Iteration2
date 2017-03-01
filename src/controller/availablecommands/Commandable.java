package controller.availablecommands;

import controller.commands.Command;
import controller.commands.CommandType;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by jordi on 2/21/2017.
 */
public class Commandable {
    HashMap<CommandType, Command> totalCommands = new HashMap<>();
    AvailableCommands availableCommands;

    protected void removeAvailableCommands() {
        if (availableCommands!=null){
            availableCommands.removeCommands(this);
        }
    }

    protected void addAvailableCommands(AvailableCommands availableCommands) {
        this.availableCommands = availableCommands;
        if (availableCommands!=null){
            availableCommands.addCommands(this);
        }
    }

    protected void addAllActions() {

    }

    public void addAction(){

    }

    protected HashMap<CommandType, Command> getCommands() {
        return totalCommands;
    }
}
