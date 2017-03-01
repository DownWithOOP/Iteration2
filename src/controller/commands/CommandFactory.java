package controller.commands;

import controller.commands.entitycommand.armycommand.DisbandCommand;
import controller.commands.entitycommand.entitycommand.CancelQueueCommand;
import controller.commands.entitycommand.entitycommand.DecommissionCommand;
import controller.commands.entitycommand.armycommand.AttackCommand;
import controller.commands.entitycommand.armycommand.DefendCommand;
import controller.commands.entitycommand.cursorcommand.MoveCommand;
import controller.commands.entitycommand.entitycommand.PowerDownCommand;
import controller.commands.entitycommand.entitycommand.PowerUpCommand;
import controller.commands.entitycommand.unitcommand.AbandonArmy;
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
    static final private HashMap<CommandType, ActionableCommandWrapper> actionableCommandResult = new HashMap<>();
    static final private HashMap<CommandType, ActionableCommandWrapperEntity> actionableCommandEntity = new HashMap<>();

    static {
        simpleCommandResult.put(CommandType.DECOMISSION, (entity) -> (new DecommissionCommand(entity)));
        simpleCommandResult.put(CommandType.CANCEL_QUEUE, (entity) -> (new CancelQueueCommand(entity)));
        simpleCommandResult.put(CommandType.POWER_UP, (entity) -> (new PowerUpCommand(entity)));
        simpleCommandResult.put(CommandType.POWER_DOWN, (entity) -> (new PowerDownCommand(entity)));

        simpleCommandResult.put(CommandType.DISBAND,(entity) -> (new DisbandCommand((Army) entity)));
        simpleCommandResult.put(CommandType.ABANDON_ARMY, (entity) -> (new AbandonArmy((Unit) entity)));


        //cursor
        actionableCommandResult.put(CommandType.MOVE, (cursor, direction) -> (new MoveCommand(cursor, direction)));
        //army
        actionableCommandEntity.put(CommandType.ATTACK, (entity, modifier) -> (new AttackCommand((Army) entity, modifier.direction)));
        actionableCommandEntity.put(CommandType.DEFEND, (entity, modifier) -> (new DefendCommand((Army) entity, modifier.direction)));
        actionableCommandEntity.put(CommandType.JOIN_ARMY, (entity, modifier) -> (new JoinArmyCommand((Unit) entity, modifier.number)));
        actionableCommandEntity.put(CommandType.ADVANCE_TO_RALLY_POINT, (entity, modifier) -> (new AdvanceToRallyPointCommand((Unit) entity, modifier.number)));

    }

    /**
     * Command that can be executed without any modifier
     * @param commandType
     * @param entity
     * @return
     */
    public Command createSimpleCommand(CommandType commandType, Entity entity) {

        try {
            if (simpleCommandResult.containsKey(commandType)) {
                return simpleCommandResult.get(commandType).createCommand(entity);
            }
        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * For cursors the game cursor or the rallyPoint
     * @param commandType
     * @param cursor
     * @param direction
     * @return
     */
    public Command createActionableCommand(CommandType commandType, Cursor cursor, Direction direction) {

        if (actionableCommandResult.containsKey(commandType)) {
            return actionableCommandResult.get(commandType).createCommand(cursor, direction);
        }

        return null;
    }

    /**
     * takes in all the actions that can be modified
     * attack, one could attack north, south, east, west
     * joinArmy one could join a different army with a different number
     * @param commandType
     * @param entity
     * @param modifier
     * @return
     */
    public Command createActionableCommand(CommandType commandType, Entity entity, Modifier modifier) {

        try {
            if (actionableCommandEntity.containsKey(commandType)) {
                return actionableCommandEntity.get(commandType).createCommand(entity, modifier);
            }
        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    private interface SimpleCommandWrapper {
        Command createCommand(Entity entity);
    }

    private interface ActionableCommandWrapper {
        Command createCommand(Cursor cursor, Direction direction);
    }

    private interface ActionableCommandWrapperEntity {
        Command createCommand(Entity entity, Modifier modifier);
    }

    public static void main(String[] args) {
        CustomID customID = new CustomID(IdType.player, "5");

        Melee melee = new Melee(customID, "6");
        Colonist colonist = new Colonist(customID, "6");
        Army army = new Army(customID, "6");
        RallyPoint rallyPoint = new RallyPoint(new Location(5, 6));
        Cursor cursor = new Cursor(new Location(5, 6));

        Command command;
        CommandFactory commandFactory = new CommandFactory();

        command = commandFactory.createSimpleCommand(CommandType.DECOMISSION, melee);
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

        command = commandFactory.createActionableCommand(CommandType.MOVE, rallyPoint, Direction.WEST);
        command.execute();

        command = commandFactory.createActionableCommand(CommandType.MOVE, cursor, Direction.WEST);
        command.execute();

        command = commandFactory.createActionableCommand(CommandType.MOVE, cursor, Direction.EAST);
        command.execute();


    }

}
