package view;

import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.RenderInformation.*;
import model.map.tile.resources.Resource;
import model.map.tile.resources.ResourceType;
import model.map.tile.terrain.TerrainType;
import utilities.id.IdType;
import view.utilities.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 3/1/2017.
 */
// this will be control the canvas area that is used to display the areaViewport
public class AreaViewport {

    private double cameraX;
    private double cameraY;
    private VBox vBox;
    private Canvas canvas;
    private int cameraSpeed;
    private MapRenderInformation mapRenderInformation;
    private UnitRenderInformation unitRenderInformation;
    private StructureRenderInformation structureRenderInformation;
    private double XBound;
    private double YBound;
    private int selectX;
    private int selectY;
    private int gridSizeX;
    private int gridSizeY;
    private boolean alteranteColumn;
    private boolean overlayOn = false;
    private boolean displayFood = true;
    private boolean displayOre = true;
    private boolean displayEnergy = true;
    private int fastCameraSpeed;
    private int slowCameraSpeed;

    Image grass = Assets.getInstance().GRASS;
    Image water = Assets.getInstance().WATER;
    Image dirt = Assets.getInstance().DIRT;
    Image mountain = Assets.getInstance().CRATER;
    Image select = Assets.getInstance().SELECT;
    Image catFood = Assets.getInstance().CATFOOD;
    Image crystal = Assets.getInstance().CRYSTAL;
    Image research = Assets.getInstance().RESEARCH;
    Image colonist = Assets.getInstance().COLONIST;
    Image explorer = Assets.getInstance().EXPLORER;

    public AreaViewport(VBox vbox, Canvas canvas){
        this.cameraX = -300; // default camera shift/starting position
        this.cameraY = 600; // default camera shift/starting position
        this.vBox = vbox;
        this.canvas = canvas;
        this.slowCameraSpeed = 5;
        this.fastCameraSpeed = 31;

        this.cameraSpeed = 21;
        this.selectX = 6; // starting X of selected tile
        this.selectY = 6; // starting Y of selected tile
        this.alteranteColumn = true;
        this.canvas.addEventFilter(MouseEvent.MOUSE_MOVED,
                event -> {
                    if(event.getSceneX() > 1000){ // move to the right fast
                        this.cameraSpeed = fastCameraSpeed;
                        changeCameraXMinus();
                    } else if(event.getSceneX() > 850){ // move to the right slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraXMinus();
                    }
                    else if(event.getSceneX() < 200){ // move to the left fast
                        this.cameraSpeed = fastCameraSpeed;
                        changeCameraXPlus();
                    } else if(event.getSceneX() < 350){ // move to the left slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraXPlus();
                    }
                    if(event.getSceneY() < 100){ // move up fast
                        this.cameraSpeed = fastCameraSpeed;
                        changeCameraYPlus();
                    } else if (event.getSceneY() < 200){ // move up slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraYPlus();
                    }
                    else if(event.getSceneY() > 500){ // move down fast
                        this.cameraSpeed = fastCameraSpeed;
                        changeCameraYMinus();
                    } else if(event.getSceneY() > 400){ // move down slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraYMinus();
                    }
                    this.drawSomething(); // refresh screen
                }
        );

    }
    /** Camera Navigation Controls **/
    public void changeCameraXPlus(){
        this.cameraX += cameraSpeed;
        if(this.cameraX > grass.getWidth()*0.75){
            this.cameraX = grass.getWidth()*0.75; // keep in bounds
        }
    }
    public void changeCameraYPlus(){
        this.cameraY += cameraSpeed;
        if(this.cameraY > this.YBound){
            this.cameraY = this.YBound; // keep in bounds;
        }
    }
    public void changeCameraXMinus(){
        if(this.cameraX - cameraSpeed < (this.XBound*-1)){
            this.cameraX = this.XBound * -1;
        } else {
            this.cameraX -= cameraSpeed;
        }
    }
    public void changeCameraYMinus(){

        if(this.cameraY - cameraSpeed <  348){ // hardcoded lower bound, not the best but does the job, must changed if different map size
            this.cameraY = 348;
        } else {
            this.cameraY -= cameraSpeed;
        }
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
    public void UpdateRenderInfo(MapRenderInformation renderMap, UnitRenderInformation renderUnit, StructureRenderInformation renderStructure){
        this.mapRenderInformation = renderMap;
        this.unitRenderInformation = renderUnit;
        this.structureRenderInformation = renderStructure;
        this.gridSizeX = mapRenderInformation.getX();
        this.gridSizeY = mapRenderInformation.getY();
        this.YBound = ((double) this.mapRenderInformation.getY()) * grass.getHeight()*0.75 + grass.getHeight()*2;
        this.XBound = ((double) this.mapRenderInformation.getX()) * grass.getWidth()*0.75 + grass.getWidth() - this.canvas.getWidth();
       if(overlayOn){
           // starts to lag with overlay so we turn off refreshing
       } else {
           this.drawSomething();
       }
    }

    public void drawSelection(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = grass.getWidth();
        double height = grass.getHeight();
        if(alteranteColumn){
            gc.drawImage(select,0.75*width*selectX+ cameraX,height*1*-selectY+ cameraY + width*0.45);
        } else {
            gc.drawImage(select,0.75*width*selectX+ cameraX,height*1*-selectY+ cameraY + width*0.9);
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

    public boolean resourceOverlaySwitch(){
        overlayOn = !overlayOn; // toggle boolean
        if(overlayOn){ drawSomething(); }
        return this.overlayOn;
    }
    public boolean foodOverlaySwitch(){
        displayFood = !displayFood; // toggle boolean
        if(overlayOn){ drawSomething(); }
        return this.displayFood;
    }
    public boolean energyOverlaySwitch(){
        displayEnergy = !displayEnergy; // toggle boolean
        if(overlayOn){ drawSomething(); }
        return this.displayEnergy;
    }
    public boolean oreOverlaySwitch(){
        displayOre = !displayOre; // toggle boolean
        if(overlayOn){ drawSomething(); }
        return this.displayOre;
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

        double width = grass.getWidth();
        double height = grass.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D(); // Clears whatever is currently on the canvas
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.WHITE);


        // draws new terrain objects
        for(int i=0; i<mapRenderInformation.getY()   ; i++){
            for(int j=0; j<mapRenderInformation.getX(); j++){
                TerrainType current = renderObjects[j][i].getTerrainType();
                if(j%2 == 0){
                    if(current.equals(TerrainType.GRASS)){
                      //  System.out.print(" GRASS ");
                        gc.drawImage(grass,0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                    } else if(current.equals(TerrainType.DIRT)){
                     //   System.out.print(" DIRT ");
                        gc.drawImage(dirt,0.75*width*j+ cameraX,height*1*-i+ cameraY+ width*0.45);
                    } else if(current.equals(TerrainType.WATER)){
                      //  System.out.print(" WATER ");
                        gc.drawImage(water,0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                    }
                }
                else {
                    if(current.equals(TerrainType.GRASS)){
                       // System.out.print(" GRASS ");
                        gc.drawImage(grass,0.75*width*j+ cameraX,height*1*-i+ cameraY  +height);
                    } else if(current.equals(TerrainType.DIRT)){
                       // System.out.print(" DIRT ");
                        gc.drawImage(dirt,0.75*width*j+ cameraX,height*1*-i+ cameraY +height);
                    } else if(current.equals(TerrainType.WATER)){
                        //System.out.print(" WATER ");
                        gc.drawImage(water,0.75*width*j+ cameraX,height*1*-i+ cameraY+height);
                    }
                }
            }
        }
        // after the map is rendered, we want to draw any units
        ArrayList<UnitRenderObject> unitData = unitRenderInformation.returnRenderInformation();
        for(int i=0; i<unitData.size(); i++ ){
            int x = unitData.get(i).getLocationX();
            int y = unitData.get(i).getLocationY();
            if(x%2 == 0){
                if(unitData.get(i).getIdType().equals(IdType.EXPLORER)){
                    gc.drawImage(explorer,0.75*width*x+ cameraX,height*1*-y+ cameraY + width*0.45);
                } else if (unitData.get(i).getIdType().equals(IdType.COLONIST)){
                    gc.drawImage(colonist,0.75*width*x+ cameraX,height*1*-y+ cameraY + width*0.45);
                }

            } else {
                if(unitData.get(i).getIdType().equals(IdType.EXPLORER)){
                    gc.drawImage(explorer,0.75*width*x+ cameraX,height*1*-y+ cameraY+height);
                }else if (unitData.get(i).getIdType().equals(IdType.COLONIST)){
                    gc.drawImage(colonist,0.75*width*x+ cameraX,height*1*-y+ cameraY+height);
                }
            }
        }

        // here we get to drawing the overlay if the user has it on
        if(overlayOn) {
            for (int i = 0; i < mapRenderInformation.getY(); i++) {
                for (int j = 0; j < mapRenderInformation.getX(); j++) {
                    List<Resource> resources = renderObjects[j][i].getResources();
                    // energy, ore, food in that order
                    int food = 0;
                    int energy = 0;
                    int ore = 0;
                    if (resources.size() == 0) {
                        // nothing, all set to 0
                        StringBuilder builder = new StringBuilder();
                        if (displayFood) {
                            builder.append("F: 0 " +"\n");
                        }
                        if (displayOre) {
                            builder.append("O: " + 0+ "\n");
                        }
                        if (displayEnergy) {
                            builder.append("E: " + 0 + "\n");
                        }
                        String resourceDisplay = builder.toString();
                        if (j % 2 == 0) {
                            gc.strokeText(resourceDisplay, 0.75 * width * j + cameraX + 40, height * 1 * -i + cameraY + width * 0.45 - 60 + height);
                        } else {
                            gc.strokeText(resourceDisplay, 0.75 * width * j + cameraX + 40, height * 1 * -i + cameraY + (2 * height) - 60);
                        }
                    } else {
                        energy = resources.get(0).getLevel();
                        ore = resources.get(1).getLevel();
                        food = resources.get(2).getLevel();
                        StringBuilder builder = new StringBuilder();
                        if (displayFood) {
                            builder.append("F: " + food + "\n");
                        }
                        if (displayOre) {
                            builder.append("O: " + ore + "\n");
                        }
                        if (displayEnergy) {
                            builder.append("E: " + energy + "\n");
                        }
                        String resourceDisplay = builder.toString();

                        if (j % 2 == 0) {
                            gc.strokeText(resourceDisplay, 0.75 * width * j + cameraX + 40, height * 1 * -i + cameraY + width * 0.45 - 60 + height);
                        } else {
                            gc.strokeText(resourceDisplay, 0.75 * width * j + cameraX + 40, height * 1 * -i + cameraY + (2 * height) - 60);
                        }
                    }
                }
            }

        }

        drawSelection(); // this draws the tile selection image
    }
}
