package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import controller.commands.CommandType;
import controller.inputhandler.MVCInputHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
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

