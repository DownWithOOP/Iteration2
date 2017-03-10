package model;

import controller.availablecommands.Commandable;
import controller.commands.*;

import java.util.HashMap;

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


    public ActiveState() {

    }

    public void update(Commandable commandable) {
        activeCommandable = commandable;
    }

    public void update(CommandType commandType) {
        activeCommandType = commandType;
    }

    public static void relayActionableCommand(CommandType commandType, Modifier modifier) {
        Command cursorCommand;

        if (checkIfCommandCanBePerformed(activeCommandType)) {
            activeCommand = commandFactory.createActionableCommand(activeCommandType, activeCommandable, modifier);
            activeCommand.execute();
        }

        cursorCommand = commandFactory.createActionableCommand(CommandType.MOVE, cursor, modifier);
        cursorCommand.execute();

    }

    public static void relaySimpleCommand(CommandType commandType) {

        if (commandType == CommandType.SELECT && activeCommand != null) {
            if (checkIfCommandCanBePerformed(activeCommandType)) {
                activeCommand = commandFactory.createSimpleCommand(commandType, activeCommandable);
                activeCommand.execute();
            }
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

}
