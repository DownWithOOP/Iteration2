package controller;

import controller.controllerTypes.ControllerType;
import controller.controllerTypes.MainViewController;
import controller.controllerTypes.WelcomeViewController;

import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerManager {

    HashMap<ControllerType, Controller> controllerMap = new HashMap<>();

    // we need to keep track of the currently active controller
    //TODO: make this private
    public Controller activeController;

    public ControllerManager(ControllerDispatch controllerDispatch) {

        initializeControllers(controllerDispatch);                                                  // initializes all controllers
        this.activeController = this.controllerMap.get(ControllerType.welcomeViewController);       // when the game is first initialized, we start at the welcomeView controller
    }

    private void initializeControllers(ControllerDispatch controllerDispatch) {
        controllerMap.put(ControllerType.mainViewController, new MainViewController(controllerDispatch, this));
        controllerMap.put(ControllerType.welcomeViewController, new WelcomeViewController(controllerDispatch, this));

    }

    public void switchControllers(ControllerType controllerType) {
        activeController = controllerMap.get(controllerType);
        activeController.resumeController();
    }


}
