package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import controller.commands.CommandType;
import controller.inputhandler.MVCInputHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.map.Map;
import utilities.ObserverInterfaces.MapObserver;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;
import view.AreaViewport;
import view.StatusViewport;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


/**
 * Created by Konrad on 2/17/2017.
 */
public class MainViewController extends Controller{


    @FXML
    MenuBar mainMenuBar;
    @FXML
    Label currentPlayerLabel;
    @FXML
    Canvas canvas;
    @FXML
    VBox vbox;
    @FXML
    Label modeLabel;
    @FXML
    Label typeLabel;
    @FXML
    Label instanceLabel;
    @FXML
    Label commandLabel;
    @FXML
    Label coordinateInfo;
    @FXML
    MenuItem resourceOverlay;
    @FXML
    MenuItem foodOverlay;
    @FXML
    MenuItem oreOverlay;
    @FXML
    MenuItem energyOverlay;

    private Map currentMap;
    private AreaViewport areaViewport;
    private StatusViewport statusViewport;
    private MapObserver mapObserver;
    private UnitObserver unitObserver;
    private StructureObserver structureObserver;
    private StatusObserver statusObserver;
    private java.util.Map<String, Label> cycleLabels = new HashMap<>();

    public MainViewController(){
        super();

    }
    public void setObservers(MapObserver mapObserver, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver){
        this.mapObserver = mapObserver;
        this.unitObserver = unitObserver;
        this.structureObserver = structureObserver;
        this.statusObserver = statusObserver;
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
                    receivedCommand = mvcInputHandler.interpretInput(event);
                    if (receivedCommand != null) {
                        System.out.println(receivedCommand.toString());
                        controllerDispatch.handleCommand(receivedCommand);
                    }
                }
        );
    }

    @Override
    protected void render() {
        this.areaViewport.UpdateRenderInfo(this.mapObserver.share(), this.unitObserver.share(), this.structureObserver.share()); // displays the map
        this.statusViewport.updateRenderInfo(this.statusObserver.share());
    }

    //Move methods for moving with keyboard - JS
    @Override
    public void moveLeft() {
        areaViewport.changeCameraXPlus();
    }
    @Override
    public void moveUp() {
        areaViewport.changeCameraYPlus();
    }

    @Override
    public void moveRight() {
        areaViewport.changeCameraXMinus();
    }

    @Override
    public void moveDown() {
        areaViewport.changeCameraYMinus();
    }

    //Move methods called by buttons on view - JS
    @FXML void moveUp(ActionEvent actionEvent) throws  IOException{
            this.areaViewport.changeCameraYPlus(); // TODO hook this up to some keyboard input
    }
    @FXML void moveDown(ActionEvent actionEvent) throws  IOException{
        this.areaViewport.changeCameraYMinus(); // TODO hook this up to some keyboard input
    }
    @FXML void moveLeft(ActionEvent actionEvent) throws  IOException{
        this.areaViewport.changeCameraXPlus(); // TODO hook this up to some keyboard input
    }
    @FXML void moveRight(ActionEvent actionEvent) throws  IOException{
        this.areaViewport.changeCameraXMinus(); // TODO hook this up to some keyboard input
    }
    @FXML void cameraFaster(ActionEvent actionEvent) throws  IOException{
        this.areaViewport.fasterCamera();  // TODO hook this up to some keyboard input
    }
    @FXML void cameraSlower(ActionEvent actionEvent) throws  IOException{
        this.areaViewport.slowerCamer();  // TODO hook this up to some keyboard input
    }
    @FXML void selectNorth() throws IOException{
        this.areaViewport.selectNorth(); // TODO hook this up to some keyboard input
        updateCoordinatesForDebugging();
    }
    @FXML void selectSouth() throws IOException{
        this.areaViewport.selectSouth(); // TODO hook this up to some keyboard input
        updateCoordinatesForDebugging();
    }
    @FXML void selectNE() throws IOException{
        this.areaViewport.selectNE(); // TODO hook this up to some keyboard input
        updateCoordinatesForDebugging();
    }
    @FXML void selectSE() throws IOException{
        this.areaViewport.selectSE(); // TODO hook this up to some keyboard input
        updateCoordinatesForDebugging();
    }
    @FXML void selectSW() throws IOException{
        this.areaViewport.selectSW(); // TODO hook this up to some keyboard input
        updateCoordinatesForDebugging();
    }
    @FXML void selectNW() throws IOException{
        this.areaViewport.selectNW();  // TODO hook this up to some keyboard input
        updateCoordinatesForDebugging();
    }
    private void updateCoordinatesForDebugging(){ // for debugging, once game is working we can get rid of this
        this.coordinateInfo.setText(areaViewport.returnXCoordinate() + " " + areaViewport.returnYCoordinate());
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
        currentPlayerLabel.setText("Current Player: Player " + controllerDispatch.getActivePlayerNumber());
    }

    public void returnToMainMenu() throws IOException {
        // TODO: properly end all game logic in Game class when returning to main menu
        this.switchControllerRelay.changeToMain();
    }

    public void overlayToggle() {
        if(this.areaViewport.resourceOverlaySwitch()){ // overlay is on so we want to display "turn off overlay"
            this.resourceOverlay.setText("turn off");
        } else { this.resourceOverlay.setText("turn on"); }
    }

    public void foodOverlay(){
        if(this.areaViewport.foodOverlaySwitch()){
            this.foodOverlay.setText("disable food");
        } else { this.foodOverlay.setText("enable food"); }
    }

    public void oreOverlay(){
        if(this.areaViewport.oreOverlaySwitch()){
            this.oreOverlay.setText("disable ore");
        } else { this.oreOverlay.setText("enable ore"); }
    }

    public void energyOverlay(){
        if(this.areaViewport.energyOverlaySwitch()){
            this.energyOverlay.setText("disable energy");
        } else { this.energyOverlay.setText("enable energy"); }
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

        //TODO don't use hard coded strings
        cycleLabels.put("mode", modeLabel);
        cycleLabels.put("type", typeLabel);
        cycleLabels.put("instance", instanceLabel);
        cycleLabels.put("command", commandLabel);

        this.areaViewport = new AreaViewport(vbox, canvas);
        this.statusViewport = new StatusViewport(cycleLabels);
    }

}

