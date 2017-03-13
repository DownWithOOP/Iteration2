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
import model.entities.EntityType;
import model.map.tile.resources.Resource;
import model.map.tile.resources.ResourceType;
import model.map.tile.terrain.TerrainType;
import org.omg.CORBA.IDLType;
import utilities.ObserverInterfaces.MiniMapObserver;
import utilities.ObserverInterfaces.MiniMapSubject;
import utilities.id.IdType;
import view.utilities.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 3/1/2017.
 */
// this will be control the canvas area that is used to display the areaViewport
public class AreaViewport implements MiniMapSubject{

    private double cameraX;
    private double cameraY;
    private VBox vBox;
    private Canvas canvas;
    private int cameraSpeed;
    private MapRenderInformation mapRenderInformation;
    private UnitRenderInformation unitRenderInformation;
    private StructureRenderInformation structureRenderInformation;
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
    private TileRenderObject[][] renderData;
    private ArrayList<MiniMapObserver> miniMapObservers= new ArrayList<MiniMapObserver>();

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

    public AreaViewport(VBox vbox, Canvas canvas, MiniMap miniMap){

        registerStatusObserver(miniMap);
        this.cameraX = -200; // default camera shift/starting position
        this.cameraY = 500; // default camera shift/starting position
        this.vBox = vbox;
        this.canvas = canvas;
        this.slowCameraSpeed = 16;
        this.fastCameraSpeed = 31;

        this.cameraSpeed = 41;
        this.selectX = 6; // starting X of selected tile
        this.selectY = 6; // starting Y of selected tile
        this.alteranteColumn = true;
        this.canvas.addEventFilter(MouseEvent.MOUSE_MOVED,
                event -> {
                    if(event.getSceneX() > 850){ // move to the right slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraXMinus();
                    }
                    if(event.getSceneX() < 350){ // move to the left slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraXPlus();
                    }
                    if (event.getSceneY() < 100){ // move up slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraYPlus();
                    }
                    if(event.getSceneY() > 500){ // move down slow
                        this.cameraSpeed = slowCameraSpeed;
                        changeCameraYMinus();
                    }
                    this.updateCanvas(); // refresh screen
                }
        );

    }
    /** Camera Navigation Controls **/
    public void changeCameraXPlus(){
        if((cameraX+cameraSpeed) > 25){
            this.cameraX = 25;
        } else {
            this.cameraX += cameraSpeed;
        }
    }
    public void changeCameraYPlus(){
        if(cameraY + cameraSpeed > 1330){
            this.cameraY = 1330;
        } else {
            this.cameraY += cameraSpeed;
        }
    }
    public void changeCameraXMinus(){
        if(cameraX-cameraSpeed < -1500){
            this.cameraX = -1500;
        } else {
            this.cameraX -= cameraSpeed;
        }
    }
    public void changeCameraYMinus(){
        if(cameraY -cameraSpeed < 370){
            this.cameraY = 370;
        } else {
            this.cameraY -= cameraSpeed;
        }
    }



    public void UpdateRenderInfo(MapRenderInformation renderMap, UnitRenderInformation renderUnit, StructureRenderInformation renderStructure, TileRenderObject[][] renderData){
        this.mapRenderInformation = renderMap;
        this.renderData = renderData;
        this.unitRenderInformation = renderUnit;
        this.structureRenderInformation = renderStructure;

        this.gridSizeX = renderData.length;
        this.gridSizeY = renderData[0].length;
        updateCanvas();
        notifyObservers(); // update mini map

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
        updateCanvas();
    }
    public void selectSouth(){
        this.selectY--; // update value
        if(this.selectY < 0){
            this.selectY = 0;
        }
        updateCanvas(); // rerender entire map

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
        updateCanvas(); // rerender entire map
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
        updateCanvas();
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
         updateCanvas();
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
        updateCanvas();
    }

    public boolean resourceOverlaySwitch(){
        overlayOn = !overlayOn; // toggle boolean
        if(overlayOn){ updateCanvas(); }
        return this.overlayOn;
    }
    public boolean foodOverlaySwitch(){
        displayFood = !displayFood; // toggle boolean
        if(overlayOn){ updateCanvas(); }
        return this.displayFood;
    }
    public boolean energyOverlaySwitch(){
        displayEnergy = !displayEnergy; // toggle boolean
        if(overlayOn){ updateCanvas(); }
        return this.displayEnergy;
    }
    public boolean oreOverlaySwitch(){
        displayOre = !displayOre; // toggle boolean
        if(overlayOn){ updateCanvas(); }
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
    public void updateCanvas(){


        int sizeX = renderData.length;
        int sizeY = renderData[0].length;

        GraphicsContext gc = canvas.getGraphicsContext2D(); // Clears whatever is currently on the canvas
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.WHITE);

        double width = grass.getWidth();
        double height = grass.getHeight();

        for(int i=0; i<sizeY; i++){
            for(int j=0; j<sizeX; j++){
                TileRenderObject render =renderData[j][i];

                // we check if it is worth rendering it in the first place by checking it if is within bounds TODO
                    if(render.getVisibilityLevel() == 0){
                        // not visible, don't do anything

                    } else if(render.getVisibilityLevel() == 1){
                        // 1, we'll handle this later
                        // TODO greyed out area


                    } else{
                        // must be 2, fully visible display everything that is there
                        TerrainType type = render.getTerrainType();


                        StringBuilder builder = new StringBuilder();
                        if (displayFood) {
                            builder.append("F: " + render.getFoodAmount() +"\n");
                        }
                        if (displayOre) {
                            builder.append("O: " + render.getOreAmount() + "\n");
                        }
                        if (displayEnergy) {
                            builder.append("E: " + render.getEnergyAmount() + "\n");
                        }
                        String resourceDisplay = builder.toString();

                        if(render.getLocationX() % 2 == 0){ // first type of column
                                        if(type.equals(TerrainType.GRASS)){
                                            gc.drawImage(grass,
                                                    0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                                        }
                                        else if(type.equals(TerrainType.DIRT)){
                                            gc.drawImage(dirt,
                                                        0.75*width*j+ cameraX,height*1*-i+ cameraY+ width*0.45);
                                        }
                                        else if(type.equals(TerrainType.WATER)){
                                            gc.drawImage(water,
                                                        0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                                        }

                                        // now we draw any friendly structures and units
                                        ArrayList<IdType> entities = render.getUserEntities();
                                        for(IdType id : entities){
                                            if(id.equals(IdType.COLONIST)){ // draw colonist
                                                gc.drawImage(colonist,0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                                            }
                                            if(id.equals(IdType.EXPLORER)){
                                                gc.drawImage(explorer,0.75*width*j+ cameraX,height*1*-i+ cameraY + width*0.45);
                                            }
                                        }
                                        // now draw resource values if the overlay is on
                                        if(resourceDisplay.equals("") || !overlayOn){
                                            // don't display anything
                                        } else {
                                            gc.strokeText(resourceDisplay, 0.75 * width * j + cameraX + 40, height * 1 * -i + cameraY + width * 0.45 - 60 + height);
                                        }
                        }
                        else { // second column type
                                        if(type.equals(TerrainType.GRASS)){
                                            gc.drawImage(grass,
                                                    0.75*width*j+ cameraX,height*1*-i+ cameraY+height);
                                        }
                                        else if(type.equals(TerrainType.DIRT)){
                                            gc.drawImage(dirt,
                                                    0.75*width*j+ cameraX,height*1*-i+ cameraY+height);
                                        }
                                        else if(type.equals(TerrainType.WATER)){
                                            gc.drawImage(water,
                                                    0.75*width*j+ cameraX,height*1*-i+ cameraY+height);
                                        }
                                        // draw friendly units and structures
                                        ArrayList<IdType> entities = render.getUserEntities();
                                        for(IdType id : entities){
                                            if(id.equals(IdType.COLONIST)){ // draw colonist
                                                gc.drawImage(explorer,0.75*width*j+ cameraX,height*1*-i+ cameraY+height);
                                            }
                                            if(id.equals(IdType.EXPLORER)){
                                                gc.drawImage(colonist,0.75*width*j+ cameraX,height*1*-i+ cameraY+height);
                                            }
                                        }

                                        // now draw resource values if the overlay is on
                                        if(resourceDisplay.equals("") || !overlayOn){
                                            // don't display anything
                                        } else {
                                            gc.strokeText(resourceDisplay, 0.75 * width * j + cameraX + 40, height * 1 * -i + cameraY + (2 * height) - 60);
                                        }

                        }
                    }
            }
        }


       drawSelection();
    }

    @Override
    public void registerStatusObserver(MiniMapObserver o) {
            miniMapObservers.add(o);
    }

    @Override
    public void unregister(MiniMapObserver o) {
            miniMapObservers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(MiniMapObserver observer : miniMapObservers){
            observer.update((int)(cameraX),(int)(cameraY),0,0,renderData);
        }
    }
}
