package controller.controllerTypes;

import controller.Controller;
import controller.ControllerDispatch;
import controller.ControllerManager;
import controller.commands.CommandType;

/**
 * Created by Konrad on 2/17/2017.
 */
public class MainViewController extends Controller {

    public MainViewController(ControllerDispatch controllerDispatch, ControllerManager controllerManager){
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

    public void transferAction(){
        controllerDispatch.handleCommand(CommandType.endTurn);
    }

}
