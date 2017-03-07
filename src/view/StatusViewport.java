package view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Created by Jonathen on 3/6/2017.
 *
 * Assuming that labels are draw (from top to bottom): Mode, Type, Instance, Command
 */
public class StatusViewport {

    Canvas canvas;
    VBox vbox;
    ObservableList<Node> labels;

    double width;
    double height;
    double x;
    double y;
    double maxWidth; //TODO specify how large text can get so it doesn't go outside of box


    public StatusViewport(VBox vbox/*, Canvas canvas*/) {
        this.vbox = vbox;
        //this.canvas = canvas;

        labels = vbox.getChildrenUnmodifiable();
    }

    public void updateRenderInfo() {
        //TODO update ActiveStateRenderInfo
        draw();
    }

    public void draw() {

        //Skip over labels that shouldn't be edited
        for (int labelIndex = 1; labelIndex < labels.size(); labelIndex = labelIndex + 2){
            ((Label) labels.get(labelIndex)).setText("hi  " + labelIndex);
        }

    }

}
