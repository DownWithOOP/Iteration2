package model.map.tile;

import model.map.tile.areaeffects.AreaEffect;
import model.map.tile.item.Item;
import model.map.tile.resources.Resource;
import model.map.tile.terrain.Terrain;
import utilities.Observer;
import utilities.Subject;
import utilities.id.CustomID;

/**
 * Created by cduica on 2/21/17.
 */
public class Tile implements Subject {

    private boolean hasEntity;
    private boolean isPassable;
    private boolean isVisible;

    private Terrain terrain;
    private AreaEffect areaEffect;
    private CustomID entityID;
    private Resource resource;
    private Item item;

    public Tile(Terrain terrain, AreaEffect areaEffect, Resource resource, Item item) {
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.resource = resource;
        this.item = item;
        this.isVisible = false;
    }

    /**
     * Entity related stuff
     */

    public void setEntity(CustomID entityID){
        this.entityID = entityID;
        this.hasEntity = true;
    }

    public void removeEntity(){
        this.entityID = null;
        this.hasEntity = false;
    }

    /**
     * Getters
     */

    public boolean hasEntity() {
        return hasEntity;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public boolean isVisible(){
        return isVisible;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

    public CustomID getEntityID() {
        return entityID;
    }

    public Resource getResource() {
        return resource;
    }

    public Item getItem() {
        return item;
    }

    /**
     * Tile hiding stuff
     */
    public void revealTile(){
        isVisible = true;
    }

    @Override
    public void setObserver(Observer o) {

    }

    @Override
    public void notifyObserver() {

    }
}
