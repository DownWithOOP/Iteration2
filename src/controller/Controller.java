package controller;

/**
 * Created by Konrad on 2/17/2017.
 */
public abstract class Controller {

    // this will be the dispatch that the controller will use to communicate with the model to relay commands and update info
    private ControllerDispatch controllerDispatch;

    public Controller(ControllerDispatch controllerDispatch){
        this.controllerDispatch = controllerDispatch;
    }

}
