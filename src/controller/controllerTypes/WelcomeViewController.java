package controller.controllerTypes;

import controller.Controller;
import controller.ControllerDispatch;
import controller.ControllerManager;

/**
 * Created by Konrad on 2/17/2017.
 */
public class WelcomeViewController extends Controller {

    public WelcomeViewController(ControllerDispatch controllerDispatch, ControllerManager controllerManager){
        super(controllerDispatch,controllerManager);
    }

    @Override
    protected void resumeController() {

    }

    @Override
    protected void suspendController() {

    }

    @Override
    protected void setView() {

    }
}
