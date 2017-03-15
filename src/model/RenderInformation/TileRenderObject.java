package model.RenderInformation;

import model.entities.EntityType;
import model.map.tile.terrain.TerrainType;
import utilities.id.IdType;

import java.util.ArrayList;

/**
 * Created by Konrad on 3/9/2017.
 */
public class TileRenderObject {

    /*
        Level takes 3 values, 0 = unknown, 1 = greyed out, 2= completely visible
     */
    private int visibilityLevel;
    private final int locationX;
    private final int locationY;
    private TerrainType terrainType;
    private int foodAmount =0;
    private int oreAmount = 0;
    private int energyAmount =0;
    private ArrayList<IdType> userEntities;
    private ArrayList<IdType> enemyEntities;

    public TileRenderObject(int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.visibilityLevel = 0; // at construction, everything is unknown
        userEntities = new ArrayList<IdType>();
        enemyEntities = new ArrayList<IdType>();
    }

    public void setTerrainType(TerrainType terrainType){
        this.terrainType = terrainType;
    }
    public void setFoodAmount(int foodAmount){
        this.foodAmount = foodAmount;
    }
    public void setOreAmount(int oreAmount){
        this.oreAmount = oreAmount;
    }
    public void setEnergyAmount(int energyAmount){
        this.energyAmount = energyAmount;
    }
    public void addUserEntity(IdType entity){
        this.userEntities.add(entity);
    }
    public void removeUserEntity(){
        userEntities.clear();
    }
    public void addEnemyEntity(IdType entity){
        this.enemyEntities.add(entity);
    }
    public ArrayList<IdType> getUserEntities(){
        return this.userEntities;
    }
    public ArrayList<IdType> getEnemyEntities() { return this.enemyEntities; }
    public void removeAllEnemies(){
        this.enemyEntities = null;
        this.enemyEntities = new ArrayList<IdType>();

    }
    public int getVisibilityLevel(){
        return this.visibilityLevel;
    }
    public TerrainType getTerrainType(){
        return this.terrainType;
    }
    public void setVisibilityTwo(){
        this.visibilityLevel = 2;
    }
    public void setVisibilityToOne(){
        this.visibilityLevel = 1;
    }
    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }
    public int getFoodAmount(){
        return this.foodAmount;
    }
    public int getOreAmount(){
        return this.oreAmount;
    }
    public int getEnergyAmount(){
        return this.energyAmount;
    }

}
