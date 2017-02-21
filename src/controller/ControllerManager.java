package controller;

import controller.controllerTypes.ControllerType;
import controller.controllerTypes.MainViewController;
import controller.controllerTypes.WelcomeViewController;

import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerManager {

    // TODO make a map or something to keep track of these
    HashMap<ControllerType,Controller> controllerMap;

    // we need to keep track of the currently active controller
    private Controller activeController;

    public ControllerManager(ControllerDispatch controllerDispatch){
        // intialize
            initializeControllers(controllerDispatch);
        // when the game is first initialized, we start at the welcomeView controller
        this.activeController = this.controllerMap.get(ControllerType.welcomeViewController);
    }

    private void initializeControllers(ControllerDispatch controllerDispatch){
        controllerMap.put(ControllerType.mainViewController,new MainViewController(controllerDispatch,this));
        controllerMap.put(ControllerType.welcomeViewController,new WelcomeViewController(controllerDispatch,this));

    }


}
