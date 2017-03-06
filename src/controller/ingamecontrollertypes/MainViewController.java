package controller.ingamecontrollertypes;

import controller.Controller;
import controller.Observers.MainViewObserver;
import controller.SwitchControllerRelay;
import controller.commands.CommandType;
import controller.inputhandler.MVCInputHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MapObserver;
import model.map.Map;
import utilities.Observer;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


/**
 * Created by Konrad on 2/17/2017.
 */
public class MainViewController extends Controller{


    @FXML
    MenuBar mainMenuBar;
    @FXML
    Canvas canvas;
    @FXML
    VBox vbox;
    private Map currentMap;
    private AreaViewPortController areaViewPortController;

    public MainViewController(){
        super();

    }

    private SwitchControllerRelay switchControllerRelay;

        public void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay){
            this.switchControllerRelay = switchControllerRelay;
        }

    @Override
    protected void enableKeyboardInput() {
        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    CommandType receivedCommand;
                    MVCInputHandler mvcInputHandler = new MVCInputHandler();
                    receivedCommand =mvcInputHandler.interpretInput(event);
                    System.out.println(receivedCommand.toString());
                    if (receivedCommand !=null) {
                        controllerDispatch.handleCommand(receivedCommand);
                    }
                }
        );
    }


        @FXML
        public void drawOnCanvas(ActionEvent actionEvent) throws  IOException{
            if(this.areaViewPortController == null){
                this.areaViewPortController = new AreaViewPortController(vbox, canvas); // TODO, gonna change this
            }
            this.currentMap = this.observer.share();
            this.areaViewPortController.UpdateRenderInfo(currentMap.returnRenderInformation());
            System.out.println(vbox.getChildren().size() + " size");
        }
        @FXML void moveUp(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraYPlus();
        }
        @FXML void moveDown(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraYMinus();
        }
        @FXML void moveLeft(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraXPlus();
        }
        @FXML void moveRight(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraXMinus();
        }
        @FXML void cameraFaster(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.fasterCamera();
        }
        @FXML void cameraSlower(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.slowerCamer();
        }



        @FXML
        public void handleChangeToStructureView(ActionEvent actionEvent) throws  IOException{
            this.switchControllerRelay.changeToStructure();}
        public void handleChangeToUnitView(ActionEvent actionEvent) throws  IOException{
            this.switchControllerRelay.changeToUnit();}
        public void handleChangeToTechTreeView(ActionEvent actionEvent) throws  IOException{
            this.switchControllerRelay.changeToTechTree();}
        @FXML
        public void handleReturnToMainMenu(ActionEvent actionEvent) throws IOException {
            // returnToMainMenu();
        }

        public void handleEndTurn(ActionEvent actionEvent) {
            controllerDispatch.handleCommand(CommandType.END_TURN);
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

