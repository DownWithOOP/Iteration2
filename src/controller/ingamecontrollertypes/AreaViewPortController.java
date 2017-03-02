package controller.ingamecontrollertypes;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by Konrad on 3/1/2017.
 */
// this will be used to represent just the canvas area that will display a subset area of the map
public class AreaViewPortController {

    @FXML
    private Canvas canvas;
    private double height;
    private double width;

    public AreaViewPortController(Canvas canvas){
        this.canvas = canvas;
        // these dimensions will be used to position everything at the right resolution
        this.height = canvas.getHeight();
        this.width = canvas.getWidth();
    }


    public void drawSomething(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(height/2,width/2,canvas.getWidth(),canvas.getHeight());

        Image image = new Image("resources/images/grass1.png");
        gc.drawImage(image,0,0);


        Image image2 = new Image("resources/images/grass1.png");
        gc.drawImage(image2,0,image.getHeight());


        Image image3 = new Image("resources/images/grass1.png");
        gc.drawImage(image3,image.getWidth()*0.75,image.getHeight()*0.5);


        Image image4 = new Image("resources/images/grass1.png");
        gc.drawImage(image4,image.getWidth()*0.75,image.getHeight()*1.5);

        Image image5 = new Image("resources/images/grass1.png");
        gc.drawImage(image5,image.getWidth()*0.75,image.getHeight()*-0.5);

        gc.drawImage(image,image.getWidth()*1.5,image.getHeight());


        gc.drawImage(image,image.getWidth()*1.5,0);
        gc.drawImage(image,image.getWidth()*2.25,image.getHeight()*-0.5);
        gc.drawImage(image,image.getWidth()*2.25,image.getHeight()*0.5);
        gc.drawImage(image,image.getWidth()*2.25,image.getHeight()*1.5);




        /*
        double[] x1 = {12,20,30,38,30,20,12};
        double[] y1 = {20,10,10,20,30,30,20};

        int rows = 10;
        int columns = 10;

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
        */


    }

}
