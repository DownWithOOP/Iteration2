package controller.ingamecontrollertypes;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.MapObserver;
import model.map.Map;

/**
 * Created by Konrad on 3/1/2017.
 */
// this will be used to represent just the canvas area that will display a subset area of the map
public class AreaViewPortController implements MapObserver{

    private double cameraX;
    private double cameraY;
    private VBox vBox;
    private Canvas canvas;
    private int cameraSpeed;

    public AreaViewPortController(VBox vbox, Canvas canvas){
        this.cameraX = 0;
        this.cameraY = 0;
        this.vBox = vbox;
        this.canvas = canvas;
        this.cameraSpeed = 31;
    }
    public void changeCameraXPlus(){
        this.cameraX += cameraSpeed;
        this.drawSomething();
    }
    public void changeCameraYPlus(){
        this.cameraY += cameraSpeed;
        this.drawSomething();
    }
    public void changeCameraXMinus(){
        this.cameraX -= cameraSpeed;
        this.drawSomething();
    }
    public void changeCameraYMinus(){
        this.cameraY -= cameraSpeed;
        this.drawSomething();
    }
    public void fasterCamera(){
        this.cameraSpeed += 10;
    }
    public void slowerCamer(){
        this.cameraSpeed -=10;
        if(this.cameraSpeed <0){
            this.cameraSpeed = 1;
        }
    }

    public void drawSomething(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        Image image = new Image("resources/images/grass1.png");
        Image image2 = new Image("resources/images/water1.png");
        Image image3 = new Image("resources/images/dirt1.png");
        Image image4 = new Image("resources/images/mountain1.png");


        // TODO just a few sample tiles atm, in future will connect with actual map
        /*
        gc.drawImage(image,0+ cameraX,0 + cameraY);
        gc.drawImage(image,0 + cameraX,image.getHeight() + cameraY);
        gc.drawImage(image,image.getWidth()*0.75 + cameraX,image.getHeight()*0.5 + cameraY);
        gc.drawImage(image,image.getWidth()*0.75 + cameraX,image.getHeight()*1.5 + cameraY);
        gc.drawImage(image,image.getWidth()*0.75 + cameraX,image.getHeight()*-0.5 + cameraY);
        gc.drawImage(image2,image.getWidth()*1.5 + cameraX,image.getHeight() + cameraY);
        gc.drawImage(image2,image.getWidth()*1.5 + cameraX,0 + cameraY);
        gc.drawImage(image,image.getWidth()*2.25 + cameraX,image.getHeight()*-0.5 + cameraY);
        gc.drawImage(image3,image.getWidth()*2.25 + cameraX,image.getHeight()*0.5 + cameraY);
        gc.drawImage(image4,image.getWidth()*2.25 + cameraX,image.getHeight()*1.5 + cameraY);
        gc.drawImage(image4,image.getWidth()*3 + cameraX,image.getHeight()*-1 + cameraY);
        gc.drawImage(image3,image.getWidth()*3 + cameraX,0 + cameraY);
        gc.drawImage(image4,image.getWidth()*3 + cameraX,image.getHeight() + cameraY);
        gc.drawImage(image4,image.getWidth()*3 + cameraX,image.getHeight()*2 + cameraY);
        */

        double width = image.getWidth();
        double height = image.getHeight();

        for(int j=-5; j<5; j+=2){ // this handles even rows
            gc.drawImage(image,0.75*width*j+ cameraX,height*-0.5+ cameraY);
            gc.drawImage(image,0.75*width*j+ cameraX,height*0.5 + cameraY);
            gc.drawImage(image4,0.75*width*j+ cameraX,height*1.5 + cameraY);
            gc.drawImage(image,0.75*width*j+ cameraX,height*2.5+ cameraY);
        }
        for(int j=-4; j<5; j+=2){ // this handles odd rows
            gc.drawImage(image,0.75*width*j+ cameraX,height*-1+ cameraY);
            gc.drawImage(image,0.75*width*j+ cameraX,height*0 + cameraY);
            gc.drawImage(image2,0.75*width*j+ cameraX,height*1 + cameraY);
            gc.drawImage(image3,0.75*width*j+ cameraX,height*2+ cameraY);

        }

    }

    @Override // whenever the map gets updated then this gets called. 
    public void update(Map map) {

    }
}
