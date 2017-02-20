package controller;

import controller.commands.TypeOfCommand;
import model.GameModel;

import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerDispatch {
    private AvailableCommands availableCommands;
    private GameModel gameModel;
//    private HashMap<TypeOfCommand,>

    public ControllerDispatch(int playerNumber){
        availableCommands=new AvailableCommands();
        gameModel=new GameModel(playerNumber);
    }
    
    public void handleCommand(){
        //TODO: add available actions in here

    }



}
