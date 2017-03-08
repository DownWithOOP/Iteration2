package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.RenderInformation.UnitRenderInformation;
import model.RenderInformation.UnitRenderObject;
import model.entities.Stats.UnitStats;
import utilities.ObserverInterfaces.UnitObserver;
import java.util.ArrayList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/25/2017.
 */
public class UnitViewController extends Controller
{
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

    private int selectedUnit; // used to keep track of which unit is clicked/currently selected and whose stats/missions need to be displayed
    private SwitchControllerRelay switchControllerRelay;
    private UnitObserver unitObserver; // this observer gets all the information from the model that we need for rendering/displaying
    @Override
    protected void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay) {
        this.switchControllerRelay = switchControllerRelay;
    }

    @Override
    protected void enableKeyboardInput() {
        this.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    if (event.getCode() == KeyCode.S) { //shortcut to Structure Overview
                        // Swap to Structure Overview screen
                        try {
                            this.switchControllerRelay.changeToStructure();
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

    @Override
    protected void render() {

        UnitRenderInformation unitRenderInformation = unitObserver.share();
        ArrayList<UnitRenderObject> data = unitRenderInformation.returnRenderInformation();
        int code = data.hashCode(); // compare to see if there is a change to the data, if so we will need to refresh
        if(vb.getChildren().size() == data.size() && code == hashcode){
            // no new data, nothing to refresh
        } else {
            for (int i = 0; i < data.size(); i++) {
                UnitRenderObject renderObject = data.get(i);
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
                UnitStats unitStats = data.get(i).getUnitStats();
                label.setText("Type: " + renderObject.getIdType() + "  locationX: " + renderObject.getLocationX() + "  locationY: " + renderObject.getLocationY());
                    vb.getChildren().add(label);
                    vb.getChildren().get((i)).addEventFilter(MouseEvent.MOUSE_PRESSED,
                            event -> {
                                Label temp = (Label)event.getSource();
                                this.selectedUnit = Integer.parseInt(temp.getId());
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

                    
                if (this.selectedUnit == i) {
                    // make it apparent the the currently selected on is selected by changing the color
                    ((Label) (vb.getChildren().get(selectedUnit))).setTextFill(Color.BLUE);

                    // update the stats textArea with information pertaining to the selected unit
                    this.stats.setText("\t\t\tUnit Number: " + i +
                            "\n Health:  " + unitStats.getHealth() +
                            "\n DefensiveDamage: " + unitStats.getDefensiveDamage() +
                            "\n Movement: " + unitStats.getMovement() +
                            "\n Range: " + unitStats.getRange() +
                            "\n Armor: " + unitStats.getArmor() +
                            "\n Offesnisve Damage: " + unitStats.getOffensiveDamage() +
                            "\n Upkeep: " + unitStats.getUpkeep());

                    // update unit information with info regardin the particular unit
                    this.mission.setText("\t\tUnit Number: " + i +
                            "\n No missions yet " +
                            "\n get working people!!");
                }
            }
            pane.setContent(vb);
            this.hashcode = data.hashCode();
        }
    }

    @Override
    public void moveLeft() {
        System.out.println("Cannot move left in UnitView");
    }

    @Override
    public void moveUp() {
        System.out.println("Cannot move up in UnitView");
    }

    @Override
    public void moveRight() {
        System.out.println("Cannot move right in UnitView");
    }

    @Override
    public void moveDown() {
        System.out.println("Cannot move down in UnitView");
    }

    public void setObservers(UnitObserver unitObserver){
        this.unitObserver = unitObserver;
    }

    @FXML
    private Stage getStage() {
        Stage stage = (Stage) ap.getScene().getWindow();
        return stage;
    }

    public void handleChangeToMainView(ActionEvent actionEvent) throws IOException {
        this.switchControllerRelay.changeToMain();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vb = new VBox();
        this.selectedUnit = 0;
        this.stats.setStyle("-fx-font-weight: bold");
        this.stats.setPadding(new Insets(5, 5, 5, 5));
        this.mission.setStyle("-fx-font-weight: bold");
        this.mission.setFont(new Font(20));
        this.hashcode = 1;
    }
}
