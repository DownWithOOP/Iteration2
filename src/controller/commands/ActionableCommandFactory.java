package controller.commands;

import controller.availablecommands.Commandable;
import controller.commands.entitycommand.armycommand.AttackCommand;
import controller.commands.entitycommand.armycommand.DefendCommand;
import controller.commands.entitycommand.cursorcommand.MoveCommand;
import controller.commands.entitycommand.unitcommand.AdvanceToRallyPointCommand;
import controller.commands.entitycommand.unitcommand.JoinArmyCommand;
import controller.commands.modifiers.Modifier;
import controller.commands.modifiers.ModifierType;
import model.Cursor;
import model.entities.unit.Army;
import model.entities.unit.Unit;

import java.util.HashMap;

/**
 * Created by jordi on 3/13/2017.
 */
public class ActionableCommandFactory {

    static final private HashMap<CommandType, ActionableCommandWrapperEntity> actionableCommandDirection = new HashMap<>();
    static final private HashMap<CommandType, ActionableCommandWrapperEntity> actionableCommandNumber = new HashMap<>();
    static final private HashMap<CommandType, ActionableCommandWrapperEntity> actionableCommandUnitType = new HashMap<>();
    static final private HashMap<CommandType, ActionableCommandWrapperEntity> actionableCommandStructureType = new HashMap<>();

    static final private HashMap<ModifierType, ModifierTypeWrapper> actionableCommandType = new HashMap<>();

    static {
        //cursor
        actionableCommandDirection.put(CommandType.MOVE, (commandable, modifier) -> (new MoveCommand((Cursor) commandable, modifier.direction)));
        // TODO add W,A,S,D move_map_Camera stuff

        //army
        actionableCommandDirection.put(CommandType.ATTACK, (commandable, modifier) -> (new AttackCommand((Army) commandable, modifier.direction)));
        actionableCommandDirection.put(CommandType.DEFEND, (commandable, modifier) -> (new DefendCommand((Army) commandable, modifier.direction)));

        actionableCommandNumber.put(CommandType.JOIN_ARMY, (commandable, modifier) -> (new JoinArmyCommand((Unit) commandable, modifier.number)));
        actionableCommandNumber.put(CommandType.ADVANCE_TO_RALLY_POINT, (commandable, modifier) -> (new AdvanceToRallyPointCommand((Unit) commandable, modifier.number)));

        //actionableCommandStructureType.put(CommandType.CREATE_UNIT)


        actionableCommandType.put(ModifierType.DIRECTION, (commandType, commandable, modifier) -> (createDirectionableCommand(commandType, commandable, modifier)));
        actionableCommandType.put(ModifierType.NUMBER, (commandType, commandable, modifier) -> (createNumberedCommand(commandType, commandable, modifier)));
        actionableCommandType.put(ModifierType.STRUCTURE_TYPE, (commandType, commandable, modifier) -> (createStructureTypeCommand(commandType, commandable, modifier)));
        actionableCommandType.put(ModifierType.UNIT_TYPE, (commandType, commandable, modifier) -> (createUnitTypeCommand(commandType, commandable, modifier)));
    }

    private static Command createDirectionableCommand(CommandType commandType, Commandable commandable, Modifier modifier) {

        try {
            if (actionableCommandDirection.containsKey(commandType)) {
                return actionableCommandDirection.get(commandType).createCommand(commandable, modifier);
            }


        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    private static Command createNumberedCommand(CommandType commandType, Commandable commandable, Modifier modifier) {

        try {
            if (actionableCommandNumber.containsKey(commandType)) {
                return actionableCommandNumber.get(commandType).createCommand(commandable, modifier);
            }

        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    private static Command createUnitTypeCommand(CommandType commandType, Commandable commandable, Modifier modifier) {

        try {
            if (actionableCommandNumber.containsKey(commandType)) {
                return actionableCommandUnitType.get(commandType).createCommand(commandable, modifier);
            }

        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    private static Command createStructureTypeCommand(CommandType commandType, Commandable commandable, Modifier modifier) {

        try {
            if (actionableCommandNumber.containsKey(commandType)) {
                return actionableCommandStructureType.get(commandType).createCommand(commandable, modifier);
            }

        } catch (Exception e) {
            System.out.println("check for the class type and the action to be performed");
            e.printStackTrace();
        }

        return null;
    }

    public static Command createCommand(CommandType commandType, Commandable commandable, Modifier modifier) {
        ModifierTypeWrapper modifierTypeWrapper = actionableCommandType.get(modifier.getModifierType());
        Command command = null;
        try {
            command = modifierTypeWrapper.createCommand(commandType, commandable, modifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return command;
    }

}


interface ActionableCommandWrapperEntity {
    Command createCommand(Commandable commandable, Modifier modifier);
}

interface ModifierTypeWrapper {
    Command createCommand(CommandType commandType, Commandable commandable, Modifier modifier);
}
