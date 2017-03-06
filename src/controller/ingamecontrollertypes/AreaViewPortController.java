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
    private int selectX;
    private int selectY;
    private int gridSizeX;
    private int gridSizeY;
    private boolean alteranteColumn;

    Image image = new Image("resources/images/smallgrass1.png");
    Image image2 = new Image("resources/images/smallwater1.png");
    Image image3 = new Image("resources/images/smalldirt1.png");
   // Image image4 = new Image("resources/images/smallgrass1.png"); TODO for mountain
    Image image5 = new Image("resources/images/smallselect.png");

    public AreaViewPortController(VBox vbox, Canvas canvas){
        this.cameraX = 100; // default camera shift/starting position
        this.cameraY = 600; // default camera shift/starting position
        this.vBox = vbox;
        this.canvas = canvas;
        this.cameraSpeed = 101;
        this.selectX = 6; // starting X of selected tile
        this.selectY = 6; // starting Y of selected tile
        this.alteranteColumn = true;

    }
    /** Camera Navigation Controls **/

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
            this.cameraX = this.XBound * -1;
        } else {
            this.cameraX -= cameraSpeed;
        }
        this.drawSomething();
    }
    public void changeCameraYMinus(){

        if(this.cameraY - cameraSpeed < (image.getHeight()*0.5)){
            this.cameraY = image.getHeight()*0.5;
        } else {
            this.cameraY -= cameraSpeed;
        }
        this.drawSomething();
    }

    /** camera speed, controls how much canvas is moved by each time
     */
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
        this.gridSizeX = mapRenderInformation.getX();
        this.gridSizeY = mapRenderInformation.getY();
            this.YBound = ((double) this.mapRenderInformation.getY()) * image.getHeight()*0.75 + image.getHeight()*2;
            this.XBound = ((double) this.mapRenderInformation.getX()) * image.getWidth()*0.7 + image.getWidth() - this.canvas.getWidth();
       this.drawSomething();

    }

    public void drawSelection(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = image.getWidth();
        double height = image.getHeight();
        if(alteranteColumn){
            gc.drawImage(image5,0.75*width*selectX+ cameraX,height*1*-selectY+ cameraY + width*0.45);
        } else {
            gc.drawImage(image5,0.75*width*selectX+ cameraX,height*1*-selectY+ cameraY + width*0.9);
        }
    }

    /** selection control **/

    public void selectNorth(){
        this.selectY++; // update value
        if(this.selectY >= gridSizeY){
            this.selectY = gridSizeY-1;
        }
        drawSomething(); // rerender entire map
    }
    public void selectSouth(){
        this.selectY--; // update value
        if(this.selectY < 0){
            this.selectY = 0;
        }
        drawSomething(); // rerender entire map

    }
    public void selectNE(){
        this.selectX++; // update value
        if(this.alteranteColumn){  // column #1
            this.selectY++;
            if(this.selectX+1 > this.gridSizeX || this.selectY+1 > gridSizeY){
                this.selectX--; // reset to original position and don't alternate if out of bounds
                this.selectY--;
            } else {
                this.alteranteColumn = false;
            }
        } else { // column #2
            if(this.selectX+1 > this.gridSizeX){
                this.selectX--; // restore to original position if out of bounds and don't rotate
            } else {  this.alteranteColumn = true; }
        }
        drawSomething(); // rerender entire map
    }
    public void selectSE(){
        this.selectX++; // update value
        if(this.alteranteColumn){
            if(selectX+1 > gridSizeX){ // out of bounds, don't alternate, stay in same place
                this.selectX--;
            } else {
                this.alteranteColumn = false;
            }
        } else {
            this.selectY--;
            if(this.selectY < 0 || this.selectX+1 > gridSizeX  ){ // out of bounds, keep in original position, don't change column
                this.selectY++;
                this.selectX--;
            } else {
                this.alteranteColumn = true;
            }
        }
        drawSomething();
    }

    public void selectSW(){
        this.selectX--; // update value
        if(this.alteranteColumn){
            if(this.selectX < 0){
                this.selectX++;
            } else {
                this.alteranteColumn = false;
            }
        } else {
            this.selectY--;
            if(this.selectY < 0 || this.selectX < 0){ // out of bounds, keep original position, don't alternate
                this.selectX++;
                this.selectY++;
            } else{
                this.alteranteColumn = true;
            }
        }
         drawSomething();
    }
    public void selectNW(){
        this.selectX--; // update value
        if(this.alteranteColumn){
                this.selectY++;
                if(this.selectY+1 > gridSizeY || this.selectX < 0){ // out of bounds, restore to original place, don't alternatete
                    this.selectX++;
                    this.selectY--;
                } else{
                    this.alteranteColumn = false; // coordinates are valid
                }
            }
        else {
            if (this.selectX < 0) {
                this.selectX++;  // bad, reset to original
            } else {
                this.alteranteColumn = true; // coordinate is good
            }
        }
        drawSomething();
    }

    /**
     * Currently these 2 methods are for debugging to see if moving around the map works correctly
     */

    public int returnXCoordinate(){
        return this.selectX;
    }
    public int returnYCoordinate(){
        return this.selectY;
    }

    /** actually draws and renders the map that is currently stored in teh mapRenderInformation
     *
     */
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
                        gc.drawImage(image,0.75*width*j+ cameraX,height*1*-i+ cameraY  +height);
                    } else if(current.equals(TerrainType.DIRT)){
                       // System.out.print(" DIRT ");
                        gc.drawImage(image3,0.75*width*j+ cameraX,height*1*-i+ cameraY +height);
                    } else if(current.equals(TerrainType.WATER)){
                        //System.out.print(" WATER ");
                        gc.drawImage(image2,0.75*width*j+ cameraX,height*1*-i+ cameraY+height);
                    }
                }
            }
            //System.out.println();
        }
       // System.out.println("---------------");
        drawSelection();
    }
}
