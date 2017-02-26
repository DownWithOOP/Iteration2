package model.map.tile;

import utilities.Observer;
import utilities.Subject;

/**
 * Created by cduica on 2/21/17.
 */
public class Tile implements Subject {

    private boolean hasEntity;
    private boolean isPassable;
//    private Terrain terrain;
//    private AreaEffect areaEffect;
//    private EntityID entityID;

    public Tile(){

    }


    @Override
    public void setObserver(Observer o) {

    }

    @Override
    public void notifyObserver() {

    }
}
