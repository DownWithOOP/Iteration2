package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
        VBox vb = new VBox();
        for(int i=0; i<data.size(); i++){
            UnitRenderObject renderObject = data.get(i);
            Label label = new Label();
            label.setTextFill(Color.BLACK);
            label.setStyle("-fx-font-weight: bold");
            label.setPadding(new Insets(15, 5, 10, 12));
            Label label2 = new Label();
            label2.setFont(new Font(12));
            label2.setStyle("-fx-font-weight: bold");
            label2.setTextFill(Color.BLACK);
            label2.setPadding(new Insets(15, 5, 10, 12));
            UnitStats unitStats = data.get(i).getUnitStats();
            label.setText("Type: " +renderObject.getIdType()+ "  locationX: " +renderObject.getLocationX()+ "  locationY: "  +renderObject.getLocationY());
            label2.setText("Health:  " +unitStats.getHealth() + " DefensiveDamage: " +unitStats.getDefensiveDamage() + " Movement: " +unitStats.getMovement() + " Range: " + unitStats.getRange() + " Armor: " + unitStats.getArmor() + " Offesnisve Damage: " + unitStats.getOffensiveDamage() + " Upkeep: " +unitStats.getUpkeep());
            vb.getChildren().add(label);
            vb.getChildren().add(label2);
            label.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
                    event -> {
                        this.stats.setText("Health:  " +unitStats.getHealth() +
                                "\n DefensiveDamage: " +unitStats.getDefensiveDamage() +
                                "\n Movement: " +unitStats.getMovement() +
                                "\n Range: " + unitStats.getRange() +
                                "\n Armor: " + unitStats.getArmor() +
                                "\n Offesnisve Damage: " + unitStats.getOffensiveDamage() +
                                "\n Upkeep: " +unitStats.getUpkeep());
                    });

        }
        pane.setContent(vb);

        // set stats for first unit
        UnitStats unitStats = data.get(0).getUnitStats();
        this.stats.setText("Health:  " +unitStats.getHealth() +
                "\n DefensiveDamage: " +unitStats.getDefensiveDamage() +
                "\n Movement: " +unitStats.getMovement() +
                "\n Range: " + unitStats.getRange() +
                "\n Armor: " + unitStats.getArmor() +
                "\n Offesnisve Damage: " + unitStats.getOffensiveDamage() +
                "\n Upkeep: " +unitStats.getUpkeep());

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

    }
}
