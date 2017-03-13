package model;

import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import controller.commands.Direction;
import model.common.Location;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Jonathen on 2/25/2017.
 */
public class RallyPoint extends Cursor {
    Queue<Location> path= new ArrayDeque<>();

    static ArrayList<CommandType> rallyPointCommand= new ArrayList<>();
    static {
        rallyPointCommand.add(CommandType.FOCUS);
    }


    public RallyPoint(Location location) {
        super(location);
        addAllCommands(rallyPointCommand);
    }

    public void move(Direction direction){
        System.out.print("rally point moves "+direction.toString()+" and then ");
        super.move(direction);
        path.add(location);
    }

    public void focus(){
        System.out.println("FOCUSED RALLY POINT!!!");
        //Todo: send the path queue to the army
    }

}
