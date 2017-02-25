package controller;

import controller.controllerTypes.ControllerType;
import controller.controllerTypes.MainViewController;
import controller.controllerTypes.WelcomeViewController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerManager implements Initializable{

    HashMap<ControllerType, Controller> controllerMap = new HashMap<>();

    // we need to keep track of the currently active controller
    //TODO: make this private
    public Controller activeController;

    public ControllerManager() {

        initializeControllers(new ControllerDispatch(2));                                                  // initializes all controllers
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
