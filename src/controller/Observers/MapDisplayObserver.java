package controller.Observers;

import model.RenderInformation.*;
import model.entities.EntityId;
import utilities.id.CustomID;

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
    private boolean allVisible = false; // FOR DEBUGGING ONLY, TURN ON IF NEED BE TO SEE EVERYTHING

    private int currPlayerNumber;
    private MapRenderInformation currMapRenderInformation;
    private UnitRenderInformation currUnitRenderInformation;
    private StructureRenderInformation currStructureRenderInformation;

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

        this.currPlayerNumber = playerNumber;
        this.currMapRenderInformation = mapRenderInformation;
        this.currStructureRenderInformation = structureRenderInformation;
        this.currUnitRenderInformation = unitRenderInformation;

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
                //clear units while we're at it
                check.removeUserEntity();
                if(check.getVisibilityLevel() == 2){
                    check.setVisibilityToOne();
                }
                if(allVisible){ // sets everything to visible
                    check.setVisibilityTwo();
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
                updateLevelTwoLOS(playerNumber,offSet,unit.getLocationX(),unit.getLocationY(),mapInfo);
            }
        }

        ArrayList<StructureRenderObject> playerStructureInfo = structureRenderInformation.returnRenderInformation();
        for(StructureRenderObject structure: playerStructureInfo){

            TileRenderObject temp = userData[structure.getLocationX()][structure.getLocationY()];
            temp.setVisibilityTwo();
            temp.addUserEntity(structure.getIdType());

            int offSet = 0;
            if(structure.getLocationX()%2 == 0){
                offSet = 0; }
            else {
                offSet =-1;
            }

            // int LOS = structure.
            int LOS = structure.getStructureStats().getVisionRadius();
            if(LOS <= 1){ // set 1 radius out as all visible
                updateLevelOneLOS(playerNumber,offSet,structure.getLocationX(),structure.getLocationY(),mapInfo);
            }
            if(LOS <= 2){ // set 2 radius out as all visible
                updateLevelTwoLOS(playerNumber,offSet,structure.getLocationX(),structure.getLocationY(),mapInfo);
            }
        }


        if(allVisible){
            for(int i=0; i<mapSizeX; i++){
                for(int j=0; j<mapSizeY; j++){
                    userData[i][j].setTerrainType(mapInfo[i][j].getTerrainType());
                    userData[i][j].setVisibilityTwo();
                    if(mapInfo[i][j].getResources().size() != 3){
                        // all set to 0
                    } else {
                        userData[i][j].setFoodAmount(mapInfo[i][j].getResources().get(2).getLevel());
                        userData[i][j].setEnergyAmount(mapInfo[i][j].getResources().get(0).getLevel());
                        userData[i][j].setOreAmount(mapInfo[i][j].getResources().get(1).getLevel());
                    }
                }
            }
        }

    }

    @Override
    public void updateUnit(EntityId unitId, UnitRenderObject unitRenderObject){
        //this.currUnitRenderInformation = unitRenderInformation;
        if(currUnitRenderInformation!=null){
            currUnitRenderInformation.removeUnit(unitId);
            currUnitRenderInformation.addUnit(unitRenderObject);
            update(currPlayerNumber, currMapRenderInformation, currUnitRenderInformation, currStructureRenderInformation);
        }
    }

    @Override
    public void updateStructure(EntityId structureId, StructureRenderObject structureRenderObject){
        //this.currStructureRenderInformation = structureRenderInformation;
        if(currStructureRenderInformation!=null){
            currStructureRenderInformation.removeStructure(structureId);
            currStructureRenderInformation.addStructure(structureRenderObject);
            update(currPlayerNumber, currMapRenderInformation, currUnitRenderInformation, currStructureRenderInformation);
        }
    }

    @Override
    public MapRenderInformation share() {
        return this.mapRenderInformation;
    }

    @Override
    public TileRenderObject[][] getPlayerXRenderMap(int playerNumber) {
        return this.playerTiles.get(playerNumber-1);
    }

    // checks if coordinate is withing bounds for the map
    private boolean validCoordinate(int x, int y){
        if(x < 0 || x > this.mapSizeX){ return false; }
        else if (y < 0 || y > this.mapSizeY) { return false; }
        else { return true; }
    }



    private void updateLevelOneLOS(int playerNumber, int offSet, int locationX, int locationY, MapRenderObject[][] mapInfo){


        TileRenderObject[][] userData = playerTiles.get(playerNumber-1);

        updateTile(userData,locationX,locationY,mapInfo); // Tile
        updateTile(userData,locationX,locationY+1,mapInfo); // N
        updateTile(userData,locationX,locationY-1,mapInfo); // S
        updateTile(userData,locationX-1,locationY+1+offSet,mapInfo); // W
        updateTile(userData,locationX-1,locationY+offSet,mapInfo); // W
        updateTile(userData,locationX+1,locationY+1+offSet,mapInfo); // E
        updateTile(userData,locationX+1,locationY+offSet,mapInfo); // E

    }

    private void updateLevelTwoLOS(int playerNumber, int offSet, int locationX, int locationY, MapRenderObject[][] mapInfo){

        TileRenderObject[][] userData = playerTiles.get(playerNumber-1);

        updateTile(userData,locationX,locationY+2,mapInfo); // N
        updateTile(userData,locationX,locationY-2,mapInfo); // NS
        updateTile(userData,locationX-1,locationY+2+offSet,mapInfo); // NW
        updateTile(userData,locationX+1,locationY+2+offSet,mapInfo); // NE
        updateTile(userData,locationX-1,locationY-1+offSet,mapInfo); // SW
        updateTile(userData,locationX+1,locationY-1+offSet,mapInfo); // SE
        updateTile(userData,locationX-2,locationY+1,mapInfo); // W
        updateTile(userData,locationX-2,locationY,mapInfo); // W
        updateTile(userData,locationX-2,locationY-1,mapInfo); // W
        updateTile(userData,locationX+2,locationY+1,mapInfo); // E
        updateTile(userData,locationX+2,locationY,mapInfo); // E
        updateTile(userData,locationX+2,locationY-1,mapInfo); // E
    }

    private void updateTile(TileRenderObject[][] userData, int X, int Y,MapRenderObject[][] mapInfo ){
        if(validCoordinate(X,Y)){
            userData[X][Y].setTerrainType(mapInfo[X][Y].getTerrainType());
            userData[X][Y].setVisibilityTwo();
            if(mapInfo[X][Y].getResources().size() != 3){
                // all set to 0
            } else {
                userData[X][Y].setFoodAmount(mapInfo[X][Y].getResources().get(2).getLevel());
                userData[X][Y].setEnergyAmount(mapInfo[X][Y].getResources().get(0).getLevel());
                userData[X][Y].setOreAmount(mapInfo[X][Y].getResources().get(1).getLevel());
            }
        }
    }
}
