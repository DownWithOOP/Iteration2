package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.RenderInformation.*;
import model.entities.Stats.StructureStats;
import model.entities.Stats.UnitStats;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/25/2017.
 */
public class StructureViewController extends Controller{
    @FXML
    private Pane ap;
    @FXML
    private ScrollPane pane;
    @FXML
    private TextArea stats;
    @FXML
    private TextArea mission;

    private VBox vb;
    private int hashcode;

    private int selectedStructure; // used to keep track of which structure is clicked/currently selected and whose stats/missions need to be displayed
    private SwitchControllerRelay switchControllerRelay;
    private StructureObserver structureObserver; // this observer gets all the information from the model that we need for rendering/displaying

    public StructureViewController(){
        super();
    }

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
        StructureRenderInformation structureRenderInformation = structureObserver.share();
        ArrayList<StructureRenderObject> data = structureRenderInformation.returnRenderInformation();
        int code = data.hashCode(); // compare to see if there is a change to the data, if so we will need to refresh
        if(vb.getChildren().size() == data.size() && code == hashcode){
            // no new data, nothing to refresh
            return;
        } else {
            vb.getChildren().clear();
            for (int i = 0; i < data.size(); i++) {
                StructureRenderObject renderObject = data.get(i);
                Label label = new Label();
                label.setTextFill(Color.BLACK);
                label.setStyle("-fx-font-weight: bold");
                label.setPadding(new Insets(15, 5, 10, 12));
                label.setId(Integer.toString(i));
                Label label2 = new Label();
                label2.setFont(new Font(12));
                label2.setStyle("-fx-font-weight: bold");
                label2.setTextFill(Color.BLACK);
                label2.setPadding(new Insets(15, 5, 10, 12));
                StructureStats structureStats = data.get(i).getStructureStats();
                String missions = renderObject.getMissions();

                label.setText("Type: " + renderObject.getIdType() + "  locationX: " + renderObject.getLocationX() + "  locationY: " + renderObject.getLocationY());
                vb.getChildren().add(label);
                vb.getChildren().get((i)).addEventFilter(MouseEvent.MOUSE_PRESSED,
                        event -> {
                            Label temp = (Label)event.getSource();
                            this.selectedStructure = Integer.parseInt(temp.getId());
                            vb.getChildren().remove(0,vb.getChildren().size()); // refresh previous list, removes old event handlers
                            this.hashcode = 1; // change happend so we want to rerender
                        });
                vb.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
                        event -> {
                            pane.getScene().setCursor(Cursor.HAND); // when we hover over unit, cursor changes
                        });
                vb.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,
                        event -> {
                            pane.getScene().setCursor(Cursor.DEFAULT); // when we leave the unit, the cursor goes back to normal
                        });


                if (this.selectedStructure == i) {
                    // make it apparent the the currently selected on is selected by changing the color
                    ((Label) (vb.getChildren().get(selectedStructure))).setTextFill(Color.BLUE);

                    this.stats.setText("\t\t\tStructure Number: " + i + structureStats.getStatsString());

                    //System.out.println("MISSIONS: "+missions);
                    // update unit information with info regarding the particular unit
                    this.mission.setText("\t\tStructure Number: " + i + "\n" + missions);
                }
            }
            pane.setContent(vb);
            this.hashcode = data.hashCode();

        }

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
        vb = new VBox();
        this.selectedStructure = 0;
        this.stats.setStyle("-fx-font-weight: bold");
        this.stats.setPadding(new Insets(5, 5, 5, 5));
        this.mission.setStyle("-fx-font-weight: bold");
        this.mission.setFont(new Font(20));
        this.hashcode = 1;
    }


    public void handleChangeToMainView(ActionEvent actionEvent) throws IOException {
        this.switchControllerRelay.changeToMain();
    }

    public void keyListener(KeyEvent event) throws IOException {
        //TODO: add key menu shortcuts and command control recognition

    }
}
