package controller;

import controller.Observers.MapObserver;
import controller.availablecommands.AvailableCommands;
import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.controllercommands.MoveDown;
import controller.commands.controllercommands.MoveLeft;
import controller.commands.controllercommands.MoveRight;
import controller.commands.controllercommands.MoveUp;
import controller.commands.playercommands.*;
import model.GameModel;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerDispatch {
    private AvailableCommands availableCommands;
    private GameModel gameModel;
    private HashMap<CommandType, Command> commandHashMap = new HashMap<>();

    public ControllerDispatch(int playerNumber, MapObserver mapObserver, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver) {
        availableCommands = new AvailableCommands();
        gameModel = new GameModel(playerNumber, mapObserver, unitObserver, structureObserver, statusObserver);
        setGameModelMap();
    }

    public void handleCommand(CommandType commandType) {
        //TODO: add available actions in here
        if (commandHashMap.containsKey(commandType)) {
            System.out.println("issuing command: " + commandHashMap.get(commandType).toString());
            commandHashMap.get(commandType).execute();
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
