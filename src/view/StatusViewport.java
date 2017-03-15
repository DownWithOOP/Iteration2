package view;

import controller.commands.CommandType;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.RallyPoint;
import model.RenderInformation.StatusRenderInformation;
import model.entities.Entity;
import model.entities.Stats.StatsType;
import model.entities.unit.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by Jonathen on 3/6/2017.
 *
 * Assuming that labels are draw (from top to bottom): Mode, Type, Instance, Command
 */
public class StatusViewport {

    Canvas canvas;
    VBox vbox;
    private Map<String, Label> cycleLabels;
    private Pane statsGrid;

    private StatusRenderInformation statusRenderInformation;

    double width;
    double height;
    double x;
    double y;
    double maxWidth; //TODO specify how large text can get so it doesn't go outside of box


    public StatusViewport(Map<String, Label> cycleLabels, Pane statsGrid/*VBox vbox, Canvas canvas*/) {
        this.cycleLabels = cycleLabels;
        this.statsGrid = statsGrid;
        //this.vbox = vbox;
        //this.canvas = canvas;
    }

    public void updateRenderInfo(StatusRenderInformation statusRenderInformation) {
        //TODO update ActiveStateRenderInfo
        this.statusRenderInformation = statusRenderInformation;
        draw();
    }

    public void draw() {
        //Label selectedEntityLabel =  new Label(statusRenderInformation.getInstanceString());
        this.statsGrid.getChildren().clear();
        VBox vb = new VBox();
        if (!statusRenderInformation.getModeString().equals("RALLY_POINT")) {
            Entity currentInstance =  (Entity) statusRenderInformation.getInstance();
            if (currentInstance != null) {
                for (Map.Entry<StatsType, Integer>entry : currentInstance.getStats().getStatsMap().entrySet()) {
                    Label label = new Label(entry.getKey().toString() + ": " + entry.getValue());
                    label.setStyle("-fx-font-size: 10;" + "-fx-font-weight: BOLD;");
                    vb.getChildren().add(label);
                }
            }
        }

        this.statsGrid.getChildren().addAll(vb);
        for (Map.Entry<String, Label> entry : cycleLabels.entrySet()) {
            String key = entry.getKey();
            Label value = entry.getValue();
            if (value == null) { break; } //TODO remove this
            switch (key) {
                case "mode":
                    value.setText(statusRenderInformation.getModeString());
                    break;
                case "type":
                    value.setText(statusRenderInformation.getTypeString());
                    break;
                case "instance":
                    value.setText(statusRenderInformation.getInstanceString());
                    break;
                case "command":
                    value.setText(statusRenderInformation.getCommandString());
                    break;
                default:
                    break;
            }
        }
    }

}
