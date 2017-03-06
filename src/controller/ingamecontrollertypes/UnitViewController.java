package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.RenderInformation.UnitRenderInformation;
import model.RenderInformation.UnitRenderObject;
import utilities.ObserverInterfaces.UnitObserver;
import java.util.ArrayList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/25/2017.
 */
public class UnitViewController extends Controller
{
    @FXML
    private Pane ap;
    @FXML
    private ScrollPane pane;
    private SwitchControllerRelay switchControllerRelay;
    private UnitObserver unitObserver; // this observer gets all the information from the model that we need for rendering/displaying
    @Override
    protected void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay) {
        this.switchControllerRelay = switchControllerRelay;
    }

    @Override
    protected void enableKeyboardInput() {
        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    if (event.getCode() == KeyCode.S) { //shortcut to Structure Overview
                        // Swap to Structure Overview screen
                        try {
                            this.switchControllerRelay.changeToStructure();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (event.getCode() == KeyCode.M) { //shortcut to Main view
                        try {
                            this.switchControllerRelay.changeToMain();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    @Override
    protected void render() {
        UnitRenderInformation unitRenderInformation = unitObserver.share();
        ArrayList<UnitRenderObject> data = unitRenderInformation.returnRenderInformation();
        VBox vb = new VBox();
        for(int i=0; i<data.size(); i++){
            UnitRenderObject renderObject = data.get(i);
            Label label = new Label();
            label.setText("Type: " +renderObject.getIdType()+ "             locationX: " +renderObject.getLocationX()+ "            locationY: "  +renderObject.getLocationY());
            vb.getChildren().add(label);
        }
        pane.setContent(vb);
    }

    public void setObservers(UnitObserver unitObserver){
        this.unitObserver = unitObserver;
    }

    @FXML
    private Stage getStage() {
        Stage stage = (Stage) ap.getScene().getWindow();
        return stage;
    }

    public void handleChangeToMainView(ActionEvent actionEvent) throws IOException {
        this.switchControllerRelay.changeToMain();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
