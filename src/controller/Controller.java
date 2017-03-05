package controller;

import controller.ingamecontrollertypes.ControllerType;
import javafx.fxml.Initializable;
import utilities.Renderer;

/**
 * Created by Konrad on 2/17/2017.
 */
public abstract class Controller implements Initializable {

    // this will be the dispatch that the controller will use to communicate with the model to relay commands and update info
    protected ControllerDispatch controllerDispatch;
    protected AbstractObserver observer;
    private Renderer renderer;

    public Controller(){

    }

    protected void setDispatch(ControllerDispatch controllerDispatch){
        this.controllerDispatch = controllerDispatch;
    }

    protected void setRenderer(Renderer r) {
        this.renderer = r;
    }

    public Renderer getRenderer() {
        return renderer;
    }


    /**
     * different controllers require different implementations
     * different procedures need to be resumed for each controller
     * hence we have an abstract class
     */

    // this method take in the observer object that communicates wiht the model
    protected void takeInObserver(AbstractObserver observer){
        this.observer = observer;
    }

    // this method is just bad OOP but it's the only way with FXML it would work
    protected abstract void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay);

    // method used by controller manager to active event handler for input for the entire scene
    protected abstract void enableKeyboardInput();
}
