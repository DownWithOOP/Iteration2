package model;

import controller.availablecommands.AvailableCommands;
import controller.availablecommands.Commandable;
import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.Direction;
import model.common.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jordi on 2/28/2017.
 */
public class Cursor extends Commandable{
    protected Location location;

    static ArrayList<CommandType> cursorCommand = new ArrayList<>();
    static {
        cursorCommand.add(CommandType.MOVE);
    }


    public Cursor(Location location) {
        this.location = location;
    }

    public void moveNorth(){
        //TODO:check in map if this action can be performed
        location.moveNorth();
    }
    public void moveSouth(){
        location.moveSouth();
    }
    public void moveWest(){
        location.moveWest();
    }
    public void moveEast(){
        location.moveEast();
    }
    public void moveNorthEast(){
        moveNorth();
        moveEast();
    }
    public void moveSouthEast(){
        moveSouth();
        moveEast();
    }
    public void moveSouthWest(){
        moveSouth();
        moveWest();
    }
    public void moveNorthWest(){
        moveNorth();
        moveWest();
    }
    public void addCursorToAvailableCommands(AvailableCommands availableCommands){
        HashMap<CommandType,Command>cursorCommand;
//        cursorCommand.put()
    }

    public Location getLocation(){
        return location;
    }

    public void move(Direction direction){
        System.out.println("cursor move "+direction.toString());
    }

}
