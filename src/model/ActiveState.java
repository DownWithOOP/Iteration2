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
    private Cursor cursor;
    private Commandable activeCommandable;
    private CommandType activeCommandType;
    private CommandFactory commandFactory = new CommandFactory();
    private Command activeCommand;
    private Modifier modifier = null;

    private static ActiveState activeState = new ActiveState();

    private ActiveState(){
        //empty like my soul
    }

    public static ActiveState getInstance(){
        return activeState;
    }

    public void init(Cursor cursor) {
        this.cursor = cursor;
    }

    public void update(Commandable commandable) {
        if (commandable == null) { return; }
        activeCommandable = commandable;
    }

    public void update(CommandType commandType) {
        System.out.println("update command type to " + commandType);
        activeCommandType = commandType;
    }


    /**
     * performs an actionable command, refer to commandFactory to know more about actionableCommand
     * moves cursor to the direction of the command if the action contains a direction
     *
     * @param commandType
     */
    private void relayActionableCommand(CommandType commandType) {

        Command cursorCommand;

        System.out.println("active state relay actionable command, command type " + commandType);
        if (activeCommandType != null && checkIfCommandCanBePerformed(activeCommandType)) {
            activeCommand = commandFactory.createActionableCommand(activeCommandType, activeCommandable, modifier);
            System.out.println("active command not null? " + (activeCommand != null));
            System.out.println("active command constructed");
            if (activeCommand != null) {
                System.out.println("active state relay actionable command active command executed");
                System.out.println(activeCommand);
                activeCommandable.addToQueue(activeCommand);
            }
        }

        //cursor move changed
        if (modifier.getModifierType() == ModifierType.DIRECTION) {

            if (cursor!=null) {
//                cursorCommand = commandFactory.createActionableCommand(CommandType.MOVE, cursor, modifier);
//
//                if (cursorCommand != null) {
//                    System.out.println("active state relay actionable command cursor command executed");
//                    cursorCommand.execute();
//                }

            }
        }

    }

    /**
     * performs a simple Command, refer to CommandFactory to know more about simpleCommand
     *
     * @param commandType
     */
    private void relaySimpleCommand(CommandType commandType) {
        if (commandType == CommandType.ACTIVATE_COMMAND && activeCommandType != null) {
            System.out.println("activating active command of type " + activeCommandType);
            System.out.println("can action be performed? " + checkIfCommandCanBePerformed(activeCommandType));
            if (checkIfCommandCanBePerformed(activeCommandType)) {
                activeCommand = commandFactory.createSimpleCommand(activeCommandType, activeCommandable);

                if (activeCommand != null) {
                    //activeCommand.execute();
                    System.out.println("active state relay simply command active command queued");
                    activeCommandable.addToQueue(activeCommand);
                }
            }
        }

        if (checkCommandAvailability(commandType) && commandType == CommandType.FOCUS) {
            Command tempCommand = commandFactory.createSimpleCommand(commandType, activeCommandable);
            System.out.println("active state relay simply command focus command executed");
            tempCommand.execute();
        }
    }

    private boolean checkCommandAvailability(CommandType commandType) {
        return activeCommandable.containsCommand(commandType);
    }

    private boolean checkIfCommandCanBePerformed(CommandType commandType) {
        if (activeCommandable != null) {
            return (checkCommandAvailability(commandType));
        }
        return false;
    }

    private void clearModifier() {
        modifier = null;
    }

    public void constructModifier(Direction direction) {
        System.out.println("direction modifier constructing");
        clearModifier();
        modifier = new Modifier(direction);
    }

    public void constructModifier(int number) {
        System.out.println("number modifier constructing");
        clearModifier();
        modifier = new Modifier(number);
    }

    public void constructModifier(UnitType unitType) {
        System.out.println("unit type modifier constructing");
        clearModifier();
        modifier = new Modifier(unitType);
    }

    public void constructModifier(StructureType structureType) {
        System.out.println("structure type modifier constructing");
        clearModifier();
        modifier = new Modifier(structureType);
    }

    public Cursor getCursor(){
        return cursor;
    }

    public void relayCommand(CommandType commandType) {
        if (modifier != null) {
            System.out.println("active state relay command, and modifier is " + modifier.getModifierType());
            relayActionableCommand(commandType);
        } else {
            relaySimpleCommand(commandType);
        }
        clearModifier();
    }

    public static void main(String[] args) {
        Cursor cursor = new Cursor(new Location(5, 5));
        //ActiveState activeState = new ActiveState(cursor);
        ActiveState.getInstance().init(cursor);
        ActiveState.getInstance().constructModifier(Direction.EAST);
        //Commandable ranged = new Ranged(new CustomID(IdType.RANGED, "ranged"), "ranged", 5, 5);
        //RallyPoint rallyPoint = new RallyPoint(new Location(5, 5), new Army(new CustomID(IdType.PLAYER,"hello"),"army",5,6));
        //activeCommandable = rallyPoint;
        ActiveState.getInstance().activeCommandType = CommandType.MOVE;
        ActiveState.getInstance().relayCommand(CommandType.MOVE);
        ActiveState.getInstance().relayCommand(CommandType.FOCUS);


        ActiveState.getInstance().constructModifier(5);
        ActiveState.getInstance().relayCommand(CommandType.MOVE);

        ActiveState.getInstance().constructModifier(Direction.SOUTH);
        ActiveState.getInstance().relayCommand(CommandType.MOVE);

        ActiveState.getInstance().constructModifier(Direction.WEST);
        ActiveState.getInstance().relayCommand(CommandType.MOVE);

        //activeState.update(ranged);
        ActiveState.getInstance().activeCommandType = CommandType.JOIN_ARMY;
        ActiveState.getInstance().constructModifier(5);
        ActiveState.getInstance().relayCommand(CommandType.JOIN_ARMY);

        ActiveState.getInstance().activeCommandType = CommandType.DECOMMISSION;
        ActiveState.getInstance().relayCommand(CommandType.ACTIVATE_COMMAND);

        //activeState.update(ranged);
        ActiveState.getInstance().activeCommandType = CommandType.JOIN_ARMY;
        ActiveState.getInstance().constructModifier(6);
        ActiveState.getInstance().relayCommand(CommandType.JOIN_ARMY);

    }

}
