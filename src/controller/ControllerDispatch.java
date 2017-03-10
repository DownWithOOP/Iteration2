package controller;

import controller.Observers.MapDisplayObserver;
import controller.availablecommands.AvailableCommands;
import controller.availablecommands.Commandable;
import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.controllercommands.MoveDown;
import controller.commands.controllercommands.MoveLeft;
import controller.commands.controllercommands.MoveRight;
import controller.commands.controllercommands.MoveUp;
import controller.commands.entitycommand.armycommand.AttackCommand;
import controller.commands.entitycommand.armycommand.DefendCommand;
import controller.commands.entitycommand.armycommand.DisbandCommand;
import controller.commands.entitycommand.entitycommand.CancelQueueCommand;
import controller.commands.entitycommand.entitycommand.DecommissionCommand;
import controller.commands.entitycommand.entitycommand.PowerDownCommand;
import controller.commands.entitycommand.entitycommand.PowerUpCommand;
import controller.commands.entitycommand.structureCommand.capitalcommand.CreateUnitCommand;
import controller.commands.entitycommand.structureCommand.capitalcommand.HealUnitCommand;
import controller.commands.entitycommand.unitcommand.AbandonArmyCommand;
import controller.commands.entitycommand.unitcommand.AdvanceToRallyPointCommand;
import controller.commands.entitycommand.unitcommand.JoinArmyCommand;
import controller.commands.entitycommand.unitcommand.MoveUnitCommand;
import controller.commands.entitycommand.unitcommand.colonistcommand.BuildCapitalCommand;
import controller.commands.entitycommand.unitcommand.explorercommand.ProspectCommand;
import controller.commands.playercommands.*;
import model.GameModel;
import model.entities.Entity;
import model.entities.unit.Army;
import model.entities.unit.Explorer;
import model.entities.unit.Unit;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerDispatch {
    private AvailableCommands availableCommands;
    private GameModel gameModel;
    private HashMap<CommandType, Command> commandHashMap = new HashMap<>();

    public ControllerDispatch(int playerNumber, MapDisplayObserver mapDisplayObserver, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver) {
        availableCommands = new AvailableCommands();
        gameModel = new GameModel(playerNumber, mapDisplayObserver, unitObserver, structureObserver, statusObserver);
        setGameModelMap();
    }

    public void handleCommand(CommandType commandType) {
        //TODO: add available actions in here
        if (commandHashMap.containsKey(commandType)) {
            System.out.println("issuing command: " + commandHashMap.get(commandType).toString());
            commandHashMap.get(commandType).execute();
        }
    }

    public void handleCommandActivation() {
        Entity selectedInstance = (Entity) gameModel.getActivePlayer().getCurrentInstance();
        CommandType selectedCommandType = gameModel.getActivePlayer().getCurrentCommandType();
        System.out.println("Added to Queue: " + selectedCommandType.toString());
        switch(selectedCommandType) {
            case DISBAND:
                selectedInstance.addToQueue(new DisbandCommand((Army)selectedInstance));
                break;
            case POWER_UP:
                selectedInstance.addToQueue(new PowerUpCommand(selectedInstance));
                break;
            case POWER_DOWN:
                selectedInstance.addToQueue(new PowerDownCommand(selectedInstance));
                break;
            case CANCEL_QUEUE:
                selectedInstance.addToQueue(new CancelQueueCommand(selectedInstance));
                break;
            case DECOMISSION:
                selectedInstance.addToQueue(new DecommissionCommand(selectedInstance));
                break;
            case ABANDON_ARMY:
                selectedInstance.addToQueue(new AbandonArmyCommand((Unit)selectedInstance));
                break;
            case PROSPECT:
                selectedInstance.addToQueue(new ProspectCommand((Explorer) selectedInstance));
                break;
            default:
                System.out.print("Invalid command");
                break;
        }
    }

    //TODO: ASK  IF THIS WILL WORK WHEN THE PLAYERS ARE CHANGED
    private void setGameModelMap() {
        commandHashMap.put(CommandType.END_TURN, () -> gameModel.endTurn() );
        commandHashMap.put(CommandType.CYCLE_MODE_NEXT, new CycleModeNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_MODE_PREV, new CycleModePrev(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_TYPE_NEXT, new CycleTypeNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_TYPE_PREV, new CycleTypePrev(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_INSTANCE_NEXT, new CycleInstanceNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_INSTANCE_PREV, new CycleInstancePrev(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_COMMAND_NEXT, new CycleCommandNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_COMMAND_PREV, new CycleCommandPrev(gameModel.getActivePlayer()));

    }

    public void updateActiveController(Controller newActiveController) {
        commandHashMap.put(CommandType.MOVE_CAMERA_UP, new MoveUp(newActiveController));
        commandHashMap.put(CommandType.MOVE_CAMERA_RIGHT, new MoveRight(newActiveController));
        commandHashMap.put(CommandType.MOVE_CAMERA_DOWN, new MoveDown(newActiveController));
        commandHashMap.put(CommandType.MOVE_CAMERA_LEFT, new MoveLeft(newActiveController));
    }

    public int getActivePlayerNumber() {
        return gameModel.getActivePlayerIndex() + 1;
    }
}
