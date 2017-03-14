package controller.availablecommands;

import controller.commands.Command;
import controller.commands.CommandType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jordi on 2/20/2017.
 */
public class AvailableCommands {
    HashMap<CommandType,Command>availableCommands=new HashMap<>();
    HashMap<CommandType,Command>cursorCommands=new HashMap<>();
    Set<CommandType> direction= new HashSet<>();
    Command activeCommand;


    public AvailableCommands(){
        setDirections();
    }

//    public void executeCommand(CommandType command){
//
//    }
//
//    public void addCommands(Commandable commandable){
//        availableCommands.putAll(commandable.getCommands());
//    }
//    public void removeCommands(Commandable commandable){
//        HashMap<CommandType,Command> tempCommands=commandable.getCommands();
//        for (CommandType key:
//                tempCommands.keySet()) {
//            availableCommands.remove(key);
//        }
//    }
//
//    public void addActiveCommand(Command activeCommand){
//        this.activeCommand=activeCommand;
//
//    }

    private void setDirections(){
        direction.add(CommandType.MOVE_CURSOR_NORTH);
        direction.add(CommandType.MOVE_CURSOR_NORTH_EAST);
        direction.add(CommandType.MOVE_CURSOR_NORTH_WEST);
        direction.add(CommandType.MOVE_CURSOR_SOUTH);
        direction.add(CommandType.MOVE_CURSOR_SOUTH_EAST);
        direction.add(CommandType.MOVE_CURSOR_SOUTH_WEST);

    }
}
