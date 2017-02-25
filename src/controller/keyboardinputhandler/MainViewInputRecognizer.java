package controller.keyboardinputhandler;

import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by LesliesLaptop on 2/23/17.
 */
public class MainViewInputRecognizer implements Initializable {

    private SwitchControllerRelay switchControllerRelay;

    public void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay){
        this.switchControllerRelay = switchControllerRelay;
        System.out.println(this.switchControllerRelay.toString());
    }

    @FXML
    MenuBar mainMenuBar;

    @FXML
    public void handleChangeToStructureView(ActionEvent actionEvent) throws  IOException{
        this.switchControllerRelay.changeToStructure();
    }

    public void handleChangeToUnitView(ActionEvent actionEvent) throws  IOException{
        this.switchControllerRelay.changeToUnit();
    }

    @FXML
    public void handleReturnToMainMenu(ActionEvent actionEvent) throws IOException {
       // returnToMainMenu();
    }

    public void returnToMainMenu() throws IOException {
        // TODO: properly end all game logic in Game class when returning to main menu
          this.switchControllerRelay.changeToMain();
    }

    //quit the entire game application
    @FXML
    public void handleQuitGame(ActionEvent event) {
        quitGame();
    }

    public void quitGame() {
        getStage().close();
    }

    @FXML
    public void keyListener(KeyEvent event) throws IOException {
        //TODO: add key menu shortcuts and command control recognition
    }


    @FXML
    private Stage getStage() {
       Stage stage = (Stage) mainMenuBar.getScene().getWindow();
       return stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
