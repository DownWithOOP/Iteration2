package controller.ingamecontrollertypes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.map.MapRenderObject;
import model.map.tile.MapRenderInformation;
import model.map.tile.terrain.TerrainType;

/**
 * Created by Konrad on 3/1/2017.
 */
// this will be control the canvas area that is used to display the areaViewport
public class AreaViewPortController{

    private double cameraX;
    private double cameraY;
    private VBox vBox;
    private Canvas canvas;
    private int cameraSpeed;
    private MapRenderInformation mapRenderInformation;
    private double XBound;
    private double YBound;

    Image image = new Image("resources/images/grass1.png");
    Image image2 = new Image("resources/images/water1.png");
    Image image3 = new Image("resources/images/dirt1.png");
    Image image4 = new Image("resources/images/mountain1.png");

    public AreaViewPortController(VBox vbox, Canvas canvas){
        this.cameraX = -400; // default camera shift/starting position
        this.cameraY = 400; // default camera shift/starting position
        this.vBox = vbox;
        this.canvas = canvas;
        this.cameraSpeed = 101;

    }
    public void changeCameraXPlus(){
        this.cameraX += cameraSpeed;
        if(this.cameraX > 0){
            this.cameraX = 0; // keep in bounds
        }
        this.drawSomething();
    }
    public void changeCameraYPlus(){
        this.cameraY += cameraSpeed;
        if(this.cameraY > this.YBound){
            this.cameraY = this.YBound; // keep in bounds;
        }
        this.drawSomething();
    }
    public void changeCameraXMinus(){
        if(this.cameraX - cameraSpeed < (this.XBound*-1)){
            this.cameraX = this.XBound * -1   ;
        } else {
            this.cameraX -= cameraSpeed;
        }
        this.drawSomething();
    }
    public void changeCameraYMinus(){

        if(this.cameraY - cameraSpeed < (image.getHeight()*1.5)){
            this.cameraY = image.getHeight()*1.5;
        } else {
            this.cameraY -= cameraSpeed;
        }
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
    public void UpdateRenderInfo(MapRenderInformation renderMap){
        this.mapRenderInformation = renderMap;
        this.YBound = ((double) this.mapRenderInformation.getY()) * image.getHeight()*0.75 + image.getHeight()*2;
        this.XBound = ((double) this.mapRenderInformation.getX()) * image.getWidth()*0.7 + image.getWidth() - this.canvas.getWidth();
       this.drawSomething();

    }

    public void drawSomething(){
        MapRenderObject[][] renderObjects = this.mapRenderInformation.getRenderObjectMap();

        double width = image.getWidth();
        double height = image.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());


        for(int i=0; i<mapRenderInformation.getY()   ; i++){
            for(int j=0; j<mapRenderInformation.getY(); j++){
                TerrainType current = renderObjects[i][j].getTerrainType();
                if(j%2 == 0){
                    if(current.equals(TerrainType.GRASS)){
                      //  System.out.print(" GRASS ");
                        gc.drawImage(image,0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                    } else if(current.equals(TerrainType.DIRT)){
                     //   System.out.print(" DIRT ");
                        gc.drawImage(image3,0.75*width*j+ cameraX,height*1*-i+ cameraY+ width*0.45);
                    } else if(current.equals(TerrainType.WATER)){
                      //  System.out.print(" WATER ");
                        gc.drawImage(image2,0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                    }

                }
                else {
                    if(current.equals(TerrainType.GRASS)){
                       // System.out.print(" GRASS ");
                        gc.drawImage(image,0.75*width*j+ cameraX,height*1*-i+ cameraY);
                    } else if(current.equals(TerrainType.DIRT)){
                       // System.out.print(" DIRT ");
                        gc.drawImage(image3,0.75*width*j+ cameraX,height*1*-i+ cameraY);
                    } else if(current.equals(TerrainType.WATER)){
                        //System.out.print(" WATER ");
                        gc.drawImage(image2,0.75*width*j+ cameraX,height*1*-i+ cameraY);
                    }
                }


            }
            //System.out.println();
        }
       // System.out.println("---------------");


        // TODO just a few sample tiles atm, in future will connect with actual map
        /*

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        Image image = new Image("resources/images/grass1.png");
        Image image2 = new Image("resources/images/water1.png");
        Image image3 = new Image("resources/images/dirt1.png");
        Image image4 = new Image("resources/images/mountain1.png");
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
        */

    }
}
