package controller.commands;

import controller.commands.entitycommand.DecommissionCommand;
import controller.commands.entitycommand.armycommand.AttackCommand;
import controller.commands.entitycommand.armycommand.DefendCommand;
import controller.commands.entitycommand.cursorcommand.MoveCommand;
import model.Cursor;
import model.RallyPoint;
import model.common.Location;
import model.entities.Entity;
import model.entities.unit.Army;
import model.entities.unit.Colonist;
import model.entities.unit.Melee;
import model.entities.unit.Ranged;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.HashMap;

/**
 * Created by jordi on 2/28/2017.
 */
public class CommandFactory {

    static final private HashMap<CommandType,SimpleCommandWrapper> simpleCommandResult = new HashMap<>();
    static final private HashMap<CommandType,ActionableCommandWrapper> actionableCommandResult = new HashMap<>();
    static final private HashMap<CommandType,ActionableCommandWrapperArmy> actionableCommandArmy = new HashMap<>();

    static {
        simpleCommandResult.put(CommandType.DECOMISSION, (entity) -> (new DecommissionCommand(entity)));

        //cursor
        actionableCommandResult.put(CommandType.MOVE, (cursor, direction) -> (new MoveCommand(cursor, direction)));
        //army
        actionableCommandArmy.put(CommandType.ATTACK, (army, direction) -> (new AttackCommand(army, direction)));
        actionableCommandArmy.put(CommandType.DEFEND, (army, direction) -> (new DefendCommand(army, direction)));

    }

    public Command createSimpleCommand(CommandType commandType, Entity entity){

        if (simpleCommandResult.containsKey(commandType)) {
            return simpleCommandResult.get(commandType).createCommand(entity);
        }

        return null;
    }

    public Command createActionableCommand(CommandType commandType, Cursor cursor, Direction direction){

        if (actionableCommandResult.containsKey(commandType)) {
            return actionableCommandResult.get(commandType).createCommand(cursor, direction);
        }

        return null;
    }

    public Command createActionableCommand(CommandType commandType, Army army, Direction direction){

        if (actionableCommandArmy.containsKey(commandType)) {
            return actionableCommandArmy.get(commandType).createCommand(army, direction);
        }

        return null;
    }

    private interface SimpleCommandWrapper {
        Command createCommand(Entity entity);
    }

    private interface ActionableCommandWrapper {
        Command createCommand(Cursor cursor, Direction direction);
    }

    private interface ActionableCommandWrapperArmy {
        Command createCommand(Army army, Direction direction);
    }

    public static void main(String[] args) {
        CustomID customID= new CustomID(IdType.player,"5");

        Melee melee= new Melee(customID,"6");
        Colonist colonist= new Colonist(customID,"6");
        Army army= new Army(customID,"6");
        RallyPoint rallyPoint= new RallyPoint(new Location(5,6));
        Cursor cursor= new Cursor(new Location(5,6));

        Command command;
        CommandFactory commandFactory= new CommandFactory();

        command=commandFactory.createSimpleCommand(CommandType.DECOMISSION, melee);
        command.execute();

        command=commandFactory.createSimpleCommand(CommandType.DECOMISSION, colonist);
        command.execute();

        command=commandFactory.createSimpleCommand(CommandType.DECOMISSION, army);
        command.execute();

        command=commandFactory.createActionableCommand(CommandType.ATTACK,army,Direction.EAST);
        command.execute();

        command=commandFactory.createActionableCommand(CommandType.DEFEND,army,Direction.WEST);
        command.execute();

        command=commandFactory.createActionableCommand(CommandType.MOVE,rallyPoint,Direction.WEST);
        command.execute();

        command=commandFactory.createActionableCommand(CommandType.MOVE,cursor,Direction.WEST);
        command.execute();


    }

}
