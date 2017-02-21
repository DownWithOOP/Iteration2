package controller;

import controller.controllerTypes.ControllerType;

/**
 * Created by Konrad on 2/17/2017.
 */
public abstract class Controller {

    // this will be the dispatch that the controller will use to communicate with the model to relay commands and update info
    protected ControllerDispatch controllerDispatch;
    private ControllerManager controllerManager;

    public Controller(ControllerDispatch controllerDispatch, ControllerManager controllerManager){
        this.controllerDispatch = controllerDispatch;
        this.controllerManager=controllerManager;
    }

    protected void switchController(ControllerType controllerType){
        suspendController();
        controllerManager.switchControllers(controllerType);
    }

    /**
     * different controllers require different implementations
     * different procedures need to be resumed for each controller
     * hence we have an abstract class
     */
    protected abstract void resumeController();

    /**
     * different controllers require different implementations
     * different procedures need to be stopped for each controller
     * hence we have an abstract class
     */
    protected abstract void suspendController();
}
