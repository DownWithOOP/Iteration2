package controller.Observers;

import model.RenderInformation.*;
import model.map.tile.resources.ResourceType;

import java.util.ArrayList;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MapDisplayObserver implements utilities.ObserverInterfaces.MapObserver{

    private MapRenderInformation mapRenderInformation;
    private int numberOfPlayers;
    private ArrayList<TileRenderObject[][]> playerTiles;
    private ArrayList<int[][]> fogOfWarManager;
    private int mapSizeX;
    private int mapSizeY;
    private boolean initialized = false;

    public MapDisplayObserver(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.playerTiles = new ArrayList<>();
        this.fogOfWarManager = new ArrayList<>();
    }

    private void initialize(MapRenderInformation mapRenderInformation){
        // called once after everything is setup and we know from the model the size of the map
        this.mapSizeX = mapRenderInformation.getX();
        this.mapSizeY = mapRenderInformation.getY();
        this.initialized = true;
        // we now 100% know the size of the map, it will stay constant throughout the rest of the game and updating
    }
    private void initializeNewPlayer(int playerNumber){
        TileRenderObject[][] playerInformation = new TileRenderObject[mapSizeX][mapSizeY];
        for(int i=0; i<mapSizeX; i++){
            for(int j=0; j<mapSizeY; j++){
                // initialize tile render objects with location info, by default will be set to 0=unknown
                TileRenderObject addMe = new TileRenderObject(i,j);
                playerInformation[i][j] = addMe;
            }
        }
        playerTiles.add(playerNumber-1,playerInformation);
    }

    @Override
    public void update(int playerNumber, MapRenderInformation mapRenderInformation, UnitRenderInformation unitRenderInformation, StructureRenderInformation structureRenderInformation) {
        if(!initialized){
            // this gets called only once since immediatly we don't know the size of the map
            initialize(mapRenderInformation);
        }
        // check if player has a TileRenderObject array initialized for them
        if(playerTiles.size() < playerNumber){
            initializeNewPlayer(playerNumber); // if size of ArrayList is too small, then player does not have one initialized
        }

        // go through TileRender objects change any that have visibility level 2, down to level 1
        TileRenderObject[][] userData = playerTiles.get(playerNumber-1);
        for(int i=0; i<mapSizeX; i++){
            for(int j=0; j<mapSizeY; j++){
                TileRenderObject check = userData[i][j];
                if(check.getVisibilityLevel() == 2){
                    check.setVisibilityToOne();
                }
            }
        }

        // now we go through each of the units
        ArrayList<UnitRenderObject> playerUnitData = unitRenderInformation.returnRenderInformation();
        MapRenderObject[][] mapInfo = mapRenderInformation.getRenderObjectMap();

        for(UnitRenderObject unit : playerUnitData){
            // we set the location of where the unit currently is as 2 (100% visible)
            TileRenderObject temp = userData[unit.getLocationX()][unit.getLocationY()];
            temp.setVisibilityTwo();
            temp.addUserEntity(unit.getIdType()); // we add this to the list of entities for the player

            // for the unit, we get their visibility radius and update terrainType, and resource count for all visible tiles
            int offSet = 0;
            if(unit.getLocationX()%2 == 0){
                offSet = 0; }
            else {
                offSet =-1;
            }

            int LOS = unit.getUnitStats().getVisionRadius();
            if(LOS <= 1){ // set 1 radius out as all visible
                updateLevelOneLOS(playerNumber,offSet,unit.getLocationX(),unit.getLocationY(),mapInfo);
            }
            if(LOS <= 2){ // set 2 radius out as all visible

            }
        }

        /*
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



    private void updateLevelOneLOS(int playerNumber, int offSet, int locationX, int locationY, MapRenderObject[][] mapInfo){


        TileRenderObject[][] userData = playerTiles.get(playerNumber-1);
        // tile location
        userData[locationX][locationY].setTerrainType(mapInfo[locationX][locationY].getTerrainType());
        // set food amount

        if(mapInfo[locationX][locationY].getResources().size() != 3){ // everything set to 0
                // by default resource levels are set to 0

        } else { // we get the resource values that are set
            userData[locationX][locationY].setFoodAmount(mapInfo[locationX][locationY].getResources().get(2).getLevel()); // food is index 2
            userData[locationX][locationY].setEnergyAmount(mapInfo[locationX][locationY].getResources().get(0).getLevel()); // ore is index 1
            userData[locationX][locationY].setOreAmount(mapInfo[locationX][locationY].getResources().get(1).getLevel()); // ore is index 1
        }


        // NORTH LOCATION
        if(validCoordinate(locationX,locationY+1)){
            userData[locationX][locationY+1].setTerrainType(mapInfo[locationX][locationY+1].getTerrainType());
            if(mapInfo[locationX][locationY+1].getResources().size() != 3){
                // all set to 0
            } else {
                userData[locationX][locationY+1].setFoodAmount(mapInfo[locationX][locationY+1].getResources().get(2).getLevel()); // food is index 2
                userData[locationX][locationY+1].setEnergyAmount(mapInfo[locationX][locationY+1].getResources().get(0).getLevel()); // ore is index 1
                userData[locationX][locationY+1].setOreAmount(mapInfo[locationX][locationY+1].getResources().get(1).getLevel()); // ore is index 1
            }

        }
        // SOUTH LOCATION
        if(validCoordinate(locationX,locationY-1)){
            userData[locationX][locationY-1].setTerrainType(mapInfo[locationX][locationY-1].getTerrainType());
            if(mapInfo[locationX][locationY-1].getResources().size() != 3){
                // all set to 0
            } else {
                userData[locationX][locationY-1].setFoodAmount(mapInfo[locationX][locationY-1].getResources().get(2).getLevel());
                userData[locationX][locationY-1].setEnergyAmount(mapInfo[locationX][locationY-1].getResources().get(0).getLevel());
                userData[locationX][locationY-1].setOreAmount(mapInfo[locationX][locationY-1].getResources().get(1).getLevel());
            }
        }
        // WEST LOCATION
        if(validCoordinate(locationX-1,locationY+offSet)){
            userData[locationX-1][locationY+offSet].setTerrainType(mapInfo[locationX-1][locationY+offSet].getTerrainType());
            if(mapInfo[locationX-1][locationY+offSet].getResources().size() != 3){
                // all set to 0
            } else {
                userData[locationX-1][locationY+offSet].setFoodAmount(mapInfo[locationX-1][locationY+offSet].getResources().get(2).getLevel());
                userData[locationX-1][locationY+offSet].setEnergyAmount(mapInfo[locationX-1][locationY+offSet].getResources().get(0).getLevel());
                userData[locationX-1][locationY+offSet].setOreAmount(mapInfo[locationX-1][locationY+offSet].getResources().get(1).getLevel());
            }
        }
        // WEST LOCATION
        if(validCoordinate(locationX-1,locationY+1+offSet)){
            userData[locationX-1][locationY+offSet+1].setTerrainType(mapInfo[locationX-1][locationY+offSet+1].getTerrainType());
            if(mapInfo[locationX-1][locationY+offSet+1].getResources().size() != 3){
                // all set to 0
            } else {
                userData[locationX-1][locationY+offSet+1].setFoodAmount(mapInfo[locationX-1][locationY+offSet+1].getResources().get(2).getLevel());
                userData[locationX-1][locationY+offSet+1].setEnergyAmount(mapInfo[locationX-1][locationY+offSet+1].getResources().get(0).getLevel());
                userData[locationX-1][locationY+offSet+1].setOreAmount(mapInfo[locationX-1][locationY+offSet+1].getResources().get(1).getLevel());
            }

        }
        // EAST LOCATION
        if(validCoordinate(locationX+1,locationY+offSet)){
            userData[locationX+1][locationY+offSet].setTerrainType(mapInfo[locationX+1][locationY+offSet].getTerrainType());
            if(mapInfo[locationX+1][locationY+offSet].getResources().size() != 3){
                // all set to 0
            } else {
                userData[locationX+1][locationY+offSet].setFoodAmount(mapInfo[locationX+1][locationY+offSet].getResources().get(2).getLevel());
                userData[locationX+1][locationY+offSet].setEnergyAmount(mapInfo[locationX+1][locationY+offSet].getResources().get(0).getLevel());
                userData[locationX+1][locationY+offSet].setOreAmount(mapInfo[locationX+1][locationY+offSet].getResources().get(1).getLevel());
            }

        }
        // EAST LOCATION
        if(validCoordinate(locationX+1,locationY+1+offSet)){
            userData[locationX+1][locationY+offSet+1].setTerrainType(mapInfo[locationX+1][locationY+offSet+1].getTerrainType());
            if(mapInfo[locationX+1][locationY+offSet+1].getResources().size() != 3){
                // all set to 0
            } else {
                userData[locationX+1][locationY+offSet+1].setFoodAmount(mapInfo[locationX+1][locationY+offSet+1].getResources().get(2).getLevel());
                userData[locationX+1][locationY+offSet+1].setEnergyAmount(mapInfo[locationX+1][locationY+offSet+1].getResources().get(0).getLevel());
                userData[locationX+1][locationY+offSet+1].setOreAmount(mapInfo[locationX+1][locationY+offSet+1].getResources().get(1).getLevel());
            }
        }
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
