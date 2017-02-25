package controller;

import controller.availablecommands.AvailableCommands;
import controller.commands.CommandType;
import model.GameModel;

import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerDispatch {
    private AvailableCommands availableCommands;
    private GameModel gameModel;
    private HashMap<CommandType, GameModelCommand> gameModelMap = new HashMap<>();

    public ControllerDispatch(int playerNumber) {
        availableCommands = new AvailableCommands();
        gameModel = new GameModel(playerNumber);
        setGameModelMap();
    }

    public void handleCommand(CommandType commandType) {
        //TODO: add available actions in here
        gameModelMap.get(commandType).execute();
    }

    private void setGameModelMap() {
        gameModelMap.put(CommandType.endTurn, new GameModelCommand() {
            @Override
            public void execute() {
                gameModel.endTurn();
            }
        });
    }

}

interface GameModelCommand {
    void execute();
}