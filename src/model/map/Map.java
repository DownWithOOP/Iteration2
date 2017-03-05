package model.map;

import javafx.geometry.Point2D;
import model.common.Location;
import model.map.tile.MapRenderInformation;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cduica on 2/21/17.
 */
public class Map {

    private int height;
    private int width;

    private HashMap<Location, Tile> tiles;

    private Observer observer;

    private static String OS = System.getProperty("os.name").toLowerCase();
    private final String XML_PATH = "res/map/Map.xml";
    private final String XML_PATH2 = "res/map/Map.xml";

    public Map() {
        tiles = new HashMap<>();
        init();
    }

    // TODO maybe move this to seperate package/file
    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    // TODO maybe move this to seperate package/file
    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }
    private void init(){

        MapXMLParser mapXMLParser = new MapXMLParser();



        try {
            if(isWindows()){
                mapXMLParser.loadDocument(XML_PATH);
            } else{
                mapXMLParser.loadDocument(XML_PATH2);
            }

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
            //Point2D location = new Point2D(x, y);

            if( y!=0 && y % (width - 1) == 0){
                x++;
                y = 0;
            } else {
                y++;
            }
            tiles.put(location, tile);
        }
    }

    // object given to MainView that will be used to render the canvas
    public MapRenderInformation returnRenderInformation(){
            MapRenderInformation renderInfo = new MapRenderInformation(this.width,this.height);
            for(Location location : tiles.keySet() ){
                   renderInfo.addTileToRenderObject(tiles.get(location), (int)location.getX(),(int)location.getY());
            }
            return renderInfo;
    }

    public HashMap<Location, Tile> getAdjacentTiles(Location location){
        return null;
    }

    public Tile getTile(Location location){
        return tiles.get(location);
    }

    // titties

    public static void main(String[] args){
        Map map = new Map();
        System.out.println(map.getTile(new Location(0, 1)));
    }

}
