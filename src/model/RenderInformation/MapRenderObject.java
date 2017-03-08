package model.RenderInformation;

import model.map.tile.resources.Resource;
import model.map.tile.terrain.TerrainType;

import java.util.List;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MapRenderObject {
    private TerrainType terrainType;
    private List<Resource> resources;

    public MapRenderObject(TerrainType terrainType, List<Resource> resources){
        this.terrainType = terrainType;
        this.resources = resources;
    }
    public TerrainType getTerrainType(){
        return this.terrainType;
    }
    public List<Resource> getResources(){
        return this.resources;
    }
}
