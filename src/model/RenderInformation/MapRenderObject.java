package model.RenderInformation;

import model.map.tile.resources.ResourceType;
import model.map.tile.terrain.TerrainType;

import java.util.List;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MapRenderObject {
    private TerrainType terrainType;
    private List<ResourceType> resourceTypes;

    public MapRenderObject(TerrainType terrainType, List<ResourceType> resourceTypes){
        this.terrainType = terrainType;
        this.resourceTypes = resourceTypes;
    }
    public TerrainType getTerrainType(){
        return this.terrainType;
    }
    public List<ResourceType> getResourceTypes(){
        return this.resourceTypes;
    }
}
