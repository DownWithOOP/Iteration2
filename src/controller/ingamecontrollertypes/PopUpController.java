package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by LesliesLaptop on 3/14/17.
 */
public class PopUpController extends Controller {
    @FXML
    Label selectStructureLabel;
    @FXML
    Label selectStructLocationLabel;
    @FXML
    Button buildStructButton;
    @FXML
    Button cancelButton;

    @FXML
    public void closePopUp(ActionEvent event) throws IOException {
        getStage().close();
    }

    @FXML
    private Stage getStage() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        return stage;
    }

    @Override
    protected void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay) {

    }

    @Override
    protected void enableKeyboardInput() {

    }

    @Override
    protected void render() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
