package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    ChoiceBox<String> structureComboBox;
    @FXML
    Button buildStructButton;
    @FXML
    ComboBox<String> unitsComboBox;
    @FXML
    ChoiceBox<String> armiesComboBox;
    @FXML
    ChoiceBox<String> healUnitComboBox;
    @FXML
    Button cancelButton;

    public PopUpController() {
        super();
    }

    @FXML
    public void handleBuildStructure(ActionEvent event) throws IOException {
        String structureType = structureComboBox.getSelectionModel().getSelectedItem();
        if (structureType != null) {
            closePopUp(event);
            this.controllerDispatch.handleBuildStructureCommand(structureType);

        }
    }

    @FXML
    public void handleCreateUnit(ActionEvent event) throws IOException {
        String unitType = unitsComboBox.getSelectionModel().getSelectedItem();
        if (unitType != null) {
            closePopUp(event);
            this.controllerDispatch.handleCreateUnitCommand(unitType);
        }
    }

    // TODO: PULL UP LIST OF ENTITY IDS IN COMBOBOX
    @FXML
    public void handleHealUnit(ActionEvent event) throws IOException {
        String unitStr = healUnitComboBox.getSelectionModel().getSelectedItem();
        if (unitStr != null) {
            closePopUp(event);
            this.controllerDispatch.handleCreateUnitCommand(unitStr);
        }
    }

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
