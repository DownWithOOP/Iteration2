package controller.availablecommands;

import controller.commands.Command;
import controller.commands.CommandType;

import java.util.HashMap;

/**
 * Created by jordi on 2/20/2017.
 */
public class AvailableCommands {
    HashMap<CommandType,Command>availableCommands=new HashMap<>();

    public void addActions(Commandable commandable){
        availableCommands.putAll(commandable.getCommands());
    }
    public void removeActions(Commandable commandable){
        HashMap<CommandType,Command> tempCommands=commandable.getCommands();
        for (CommandType key:
                tempCommands.keySet()) {
            availableCommands.remove(key);
        }
    }
}
