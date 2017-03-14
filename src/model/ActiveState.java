package model;

import controller.availablecommands.Commandable;
import controller.commands.*;
import controller.commands.modifiers.Modifier;
import controller.commands.modifiers.ModifierType;
import model.common.Location;
import model.entities.structure.StructureType;
import model.entities.unit.Army;
import model.entities.unit.Ranged;
import model.entities.unit.UnitType;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by jordi on 3/6/2017.
 * <p>
 * Keeps track of the active player (this might change)
 * Keeps track of the active unit and the active cursor
 */
public class ActiveState {
    private static Cursor cursor;
    private static Commandable activeCommandable;
    private static CommandType activeCommandType;
    private static CommandFactory commandFactory = new CommandFactory();
    private static Command activeCommand;
    private static Modifier modifier = null;


    public ActiveState(Cursor cursor) {
        this.cursor = cursor;
    }

    public void update(Commandable commandable) {
        activeCommandable = commandable;
    }

    public void update(CommandType commandType) {
        activeCommandType = commandType;
    }


    /**
     * performs an actionable command, refer to commandFactory to know more about actionableCommand
     * moves cursor to the direction of the command if the action contains a direction
     *
     * @param commandType
     */
    private static void relayActionableCommand(CommandType commandType) {
        Command cursorCommand;

        if (activeCommandType != null && checkIfCommandCanBePerformed(activeCommandType)) {
            activeCommand = commandFactory.createActionableCommand(activeCommandType, activeCommandable, modifier);

            if (activeCommand != null) {
                activeCommand.execute();
            }
        }

        if (modifier.getModifierType() == ModifierType.DIRECTION) {

            if (cursor!=null) {
                cursorCommand = commandFactory.createActionableCommand(CommandType.MOVE, cursor, modifier);


                if (cursorCommand != null) {
                    cursorCommand.execute();
                }
            }
        }

    }

    /**
     * performs a simple Command, refer to CommandFactory to know more about simpleCommand
     *
     * @param commandType
     */
    private static void relaySimpleCommand(CommandType commandType) {
        if (commandType == CommandType.ACTIVATE_COMMAND && activeCommandType != null) {
            if (checkIfCommandCanBePerformed(activeCommandType)) {
                activeCommand = commandFactory.createSimpleCommand(activeCommandType, activeCommandable);

                if (activeCommand != null) {
                    //activeCommand.execute();
                    activeCommandable.addToQueue(activeCommand);
                }
            }
        }

        if (checkCommandAvailability(commandType) && commandType == CommandType.FOCUS) {
            Command tempCommand = commandFactory.createSimpleCommand(commandType, activeCommandable);
            tempCommand.execute();
        }
    }

    private static boolean checkCommandAvailability(CommandType commandType) {
        return activeCommandable.containsCommand(commandType);
    }

    private static boolean checkIfCommandCanBePerformed(CommandType commandType) {
        if (activeCommandable != null) {
            return (checkCommandAvailability(commandType));
        }
        return false;
    }

    private static void clearModifier() {
        modifier = null;
    }

    public static void constructModifier(Direction direction) {
        clearModifier();
        modifier = new Modifier(direction);
    }

    public static void constructModifier(int number) {
        clearModifier();
        modifier = new Modifier(number);
    }

    public static void constructModifier(UnitType unitType) {
        clearModifier();
        modifier = new Modifier(unitType);
    }

    public static void constructModifier(StructureType structureType) {
        clearModifier();
        modifier = new Modifier(structureType);
    }

    public static void relayCommand(CommandType commandType) {
        if (modifier != null) {
            relayActionableCommand(commandType);
        } else {
            relaySimpleCommand(commandType);
        }
        clearModifier();
    }

    public static void main(String[] args) {
        Cursor cursor = new Cursor(new Location(5, 5));
        ActiveState activeState = new ActiveState(cursor);

        ActiveState.constructModifier(Direction.EAST);
        Commandable ranged = new Ranged(new CustomID(IdType.RANGED, "ranged"), "ranged", 5, 5);
        RallyPoint rallyPoint = new RallyPoint(new Location(5, 5), new Army(new CustomID(IdType.PLAYER,"hello"),"army",5,6));
        activeCommandable = rallyPoint;
        activeCommandType = CommandType.MOVE;
        ActiveState.relayCommand(CommandType.MOVE);
        ActiveState.relayCommand(CommandType.FOCUS);


        ActiveState.constructModifier(5);
        ActiveState.relayCommand(CommandType.MOVE);

        ActiveState.constructModifier(Direction.SOUTH);
        ActiveState.relayCommand(CommandType.MOVE);

        ActiveState.constructModifier(Direction.WEST);
        ActiveState.relayCommand(CommandType.MOVE);

        activeState.update(ranged);
        activeCommandType = CommandType.JOIN_ARMY;
        ActiveState.constructModifier(5);
        ActiveState.relayCommand(CommandType.JOIN_ARMY);

        activeCommandType = CommandType.DECOMMISSION;
        ActiveState.relayCommand(CommandType.ACTIVATE_COMMAND);

        activeState.update(ranged);
        activeCommandType = CommandType.JOIN_ARMY;
        ActiveState.constructModifier(6);
        ActiveState.relayCommand(CommandType.JOIN_ARMY);

    }

}
