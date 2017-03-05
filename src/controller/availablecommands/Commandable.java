package controller.availablecommands;

import controller.commands.Command;
import controller.commands.CommandType;

import java.util.*;

/**
 * Created by jordi on 2/21/2017.
 */
public abstract class Commandable {
    Set<CommandType> totalCommands = new HashSet<>();


    protected void addAllCommands(ArrayList<CommandType> commandTypes) {
        totalCommands.addAll(commandTypes);
    }

    public boolean containsCommand(CommandType commandType){
        return totalCommands.contains(commandType);
    }

}
