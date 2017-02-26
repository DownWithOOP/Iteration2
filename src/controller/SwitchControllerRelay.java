package controller;

import java.io.IOException;

/**
 * Created by Konrad on 2/25/2017.
 */
public class SwitchControllerRelay {

    private ControllerManager controllerManager;

    public SwitchControllerRelay(ControllerManager controllerManager){
        this.controllerManager = controllerManager;
    }

    public void changeToMain() throws IOException {
        this.controllerManager.changeToMainView();
    }
    public void changeToStructure() throws IOException{
        this.controllerManager.changeToStructureView();
    }
    public void changeToUnit() throws IOException{
        this.controllerManager.changeToUnitView();
    }
}
