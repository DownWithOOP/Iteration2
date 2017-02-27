package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import controller.commands.CommandType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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

    private SwitchControllerRelay switchControllerRelay;

        public void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay){
            this.switchControllerRelay = switchControllerRelay;
        }

    @Override
    protected void enableKeyboardInput() {

        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    if (event.isControlDown() && event.getCode() == KeyCode.UP) {
                        // TODO: Cycle MODE next
                        System.out.println("Ctrl + UP");
                        System.out.println("is controller dispatch null..? " + (controllerDispatch == null));
                        controllerDispatch.handleCommand(CommandType.CYCLE_MODE_NEXT);
                    }
                    else if (event.isControlDown() && event.getCode() == KeyCode.DOWN) {
                        // TODO: Cycle MODE previous
                        System.out.println("Ctrl + DOWN");
                        controllerDispatch.handleCommand(CommandType.CYCLE_MODE_PREV);
                    }
                    else if (event.isControlDown() && event.getCode() == KeyCode.LEFT) {
                        // TODO: Cycle TYPE previous
                        System.out.println("Ctrl + LEFT");
                        controllerDispatch.handleCommand(CommandType.CYCLE_TYPE_PREV);
                    }
                    else if (event.isControlDown() && event.getCode() == KeyCode.RIGHT) {
                        // TODO: Cycle TYPE next
                        System.out.println("Ctrl + RIGHT");
                        controllerDispatch.handleCommand(CommandType.CYCLE_TYPE_NEXT);
                    }
                    else if (event.getCode() == KeyCode.LEFT) {
                        // TODO: Cycle TYPE INSTANCES previous
                        System.out.println(event.getCode());
                        controllerDispatch.handleCommand(CommandType.CYCLE_INSTANCE_PREV);
                    }
                    else if (event.getCode() == KeyCode.RIGHT) {
                        // TODO: Cycle TYPE INSTANCES next
                        System.out.println(event.getCode());
                        controllerDispatch.handleCommand(CommandType.CYCLE_INSTANCE_NEXT);
                    }
                    else if (event.getCode() == KeyCode.UP) {
                        // TODO: Cycle COMMAND next
                        System.out.println(event.getCode());
                        controllerDispatch.handleCommand(CommandType.CYCLE_COMMAND_NEXT);
                    }
                    else if (event.getCode() == KeyCode.DOWN) {
                        // TODO: Cycle COMMAND previous
                        System.out.println(event.getCode());
                        controllerDispatch.handleCommand(CommandType.CYCLE_COMMAND_PREV);
                    }
                    else if (event.getCode() == KeyCode.NUMPAD1) {
                        // TODO: SW Direction
                    }
                    else if (event.getCode() == KeyCode.NUMPAD2) {
                        // TODO: S Direction
                    }
                    else if (event.getCode() == KeyCode.NUMPAD3) {
                        // TODO: SE Direction
                    }
                    else if (event.getCode() == KeyCode.NUMPAD4) {
                        // TODO: W Direction
                    }
                    else if (event.getCode() == KeyCode.NUMPAD5) {
                        // TODO: Set viewport focus on the currently selected army/building/unit and,
                        // when moving, to indicate that the tile the cursor is on is the new target location
                    }
                    else if (event.getCode() == KeyCode.NUMPAD6) {
                        // TODO: E Direction
                    }
                    else if (event.getCode() == KeyCode.NUMPAD7) {
                        // TODO: NW Direction
                    }
                    else if (event.getCode() == KeyCode.NUMPAD8) {
                        // TODO: N Direction
                    }
                    else if (event.getCode() == KeyCode.NUMPAD9) {
                        // TODO: NE Direction
                    }
                    else if (event.getCode() == KeyCode.ENTER) {
                        // TODO: select/activate commands without arguments (e.g., disband)
                    }
                    else if (event.getCode() == KeyCode.S) { //shortcut to Structure Overview
                        // Swap to Structure Overview screen
                        try {
                            this.switchControllerRelay.changeToStructure();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (event.getCode() == KeyCode.U) { //shortcut to Unit Overview
                        // Swap to Structure Overview screen
                        try {
                            this.switchControllerRelay.changeToUnit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    @FXML
    MenuBar mainMenuBar;
    @FXML
    Canvas canvas;

        @FXML
        public void drawOnCanvas(ActionEvent actionEvent) throws  IOException{
            GraphicsContext gc = canvas.getGraphicsContext2D();

            this.drawMap(gc,10,10);
        }

        // just for testing, pretty terrible at the moment, needs ALOT of work, working concept
        private void drawMap(GraphicsContext gc, int rows, int columns){

            double[] x1 = {12,20,30,38,30,20,12};
            double[] y1 = {20,10,10,20,30,30,20};

            gc.setFill(Color.GREEN);
            gc.setStroke(Color.YELLOW);

            double offsetx = 40;
            double offsety = 20;
            for(int m=0;m<rows; m++){
                for(int i=0; i<columns; i++){
                    double[] xx2 = x1;

                    for (int j = 0; j < 7; j++) {
                        xx2[j] += offsetx;
                    }
                    gc.strokePolyline(xx2, y1, 7);
                    gc.fillPolygon(xx2,y1,7);
                }
                for(int i=0; i<7; i++){
                    x1[i] -= (offsetx*columns);
                }
                for(int i=0; i<7; i++){
                    y1[i] += offsety;
                }
            }

            // at this point the first row and stuff was done
            double[] x11 = {12,20,30,38,30,20,12};
            double[] y11 = {20,10,10,20,30,30,20};

            double change1 = 20;
            double change2 = 10;
            for(int i=0; i<7; i++){
                x11[i] += change1;
            }
            for(int i=0; i<7; i++){
                y11[i] += change2;
            }
            offsetx = 40;
            offsety = 20;
            for(int m=0;m<rows; m++) {
                for (int i = 0; i < columns; i++) {
                    double[] xx2 = x11;

                    for (int j = 0; j < 7; j++) {
                        xx2[j] += offsetx;
                    }
                    gc.strokePolyline(x11, y11, 7);
                    gc.fillPolygon(x11, y11, 7);
                }
                for (int i = 0; i < 7; i++) {
                    x11[i] -= (offsetx * columns);
                }
                for (int i = 0; i < 7; i++) {
                    y11[i] += offsety;
                }
            }
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

