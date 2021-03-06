package controller.availablecommands;

import controller.CommandRelay;
import controller.commands.Command;
import controller.commands.CommandType;

import java.util.*;

/**
 * Created by jordi on 2/21/2017.
 */
public abstract class Commandable {
    private Set<CommandType> totalCommands = new HashSet<>();
    private ArrayList<CommandType> iterableCommands = new ArrayList<>();
    protected static ArrayList<CommandType> commandableCommand = new ArrayList<>();
    private Queue<Command> commandQueue = new ArrayDeque<>();
    private Command currentCommand;
    private int commandIterator = 0;

    protected CommandRelay commandRelay;

    public Commandable(CommandRelay commandRelay) {
        this.commandRelay = commandRelay;
    }

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

    public CommandType getIterableCommand(int index) {
        return iterableCommands.get(index);
    }

    public int getIterableCommandsSize() {
        return iterableCommands.size();
    }

    public void addToQueue(Command command) {
        System.out.println("ADDING TO QUEUE " + command);
        commandQueue.add(command);
    }

    //TODO: Get cancelQueue to work properly
    public void cancelQueue() {
        while(!commandQueue.isEmpty()) {
            System.out.println("Cancelling queue");
            commandQueue.poll();
        }
        currentCommand = null;
    }

    public void executeQueue(){
        if(currentCommand == null){
            //System.out.println("hell0");
            if(!commandQueue.isEmpty()){
                System.out.println("Not yet kiddo");
                currentCommand = commandQueue.poll();
            }
        }
        if (currentCommand != null) {
            if(currentCommand.execute()){
                System.out.println("Command got executed");
                currentCommand = commandQueue.poll();
            }
        }
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    // Get a list of all missions/commands currently in the commandable's queue
    public String missionsToString() {
        String str = "";
        for (Command command : commandQueue) {
            str += command.getCommandType().toString() + "\n";
        }
        return str;
    }
}
