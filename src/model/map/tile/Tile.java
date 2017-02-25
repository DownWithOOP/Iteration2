package model.map.tile;

import model.entities.EntityID;
import model.map.tile.areaeffects.AreaEffect;
import model.map.tile.item.Item;
import model.map.tile.resources.Resource;
import model.map.tile.terrain.Terrain;
import utilities.Observer;
import utilities.Subject;

/**
 * Created by cduica on 2/21/17.
 */
public class Tile implements Subject {

    private boolean hasEntity;
    private boolean isPassable;
    private Terrain terrain;
    private AreaEffect areaEffect;
    private EntityID entityID;
    private Resource resource;
    private Item item;

    public Tile(Terrain terrain, AreaEffect areaEffect, Resource resource, Item item) {
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.resource = resource;
        this.item = item;
    }

    public void setEntity(EntityID entityID){
        this.entityID = entityID;
        this.hasEntity = true;
    }

    public void removeEntity(){
        this.entityID = null;
        this.hasEntity = false;
    }

    public boolean hasEntity() {
        return hasEntity;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

    public EntityID getEntityID() {
        return entityID;
    }

    public Resource getResource() {
        return resource;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void setObserver(Observer o) {

    }

    @Override
    public void notifyObserver() {

    }
}
