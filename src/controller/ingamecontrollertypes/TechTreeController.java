package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/26/2017.
 */
public class TechTreeController extends Controller {

    @FXML
    private Pane ap;
    private SwitchControllerRelay switchControllerRelay;
    @Override
    protected void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay) {
        this.switchControllerRelay = switchControllerRelay;
    }

    @Override
    protected void enableKeyboardInput() {
        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> System.out.println("Pressed: "+event.getCode())
                // TODO to whatever you want with this input
        );
    }

    @Override
    protected void render() {

    }

    @FXML
    private Stage getStage() {
        Stage stage = (Stage) ap.getScene().getWindow();
        return stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleChangeToMainView(ActionEvent actionEvent) throws IOException {
        this.switchControllerRelay.changeToMain();
    }
}
