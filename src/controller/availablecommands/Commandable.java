package controller.availablecommands;

import controller.commands.Command;
import controller.commands.CommandType;

import java.util.*;

/**
 * Created by jordi on 2/21/2017.
 */
public abstract class Commandable {
    private Set<CommandType> totalCommands = new HashSet<>();
    private ArrayList<CommandType> iterableCommands = new ArrayList<>();
    private int commandIterator = 0;

    protected void addAllCommands(ArrayList<CommandType> commandTypes) {
        totalCommands.addAll(commandTypes);
        iterableCommands.addAll(commandTypes);
    }

    public boolean containsCommand(CommandType commandType) {
        System.out.println(totalCommands.contains(commandType));
        return totalCommands.contains(commandType);
    }

    /**
     * if you want to iterate through the commandable
     * @return
     */
    public CommandType nextCommand() {
        commandIterator++;
        commandIterator %= iterableCommands.size();
        return iterableCommands.get(commandIterator);
    }

    /**
     * if you want to iterate through the commandable
     * @return
     */
    public CommandType previousCommand() {
        commandIterator--;
        commandIterator %= iterableCommands.size();
        return iterableCommands.get(commandIterator);
    }

    /**
     * use to get all the commands the Commandable can perform
     * @return
     */
    public ArrayList<CommandType> getIterableCommands(){
        ArrayList<CommandType> temp= new ArrayList<>();
        temp.addAll(iterableCommands);
        return temp;
    }
}
