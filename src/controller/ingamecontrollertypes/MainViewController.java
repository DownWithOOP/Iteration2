package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Konrad on 2/17/2017.
 */
public class MainViewController extends Controller {

    public MainViewController(){
        super();
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

    private SwitchControllerRelay switchControllerRelay;

        public void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay){
            this.switchControllerRelay = switchControllerRelay;
        }

    @Override
    protected void enableKeyboardInput() {

        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> System.out.println("Pressed: "+event.getCode())
                // TODO to whatever you want with this input
        );
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

        public void transferAction(){

        }
    }

