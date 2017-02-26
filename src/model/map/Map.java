package model.map;

import model.common.Location;
import model.map.tile.Tile;
import model.map.tile.areaeffects.AreaEffect;
import model.map.tile.areaeffects.AreaEffectFactory;
import model.map.tile.areaeffects.AreaEffectType;
import model.map.tile.areaeffects.DecalType;
import model.map.tile.resources.Resource;
import model.map.tile.resources.ResourceType;
import model.map.tile.terrain.Terrain;
import model.map.tile.terrain.TerrainType;
import utilities.MapXMLParser;
import utilities.Observer;
import utilities.Subject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cduica on 2/21/17.
 */
public class Map implements Subject {

    private int height;
    private int width;

    private HashMap<Location, Tile> tiles;

    private Observer observer;

    private final String XML_PATH = "res/map/Map.xml";

    public Map() {
        tiles = new HashMap<>();
        init();
    }

    private void init(){

        MapXMLParser mapXMLParser = new MapXMLParser();

        try {
            mapXMLParser.loadDocument(XML_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String, String>> tileList = mapXMLParser.parseDocument();

        AreaEffectFactory areaEffectFactory = new AreaEffectFactory();

        int x = 0;
        int y = 0;

        for(int i = 0; i < tileList.size(); i++){

            HashMap<String , String > map = tileList.get(i);
            ResourceType resourceType= ResourceType.EMPTY;
            DecalType decalType= DecalType.EMPTY;
            TerrainType terrainType= TerrainType.EMPTY;

            if (!map.get("Resource").equals("")) {
                resourceType = ResourceType.valueOf(map.get("Resource"));
            }
            if (!map.get("Terrain").equals("")) {
                terrainType = TerrainType.valueOf(map.get("Terrain"));
            }
            if (!map.get("Decal").equals("")) {
                decalType = DecalType.valueOf(map.get("Decal"));
            }

            AreaEffect areaEffect = areaEffectFactory.createAreaEffect(AreaEffectType.valueOf(map.get("AreaEffect")));

            Tile tile = new Tile(
                    new Terrain(terrainType, decalType),
                    areaEffect,
                    new Resource(resourceType),
                    null
            );

            if(i%width==0){
                y++;
            }

            if(y%width == 0){
                x = 0;
            } else {
                x++;
            }

            Location location = new Location(x, y);

            tiles.put(location, tile);

        }

    }

    public HashMap<Location, Tile> getAdjacentTiles(Location location){

        return null;
    }

    public Tile getTile(Location location){
        return tiles.get(location);
    }

    @Override
    public void setObserver(Observer o){

    }

    @Override
    public void notifyObserver(){

    }
}
