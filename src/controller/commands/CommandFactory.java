package controller.commands;

import controller.availablecommands.Commandable;
import controller.commands.entitycommand.armycommand.DisbandCommand;
import controller.commands.entitycommand.entitycommand.CancelQueueCommand;
import controller.commands.entitycommand.entitycommand.DecommissionCommand;
import controller.commands.entitycommand.armycommand.AttackCommand;
import controller.commands.entitycommand.armycommand.DefendCommand;
import controller.commands.entitycommand.cursorcommand.MoveCommand;
import controller.commands.entitycommand.entitycommand.PowerDownCommand;
import controller.commands.entitycommand.entitycommand.PowerUpCommand;
import controller.commands.entitycommand.unitcommand.AbandonArmyCommand;
import controller.commands.entitycommand.unitcommand.AdvanceToRallyPointCommand;
import controller.commands.entitycommand.unitcommand.JoinArmyCommand;
import model.Cursor;
import model.RallyPoint;
import model.common.Location;
import model.entities.Entity;
import model.entities.unit.*;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.HashMap;

/**
 * Created by jordi on 2/28/2017.
 */
public class CommandFactory {

    static final private HashMap<CommandType, SimpleCommandWrapper> simpleCommandResult = new HashMap<>();
    static final private HashMap<CommandType, ActionableCommandWrapperEntity> actionableCommandEntity = new HashMap<>();

    static {
        simpleCommandResult.put(CommandType.DECOMMISSION, (commandable) -> (new DecommissionCommand((Entity) commandable)));
        simpleCommandResult.put(CommandType.CANCEL_QUEUE, (commandable) -> (new CancelQueueCommand((Entity)commandable)));
        simpleCommandResult.put(CommandType.POWER_UP, (commandable) -> (new PowerUpCommand((Entity)commandable)));
        simpleCommandResult.put(CommandType.POWER_DOWN, (commandable) -> (new PowerDownCommand((Entity)commandable)));

        simpleCommandResult.put(CommandType.DISBAND,(commandable) -> (new DisbandCommand((Army) commandable)));
        simpleCommandResult.put(CommandType.ABANDON_ARMY, (commandable) -> (new AbandonArmyCommand((Unit) commandable)));


        //cursor
        actionableCommandEntity.put(CommandType.MOVE, (commandable, modifier) -> (new MoveCommand((Cursor) commandable, modifier.direction)));
        // TODO add W,A,S,D move_map_Camera stuff

        //army
        actionableCommandEntity.put(CommandType.ATTACK, (commandable, modifier) -> (new AttackCommand((Army) commandable, modifier.direction)));
        actionableCommandEntity.put(CommandType.DEFEND, (commandable, modifier) -> (new DefendCommand((Army) commandable, modifier.direction)));
        actionableCommandEntity.put(CommandType.JOIN_ARMY, (commandable, modifier) -> (new JoinArmyCommand((Unit) commandable, modifier.number)));
        actionableCommandEntity.put(CommandType.ADVANCE_TO_RALLY_POINT, (commandable, modifier) -> (new AdvanceToRallyPointCommand((Unit) commandable, modifier.number)));

    }

    /**
     * Command that can be executed without any modifier
     * @param commandType
     * @param commandable
     * @return
     */
    public Command createSimpleCommand(CommandType commandType, Commandable commandable) {

        try {
            if (simpleCommandResult.containsKey(commandType)) {
                return simpleCommandResult.get(commandType).createCommand(commandable);
            }
        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * takes in all the actions that can be modified
     * attack, one could attack north, south, east, west
     * joinArmy one could join a different army with a different number
     * @param commandType
     * @param commandable
     * @param modifier
     * @return
     */
    public Command createActionableCommand(CommandType commandType, Commandable commandable, Modifier modifier) {

        try {
            if (actionableCommandEntity.containsKey(commandType)) {
                return actionableCommandEntity.get(commandType).createCommand(commandable, modifier);
            }
        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    private interface SimpleCommandWrapper {
        Command createCommand(Commandable commandable);
    }

    private interface ActionableCommandWrapper {
        Command createCommand(Commandable commandable, Direction direction);
    }

    private interface ActionableCommandWrapperEntity {
        Command createCommand(Commandable commandable, Modifier modifier);
    }

    public static void main(String[] args) {
        CustomID customID = new CustomID(IdType.PLAYER, "5");

        Melee melee = new Melee(customID, "6", 0, 0);
        Colonist colonist = new Colonist(customID, "6", 1,1);
        Army army = new Army(customID, "6",2,2);
        RallyPoint rallyPoint = new RallyPoint(new Location(5, 6));
        Cursor cursor = new Cursor(new Location(5, 6));

        Command command;
        CommandFactory commandFactory = new CommandFactory();

        command = commandFactory.createSimpleCommand(CommandType.DECOMMISSION, melee);
        command.execute();

        command = commandFactory.createSimpleCommand(CommandType.ABANDON_ARMY, colonist);
        command.execute();

        command = commandFactory.createSimpleCommand(CommandType.POWER_UP, army);
        command.execute();

        Modifier modifier = new Modifier();
        modifier.setModifier(Direction.EAST);
        modifier.setModifier(5);

        command = commandFactory.createActionableCommand(CommandType.ATTACK, army, modifier);
        command.execute();


        command = commandFactory.createActionableCommand(CommandType.JOIN_ARMY, colonist, modifier);
        command.execute();

        if (colonist.containsCommand(CommandType.ATTACK)) {
            command = commandFactory.createActionableCommand(CommandType.ATTACK, colonist, modifier);
            command.execute();
        }

        modifier.setModifier(Direction.WEST);
        command = commandFactory.createActionableCommand(CommandType.DEFEND, army, modifier);
        command.execute();

        modifier.setModifier(Direction.NORTH);
        command = commandFactory.createActionableCommand(CommandType.MOVE, rallyPoint, modifier);
        command.execute();

        modifier.setModifier(Direction.WEST);
        command = commandFactory.createActionableCommand(CommandType.MOVE, cursor, modifier);
        command.execute();

        modifier.setModifier(Direction.EAST);
        command = commandFactory.createActionableCommand(CommandType.MOVE, cursor, modifier);
        command.execute();


    }

}
