package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.ObserverInterfaces.StructureObserver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/25/2017.
 */
public class StructureViewController extends Controller{

    private SwitchControllerRelay switchControllerRelay;
    private StructureObserver structureObserver; // observer from model that has all the info we need for rendering/display

    public StructureViewController(){
        super();
    }

    @FXML
    private Pane ap;

    @Override
    protected void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay) {
        this.switchControllerRelay = switchControllerRelay;
    }

    @Override
    protected void enableKeyboardInput() {
        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    if (event.getCode() == KeyCode.U) { //shortcut to Unit Overview
                        // Swap to Structure Overview screen
                        try {
                            this.switchControllerRelay.changeToUnit();
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

    public void setObservers(StructureObserver structureObserver){
        this.structureObserver = structureObserver;
    }

    @Override
    protected void render() {

    }

    @Override
    public void moveLeft() {
        System.out.println("Cannot move left in StructureView");
    }

    @Override
    public void moveUp() {
        System.out.println("Cannot move up in StructureView");
    }

    @Override
    public void moveRight() {
        System.out.println("Cannot move right in StructureView");
    }

    @Override
    public void moveDown() {
        System.out.println("Cannot move down in StructureView");
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

    public void keyListener(KeyEvent event) throws IOException {
        //TODO: add key menu shortcuts and command control recognition

    }
}
