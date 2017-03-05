package controller;

import controller.Observers.MainViewObserver;
import controller.availablecommands.AvailableCommands;
import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.playercommands.*;
import model.GameModel;
import utilities.Renderer;

import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerDispatch {
    private AvailableCommands availableCommands;
    private GameModel gameModel;
    private HashMap<CommandType, Command> commandHashMap = new HashMap<>();

    public ControllerDispatch(int playerNumber, MainViewObserver mainViewObserver) {
        availableCommands = new AvailableCommands();
        gameModel = new GameModel(playerNumber,mainViewObserver);
        setGameModelMap();
    }

    public void handleCommand(CommandType commandType) {
        //TODO: add available actions in here
        System.out.println("is value null in command map in ControllerDispatch? " + (commandHashMap.get(commandType) == null));
        if (commandHashMap.containsKey(commandType)) {
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

    //TODO don't do this
    public void drawGameModel(Renderer r){
        gameModel.draw(r);
    }

}
