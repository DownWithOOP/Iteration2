package model.RenderInformation;

import model.map.tile.Tile;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MapRenderInformation {
    private int MapX; // dimensions of X
    private int MapY; // dimensions of Y
    private MapRenderObject[][] renderInfo;

    public MapRenderInformation(int mapX, int mapY){
        this.MapX = mapX;
        this.MapY = mapY;
        this.renderInfo = new MapRenderObject[mapX][mapY];
    }
    public void addTileToRenderObject(Tile tile, int x, int y){
        MapRenderObject renderObject = new MapRenderObject(tile.getTerrain().getTerrainType(),tile.getResources());
        renderInfo[x][y] = renderObject;
    }
    public MapRenderObject[][] getRenderObjectMap(){
        return this.renderInfo;
    }
    public int getX(){
        return this.MapX;
    }
    public int getY(){
        return this.MapY;
    }
}
