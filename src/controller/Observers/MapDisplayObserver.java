package controller.Observers;

import model.RenderInformation.*;

import java.util.ArrayList;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MapDisplayObserver implements utilities.ObserverInterfaces.MapObserver{

    private MapRenderInformation mapRenderInformation;
    private int numberOfPlayers;
    private ArrayList<int[][]> fogOfWarManager;
    private int mapSizeX;
    private int mapSizeY;

    public MapDisplayObserver(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.fogOfWarManager = new ArrayList<>();
    }


    @Override
    public void update(int playerNumber, MapRenderInformation mapRenderInformation, UnitRenderInformation unitRenderInformation, StructureRenderInformation structureRenderInformation) {
        this.mapRenderInformation = mapRenderInformation;
        if(fogOfWarManager.size() < playerNumber){
            // not initialized yet
            int[][] fogOfWarMask = new int[mapRenderInformation.getX()][mapRenderInformation.getY()];
            fogOfWarManager.add(playerNumber-1,fogOfWarMask);
            // initially everything is set to 0 meaning player is not discovered anything
        } else {
            // 0 = not visible
            // 1 = grey
            // 2 = fully visible

            // player is already initialized,we go through the existing mask, anything set to 2 we set to 1
            int[][] mask = fogOfWarManager.get(playerNumber-1);
            for(int i=0; i<mapRenderInformation.getX(); i++){
                for(int j=0; j<mapRenderInformation.getY(); j++){
                    int current = mask[i][j];
                    if(current == 2){
                        mask[j][i] = 1;
                    }
                }
            }
        }

        int[][] mask = fogOfWarManager.get(playerNumber-1);
        this.mapSizeX = mapRenderInformation.getX();
        this.mapSizeY = mapRenderInformation.getY();

        // now we go through the structures and units and determine which tiles are completly visible
        ArrayList<UnitRenderObject> unitData = unitRenderInformation.returnRenderInformation();
        for(int i=0; i<unitData.size(); i++){
            UnitRenderObject temp = unitData.get(i);
            // we set the location of the unit as completly visible
            mask[temp.getLocationX()][temp.getLocationY()] = 2;
            // now we get the visibility radius of the unit
            int radius = temp.getUnitStats().getVisionRadius();
            int offSet = 0;
            if(temp.getLocationX()%2 == 0){
                offSet = 0; }
            else {
                offSet =-1;
            }
            if(radius <= 1){  calculateLOSForOne(temp,offSet,mask); }
            if(radius <= 2){  calculateLOSForTwo(temp,offSet,mask); }
        }

        // now for structures
        // TODO
        /*ArrayList<StructureRenderObject> structureData = structureRenderInformation.returnRenderInformation();
        for(int i=0; i<structureData.size(); i++){
            StructureRenderObject temp = structureData.get(i);
            // we set the location of the unit as completly visible
            mask[temp.getLocationX()][temp.getLocationY()] = 2;
            // now we get the visibility radius of the unit

            if(validCoordinate(temp.getLocationX(),temp.getLocationY()+1)){
                mask[temp.getLocationX()][temp.getLocationY()+1] = 2;
            }
            if(validCoordinate(temp.getLocationX(),temp.getLocationY()-1)){
                mask[temp.getLocationX()][temp.getLocationY()-1] = 2;
            }
        }
        */
    }

    @Override
    public MapRenderInformation share() {
        return this.mapRenderInformation;
    }

    @Override
    public int[][] getPlayerXFogOfWarMap(int playerNumber){
        return this.fogOfWarManager.get(playerNumber-1);
    }


    // checks if coordinate is withing bounds for the map
    private boolean validCoordinate(int x, int y){
        if(x < 0 || x > this.mapSizeX){ return false; }
        else if (y < 0 || y > this.mapSizeY) { return false; }
        else { return true; }
    }

    private void calculateLOSForOne(UnitRenderObject temp,int offSet, int[][] mask){
        if(validCoordinate(temp.getLocationX(),temp.getLocationY()+1)){
            mask[temp.getLocationX()][temp.getLocationY()+1] = 2;  // North location
        }
        if(validCoordinate(temp.getLocationX(),temp.getLocationY()-1)){
            mask[temp.getLocationX()][temp.getLocationY()-1] = 2;  // South location
        }
        if(validCoordinate(temp.getLocationX()-1,temp.getLocationY()+offSet)){ // West
            mask[temp.getLocationX()-1][temp.getLocationY() +offSet] = 2;
        }
        if(validCoordinate(temp.getLocationX()-1,temp.getLocationY()+1 +offSet)){ // West
            mask[temp.getLocationX()-1][temp.getLocationY()+1+offSet] = 2;
        }
        if(validCoordinate(temp.getLocationX()+1,temp.getLocationY()+offSet)){  // East
            mask[temp.getLocationX()+1][temp.getLocationY()+offSet] = 2;
        }
        if(validCoordinate(temp.getLocationX()+1,temp.getLocationY()+1+offSet)){ // East
            mask[temp.getLocationX()+1][temp.getLocationY()+1+offSet] = 2;
        }
    }

    private void calculateLOSForTwo(UnitRenderObject temp,int offSet, int[][] mask) {
        if (validCoordinate(temp.getLocationX(), temp.getLocationY() + 2)) {
            mask[temp.getLocationX()][temp.getLocationY() + 2] = 2;  // North location
        }
        if (validCoordinate(temp.getLocationX(), temp.getLocationY() - 2)) {
            mask[temp.getLocationX()][temp.getLocationY() - 2] = 2;  // South location
        }
        if (validCoordinate(temp.getLocationX() - 1, temp.getLocationY() + 2 + offSet)) { // NW
            mask[temp.getLocationX() - 1][temp.getLocationY() + 2 + offSet] = 2;
        }
        if (validCoordinate(temp.getLocationX() + 1, temp.getLocationY() + 2 + offSet)) { // NE
            mask[temp.getLocationX() +1][temp.getLocationY() + 2 + offSet] = 2;
        }
        if (validCoordinate(temp.getLocationX() - 1, temp.getLocationY() -1 + offSet)) { // SW
            mask[temp.getLocationX() - 1][temp.getLocationY() -1 + offSet] = 2;
        }
        if (validCoordinate(temp.getLocationX() + 1, temp.getLocationY() -1 + offSet)) { // SE
            mask[temp.getLocationX() +1][temp.getLocationY() -1 + offSet] = 2;
        }
        if (validCoordinate(temp.getLocationX() - 2, temp.getLocationY() -1)) {  // W
            mask[temp.getLocationX() - 2][temp.getLocationY() -1] = 2;
        }
        if (validCoordinate(temp.getLocationX() -2, temp.getLocationY())) {  // W
            mask[temp.getLocationX() -2][temp.getLocationY()] = 2;
        }
        if (validCoordinate(temp.getLocationX() -2, temp.getLocationY() + 1)) {  // W
            mask[temp.getLocationX() -2][temp.getLocationY() + 1] = 2;
        }
        if (validCoordinate(temp.getLocationX() + 2, temp.getLocationY() -1)) {  // E
            mask[temp.getLocationX() + 2][temp.getLocationY() -1] = 2;
        }
        if (validCoordinate(temp.getLocationX() + 2, temp.getLocationY())) {  // E
            mask[temp.getLocationX() + 2][temp.getLocationY()] = 2;
        }
        if (validCoordinate(temp.getLocationX() + 2, temp.getLocationY() + 1)) {  // E
            mask[temp.getLocationX() + 2][temp.getLocationY() + 1] = 2;
        }
    }
}
