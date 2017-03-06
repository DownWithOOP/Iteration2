package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import controller.commands.CommandType;
import controller.inputhandler.MVCInputHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.map.Map;
import utilities.ObserverInterfaces.MapObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Konrad on 2/17/2017.
 */
public class MainViewController extends Controller{


    @FXML
    MenuBar mainMenuBar;
    @FXML
    MenuItem endTurnMenuItem;
    @FXML
    Canvas canvas;
    @FXML
    VBox vbox;
    @FXML
    Label coordinateInfo;
    private Map currentMap;
    private AreaViewPortController areaViewPortController;
    private MapObserver mapObserver;
    private UnitObserver unitObserver;
    private StructureObserver structureObserver;

    public MainViewController(){
        super();

    }
    public void setObservers(MapObserver mapObserver, UnitObserver unitObserver, StructureObserver structureObserver){
        this.mapObserver = mapObserver;
        this.unitObserver = unitObserver;
        this.structureObserver = structureObserver;
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

    @Override
    protected void render() {
        this.areaViewPortController.UpdateRenderInfo(this.mapObserver.share()); // displays the map
    }

    @FXML
        public void drawOnCanvas() throws  IOException{
               // this.areaViewPortController.UpdateRenderInfo(this.mapObserver.share());

        }
        @FXML void moveUp(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraYPlus(); // TODO hook this up to some keyboard input
        }
        @FXML void moveDown(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraYMinus(); // TODO hook this up to some keyboard input
        }
        @FXML void moveLeft(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraXPlus(); // TODO hook this up to some keyboard input
        }
        @FXML void moveRight(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.changeCameraXMinus(); // TODO hook this up to some keyboard input
        }
        @FXML void cameraFaster(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.fasterCamera();  // TODO hook this up to some keyboard input
        }
        @FXML void cameraSlower(ActionEvent actionEvent) throws  IOException{
            this.areaViewPortController.slowerCamer();  // TODO hook this up to some keyboard input
        }
        @FXML void selectNorth() throws IOException{
            this.areaViewPortController.selectNorth(); // TODO hook this up to some keyboard input
            updateCoordinatesForDebugging();
        }
        @FXML void selectSouth() throws IOException{
            this.areaViewPortController.selectSouth(); // TODO hook this up to some keyboard input
            updateCoordinatesForDebugging();
        }
        @FXML void selectNE() throws IOException{
            this.areaViewPortController.selectNE(); // TODO hook this up to some keyboard input
            updateCoordinatesForDebugging();
        }
        @FXML void selectSE() throws IOException{
            this.areaViewPortController.selectSE(); // TODO hook this up to some keyboard input
            updateCoordinatesForDebugging();
        }
        @FXML void selectSW() throws IOException{
            this.areaViewPortController.selectSW(); // TODO hook this up to some keyboard input
            updateCoordinatesForDebugging();
        }
        @FXML void selectNW() throws IOException{
            this.areaViewPortController.selectNW();  // TODO hook this up to some keyboard input
            updateCoordinatesForDebugging();
        }
        private void updateCoordinatesForDebugging(){ // for debugging, once game is working we can get rid of this
            this.coordinateInfo.setText(areaViewPortController.returnXCoordinate() + " " + areaViewPortController.returnYCoordinate());
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
            endTurnMenuItem.setText("Player " + controllerDispatch.getActivePlayerNumber());
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
        public void initialize(URL location, ResourceBundle resources) { // initialized the component correctly
            this.areaViewPortController = new AreaViewPortController(vbox, canvas);
        }


}

