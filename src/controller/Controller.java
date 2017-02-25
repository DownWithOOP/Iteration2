package controller;

import controller.ingamecontrollertypes.ControllerType;
import javafx.fxml.Initializable;

/**
 * Created by Konrad on 2/17/2017.
 */
public abstract class Controller implements Initializable {

    // this will be the dispatch that the controller will use to communicate with the model to relay commands and update info
    protected ControllerDispatch controllerDispatch;

    public Controller(){

    }

    protected void switchController(ControllerType controllerType){
        suspendController();
    }

    protected void setControllerDispatch(ControllerDispatch controllerDispatch){
        this.controllerDispatch = controllerDispatch;
    }


    /**
     * different controllers require different implementations
     * different procedures need to be resumed for each controller
     * hence we have an abstract class
     */
    protected abstract void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay);

    protected abstract void resumeController();

    /**
     * different controllers require different implementations
     * different procedures need to be stopped for each controller
     * hence we have an abstract class
     */
    protected abstract void suspendController();

    /**
     * assign the different views to the different controllers
     */
    protected abstract void setView();

    public void receiveInput(){

    }
    protected void manageInput(){

    }
}
