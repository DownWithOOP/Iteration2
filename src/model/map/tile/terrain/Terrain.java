package model.map.tile.terrain;

import model.map.tile.areaeffects.DecalType;

/**
 * Created by cduica on 2/25/17.
 */
public class Terrain {

    private boolean isPassable;
    private boolean decalPresent;
    private TerrainType terrainType;
    private DecalType decalType;

    public Terrain(TerrainType terrainType, DecalType decalType){
        this.terrainType = terrainType;
        this.decalType = decalType;
        if(terrainType.equals(TerrainType.WATER)){
            isPassable = false;
        } else {
            isPassable = true;
        }
    }

    public boolean isPassable() {
        return isPassable;
    }

    public boolean decalPresent() {
        return decalPresent;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public DecalType getDecalType() {
        return decalType;
    }
}
