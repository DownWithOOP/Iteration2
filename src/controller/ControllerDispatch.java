package controller;

import model.GameModel;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerDispatch {
    private AvailableCommands availableCommands;
    private GameModel gameModel;
//    private HashMap<CommandType,>

    public ControllerDispatch(int playerNumber){
        availableCommands=new AvailableCommands();
        gameModel=new GameModel(playerNumber);
    }
    
    public void handleCommand(){
        //TODO: add available actions in here

    }



}
