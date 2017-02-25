package model.map;

import model.common.Location;
import model.map.tile.Tile;
import utilities.Observer;
import utilities.Subject;

import java.util.HashMap;
import java.util.List;

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
        init();
    }

    private void init(){

    }

    public Tile getTile(Location point){
        return null;
    }

    public List<Tile> getAdjacentTiles(Location point){
        return null;
    }

    @Override
    public void setObserver(Observer o){

    }

    @Override
    public void notifyObserver(){

    }
}
