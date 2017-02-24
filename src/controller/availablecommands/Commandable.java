package controller.availablecommands;

import controller.commands.Command;
import controller.commands.CommandType;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by jordi on 2/21/2017.
 */
public class Commandable {
    HashMap<CommandType,Command> totalCommands=new HashMap<>();
    protected void removeAvailableCommands(){

    }
    protected void addAvailableCommands(){

    }

    protected void addAllActions(){

    }

    protected HashMap<CommandType,Command> getCommands(){
        return totalCommands;
    }
}
