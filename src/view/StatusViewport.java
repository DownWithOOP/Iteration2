package view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.RenderInformation.StatusRenderInformation;

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

    private StatusRenderInformation statusRenderInformation;

    double width;
    double height;
    double x;
    double y;
    double maxWidth; //TODO specify how large text can get so it doesn't go outside of box


    public StatusViewport(Map<String, Label> cycleLabels/*VBox vbox, Canvas canvas*/) {
        this.cycleLabels = cycleLabels;
        //this.vbox = vbox;
        //this.canvas = canvas;
    }

    public void updateRenderInfo(StatusRenderInformation statusRenderInformation) {
        //TODO update ActiveStateRenderInfo
        this.statusRenderInformation = statusRenderInformation;
        draw();
    }

    public void draw() {

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
