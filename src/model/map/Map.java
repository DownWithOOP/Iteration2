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

        height = Integer.parseInt(mapXMLParser.getMapAttribute("height"));
        width = Integer.parseInt(mapXMLParser.getMapAttribute("width"));

        ArrayList<HashMap<String, String>> tileList = mapXMLParser.parseDocument();

        AreaEffectFactory areaEffectFactory = new AreaEffectFactory();

        int x = 0;
        int y = 0;

        for(int i = 0; i < tileList.size(); i++){

            HashMap<String , String > map = tileList.get(i);
            ResourceType resourceType = ResourceType.EMPTY;
            DecalType decalType = DecalType.EMPTY;
            TerrainType terrainType = TerrainType.EMPTY;
            AreaEffectType areaEffectType = AreaEffectType.EMPTY;

            if (!map.get("Resource").equals("")) {
                resourceType = ResourceType.valueOf(map.get("Resource"));
            }
            if (!map.get("Terrain").equals("")) {
                terrainType = TerrainType.valueOf(map.get("Terrain"));
            }
            if (!map.get("Decal").equals("")) {
                decalType = DecalType.valueOf(map.get("Decal"));
            }
            if (!map.get("AreaEffect").equals("")){
                areaEffectType = AreaEffectType.valueOf(map.get("AreaEffect"));
            }
            AreaEffect areaEffect = areaEffectFactory.createAreaEffect(areaEffectType);

            Tile tile = new Tile(
                    new Terrain(terrainType, decalType),
                    areaEffect,
                    new Resource(resourceType),
                    null
            );


            Location location = new Location(x, y);

            if( y!=0 && y % (width - 1) == 0){
                x++;
                y = 0;
            } else {
                y++;
            }

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

    public static void main(String[] args){
        Map map = new Map();
    }

}
