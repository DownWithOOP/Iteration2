package controller.ingamecontrollertypes;

import controller.Controller;
import controller.Observers.PlayerObservator;
import controller.SwitchControllerRelay;
import controller.commands.CommandType;
import controller.commands.Direction;
import controller.inputhandler.MVCInputHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.map.Map;
import utilities.ObserverInterfaces.MapObserver;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;
import view.AreaViewport;
import view.MiniMap;
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
    Canvas MinMap;
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
    @FXML
    MenuItem keyboardMap;

    private Map currentMap;
    private AreaViewport areaViewport;
    private StatusViewport statusViewport;
    private MapObserver mapObserver;
    private UnitObserver unitObserver;
    private StructureObserver structureObserver;
    private StatusObserver statusObserver;
    private java.util.Map<String, Label> cycleLabels = new HashMap<>();
    private java.util.Map<String, String> argumentCommands = new HashMap<>();
    private SwitchControllerRelay switchControllerRelay;
    private MiniMap miniMap;
    private PlayerObservator playerObservator;

    public MainViewController(){
        super();

    }
    public void setObservers(MapObserver mapObserver, UnitObserver unitObserver, StructureObserver structureObserver,
                             StatusObserver statusObserver, PlayerObservator playerObservator){
        this.mapObserver = mapObserver;
        this.unitObserver = unitObserver;
        this.structureObserver = structureObserver;
        this.statusObserver = statusObserver;
        this.playerObservator = playerObservator;
    }

    public void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay){
        this.switchControllerRelay = switchControllerRelay;
    }


    @Override
    protected void enableKeyboardInput() {
        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    if (event.getCode() == KeyCode.E) {
                        controllerDispatch.handleCommand(CommandType.END_TURN);
                        currentPlayerLabel.setText("Current Player: Player " + controllerDispatch.getActivePlayerNumber() +
                                "   Energy Resources: " + controllerDispatch.getPlayer().getEnergyResourceLevel() +
                                "   Ore Resources: " + controllerDispatch.getPlayer().getOreResourceLevel() +
                                "   Food Resources: " + controllerDispatch.getPlayer().getFoodResourceLevel());
                    }
                    System.out.println(playerObservator.share().size() + " SIZE");

                    if(playerObservator.share().containsKey(event.getCode())){
                        String command = (String)playerObservator.share().get(event.getCode());
                        System.out.println("COMMAND: "   + command);
                        if(command.equals("SOUTH")){
                            this.areaViewport.selectSouth();
                            controllerDispatch.updateActiveStateModifier(Direction.SOUTH);
                            updateCoordinatesForDebugging();
                        }
                        if(command.equals("NORTH")){
                            this.areaViewport.selectNorth();
                            controllerDispatch.updateActiveStateModifier(Direction.NORTH);
                            updateCoordinatesForDebugging();
                        }
                        if(command.equals("NE")){
                            this.areaViewport.selectNE();
                            controllerDispatch.updateActiveStateModifier(Direction.NORTH_EAST);
                            updateCoordinatesForDebugging();
                        }
                        if(command.equals("NW")){
                            this.areaViewport.selectNW();
                            controllerDispatch.updateActiveStateModifier(Direction.NORTH_WEST);
                            updateCoordinatesForDebugging();
                        }
                        if(command.equals("SE")){
                            this.areaViewport.selectSE();
                            controllerDispatch.updateActiveStateModifier(Direction.SOUTH_EAST);
                            updateCoordinatesForDebugging();
                        }
                        if(command.equals("SW")){
                            this.areaViewport.selectSW();
                            controllerDispatch.updateActiveStateModifier(Direction.SOUTH_WEST);
                            updateCoordinatesForDebugging();
                        }

                    }


                    CommandType receivedCommand;
                    MVCInputHandler mvcInputHandler = new MVCInputHandler();
                    receivedCommand = mvcInputHandler.interpretInput(event);
                    if (receivedCommand != null) {
                        if (receivedCommand == CommandType.ACTIVATE_COMMAND) { // if a command is selected
                            if (argumentCommands.containsKey(commandLabel.getText())) {
                                try {
                                    popUpView(commandLabel.getText());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                System.out.println(receivedCommand.toString());
                                controllerDispatch.handleCommandActivation();
                            }
                        }
                        else {
                            System.out.println(receivedCommand.toString());
                            controllerDispatch.handleCommand(receivedCommand);
                        }
                    }
                }
        );
    }

    public void popUpView(String text) throws IOException {
        Stage stage = new Stage();
        //URL url = new File("/resources/buildStructurePopUp.fxml").toURL();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(argumentCommands.get(text)));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle(text);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(commandLabel.getScene().getWindow());
        Controller inputController = loader.getController();
        inputController.setDispatch(controllerDispatch);
        stage.showAndWait();
    }

    @Override
    protected void render() {
        this.areaViewport.getCurrentActiveUnit(this.statusObserver.share().getInstanceString(), this.statusObserver.share().getLocationX(), this.statusObserver.share().getLocationY() );
        this.areaViewport.UpdateRenderInfo(this.mapObserver.share(), this.unitObserver.share(), this.structureObserver.share(), this.mapObserver.getPlayerXRenderMap(controllerDispatch.getActivePlayerNumber())); // displays the map
        this.statusViewport.updateRenderInfo(this.statusObserver.share());

    }



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


    public void handleKeyBoardSwitch(){

    }

    @FXML void selectNorth() throws IOException{
        this.areaViewport.selectNorth(); // TODO hook this up to some keyboard input
        controllerDispatch.updateActiveStateModifier(Direction.NORTH);
        updateCoordinatesForDebugging();
    }
    @FXML void selectSouth() throws IOException{
        this.areaViewport.selectSouth(); // TODO hook this up to some keyboard input
        controllerDispatch.updateActiveStateModifier(Direction.SOUTH);
        updateCoordinatesForDebugging();
    }
    @FXML void selectNE() throws IOException{
        this.areaViewport.selectNE(); // TODO hook this up to some keyboard input
        controllerDispatch.updateActiveStateModifier(Direction.NORTH_EAST);
        updateCoordinatesForDebugging();
    }
    @FXML void selectSE() throws IOException{
        this.areaViewport.selectSE(); // TODO hook this up to some keyboard input
        controllerDispatch.updateActiveStateModifier(Direction.SOUTH_EAST);
        updateCoordinatesForDebugging();
    }
    @FXML void selectSW() throws IOException{
        this.areaViewport.selectSW(); // TODO hook this up to some keyboard input
        controllerDispatch.updateActiveStateModifier(Direction.SOUTH_WEST);
        updateCoordinatesForDebugging();
    }
    @FXML void selectNW() throws IOException{
        this.areaViewport.selectNW();  // TODO hook this up to some keyboard input
        controllerDispatch.updateActiveStateModifier(Direction.NORTH_WEST);
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
        currentPlayerLabel.setText("Current Player: Player " + controllerDispatch.getActivePlayerNumber() +
                                   "   Energy Resources: " + controllerDispatch.getPlayer().getEnergyResourceLevel() +
                                   "   Ore Resources: " + controllerDispatch.getPlayer().getOreResourceLevel() +
                                   "   Food Resources: " + controllerDispatch.getPlayer().getFoodResourceLevel());
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
    @FXML public void initialize(URL location, ResourceBundle resources) { // initialized the component correctly

        //TODO don't use hard coded strings
        cycleLabels.put("mode", modeLabel);
        cycleLabels.put("type", typeLabel);
        cycleLabels.put("instance", instanceLabel);
        cycleLabels.put("command", commandLabel);

        argumentCommands.put("BUILD_STRUCTURE", "/resources/buildStructurePopUp.fxml");
        argumentCommands.put("CREATE_UNIT", "/resources/createUnitPopUp.fxml");
        argumentCommands.put("HEAL_UNIT", "/resources/healUnitPopUp.fxml");

        this.miniMap = new MiniMap(MinMap);
        this.areaViewport = new AreaViewport(vbox, canvas, miniMap);
        this.statusViewport = new StatusViewport(cycleLabels);
        this.updateCoordinatesForDebugging();

    }

    //Functions to move cursor via numpad input
    public void moveCursorSW(){
        areaViewport.selectSW();
    }
    public void moveCursorSouth(){
        areaViewport.selectSouth();
    }
    public void moveCursorSE(){
        areaViewport.selectSE();
    }
    public void moveCursorNW(){
        areaViewport.selectNW();
    }
    public void moveCursorNorth(){
        areaViewport.selectNorth();
    }
    public void moveCursorNE(){
        areaViewport.selectNE();
    }

}

