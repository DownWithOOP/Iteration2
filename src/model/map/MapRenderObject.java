package model.map;

import model.map.tile.resources.ResourceType;
import model.map.tile.terrain.TerrainType;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MapRenderObject {
    private TerrainType terrainType;
    private ResourceType resourceType;

    public MapRenderObject(TerrainType terrainType, ResourceType resourceType){
        this.terrainType = terrainType;
        this.resourceType = resourceType;
    }
    public TerrainType getTerrainType(){
        return this.terrainType;
    }
    public ResourceType getResourceType(){
        return this.resourceType;
    }
}
