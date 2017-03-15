package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import controller.commands.CommandType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.ActiveState;
import model.RenderInformation.ArmyRenderObject;
import model.RenderInformation.UnitRenderInformation;
import model.RenderInformation.UnitRenderObject;
import model.entities.EntityId;
import model.entities.EntityType;
import model.entities.Stats.UnitStats;
import utilities.ObserverInterfaces.UnitObserver;
import java.util.ArrayList;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    @FXML
    private TabPane armyPane;
    @FXML
    private ComboBox<Integer> armyNumberComboBox;

    private VBox vb;
    private HBox hb;
    private int hashcode;
    private int numArmyTabs;

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
            return;
        } else {
            vb.getChildren().clear();
            for (int i = 0; i < data.size(); i++) {
                hb.getChildren().clear();
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
                String missions = renderObject.getMissions();
                label.setText("Type: " + renderObject.getIdType() + "  locationX: " + renderObject.getLocationX() + "  locationY: " + renderObject.getLocationY());
//                if (data.get(i).getIdType() == IdType.COLONIST || data.get(i).getIdType() == EntityType.EXPLORER || data.get(i).getIdType() == EntityType.MELEE || data.get(i).getIdType() == EntityType.RANGED) {
//
//                }
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
//                    this.stats.setText("\t\t\tUnit Number: " + i +
//                            "\n Health:  " + unitStats.getHealth() +
//                            "\n DefensiveDamage: " + unitStats.getDefensiveDamage() +
//                            "\n Movement: " + unitStats.getMovement() +
//                            "\n Range: " + unitStats.getRange() +
//                            "\n Armor: " + unitStats.getArmor() +
//                            "\n Offesnisve Damage: " + unitStats.getOffensiveDamage() +
//                            "\n Upkeep: " + unitStats.getUpkeep());
                    this.stats.setText("\t\t\tUnit Number: " + i + unitStats.getStatsString());

                    // update unit information with info regardin the particular unit
                    this.mission.setText("\t\tUnit Number: " + i + "\n" + missions);
                }
            }
            pane.setContent(vb);
            this.hashcode = data.hashCode();

            List<ArmyRenderObject> armyRenderObjectList = unitRenderInformation.returnArmyInformation();
            List<Integer> armyNumberChoices = new ArrayList<>();

            armyPane.getTabs().clear();
            for (int i=0; i < armyRenderObjectList.size(); i++) {
                ArmyRenderObject armyRenderObject = armyRenderObjectList.get(i);
                armyNumberChoices.add(Integer.parseInt(armyRenderObject.getId().getId()));

                Tab currentTab = new Tab();
                currentTab.setText("Army " + armyRenderObject.getId().getId());
                currentTab.setContent(new HBox());
                HBox currentTabHBox = (HBox) currentTab.getContent();
                currentTabHBox.getChildren().clear(); //Prevent previous tabs from getting extraneous information when creating armies

                VBox battleGroupBox = new VBox();

                Label bgLabel = new Label();
                bgLabel.setTextFill(Color.BLACK);
                bgLabel.setStyle("-fx-font-weight: bold");
                bgLabel.setPadding(new Insets(15, 5, 10, 12));
                bgLabel.setId(Integer.toString(i));
                bgLabel.setText("Battle Group");
                battleGroupBox.getChildren().add(bgLabel);
                for(UnitRenderObject renderObject : armyRenderObject.getBattleGroup()) {
                    Label bgUnitLabel = new Label();
                    bgUnitLabel.setFont(new Font(12));
                    bgUnitLabel.setStyle("-fx-font-weight: bold");
                    bgUnitLabel.setTextFill(Color.BLACK);
                    bgUnitLabel.setPadding(new Insets(15, 5, 10, 12));
                    bgUnitLabel.setText("Type: " + renderObject.getIdType() + "  locationX: " + renderObject.getLocationX() + "  locationY: " + renderObject.getLocationY());
                    battleGroupBox.getChildren().add(bgUnitLabel);
                }
                currentTabHBox.getChildren().add(battleGroupBox);

                VBox reinforcementsBox = new VBox();
                Label reinforcementsLabel = new Label();
                reinforcementsLabel.setTextFill(Color.BLACK);
                reinforcementsLabel.setStyle("-fx-font-weight: bold");
                reinforcementsLabel.setPadding(new Insets(15, 5, 10, 12));
                reinforcementsLabel.setId(Integer.toString(i));
                reinforcementsLabel.setText("Reinforcements");
                reinforcementsBox.getChildren().add(reinforcementsLabel);
                for (UnitRenderObject renderObject : armyRenderObject.getReinforcements()) {
                    Label reUnitLabel = new Label();
                    reUnitLabel.setFont(new Font(12));
                    reUnitLabel.setStyle("-fx-font-weight: bold");
                    reUnitLabel.setTextFill(Color.BLACK);
                    reUnitLabel.setPadding(new Insets(15, 5, 10, 12));
                    reUnitLabel.setText("Type: " + renderObject.getIdType() + "  locationX: " + renderObject.getLocationX() + "  locationY: " + renderObject.getLocationY());
                    reinforcementsBox.getChildren().add(reUnitLabel);
                }
                currentTabHBox.getChildren().add(reinforcementsBox);
                armyPane.getTabs().add(currentTab);
            }

            armyNumberComboBox.setItems(FXCollections.observableList(armyNumberChoices));

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

    public void handleCreateArmy(ActionEvent actionEvent) {
        this.controllerDispatch.handleCommand(CommandType.CREATE_ARMY);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vb = new VBox();
        hb = new HBox();
        this.selectedUnit = 0;
        this.stats.setStyle("-fx-font-weight: bold");
        this.stats.setPadding(new Insets(5, 5, 5, 5));
        this.mission.setStyle("-fx-font-weight: bold");
        this.mission.setFont(new Font(20));
        this.hashcode = 1;
        System.out.println(numArmyTabs + " army tabs being inited");

    }

    public void addUnitToArmy(ActionEvent actionEvent) {
        EntityId selectedUnitId = unitObserver.share().returnRenderInformation().get(selectedUnit).getId();
        System.out.println("id of entity to add " + selectedUnitId);
        int armyNumber = armyNumberComboBox.getValue();
        this.controllerDispatch.updateActiveStateCommandable(selectedUnitId);
        this.controllerDispatch.updateActiveStateModifier(armyNumber);
        this.controllerDispatch.updateActiveStateCommand(CommandType.JOIN_ARMY);
        this.controllerDispatch.handleCommandActivationFromView();
    }
}
