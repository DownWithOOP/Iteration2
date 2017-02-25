package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/25/2017.
 */
public class StructureViewController extends Controller{

    private SwitchControllerRelay switchControllerRelay;
    @Override
    protected void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay) {
        this.switchControllerRelay = switchControllerRelay;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleChangeToMainView(ActionEvent actionEvent) throws IOException {
        this.switchControllerRelay.changeToMain();
    }

    public void keyListener(KeyEvent event) throws IOException {
        //TODO: add key menu shortcuts and command control recognition

    }
}
