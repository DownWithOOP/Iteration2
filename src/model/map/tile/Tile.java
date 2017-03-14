package model.map.tile;

import model.map.tile.areaeffects.AreaEffect;
import model.map.tile.item.Item;
import model.map.tile.resources.Resource;
import model.map.tile.resources.ResourceType;
import model.map.tile.terrain.Terrain;
import model.map.tile.terrain.TerrainType;
import utilities.Observer;
import utilities.Subject;
import utilities.id.CustomID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cduica on 2/21/17.
 */
public class Tile implements Subject {

    private boolean hasEntity;
    private boolean isPassable;
    private boolean isVisible;

    private Terrain terrain;
    private AreaEffect areaEffect;
    private CustomID playerId;
    private List<Resource> resources;
    private Item item;

    public Tile(Terrain terrain, AreaEffect areaEffect, List<Resource> resources, Item item) {
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.resources = resources;
        this.item = item;
        this.isVisible = false;
        this.isPassable = terrain.isPassable();
    }

    /**
     * Entity related stuff
     */

    public void setPlayerId(CustomID playerId){
        this.playerId = playerId;
        this.hasEntity = true;
    }

    public void removeEntity(){
        this.playerId = null;
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

    public List<Resource> getResources() {
        return resources;
    }

    public List<ResourceType> getResourceTypes() {
        List<ResourceType> resourceTypes = new ArrayList<>();

        for(int i = 0; i < resources.size(); i++){
            resourceTypes.add(resources.get(i).getResourceType());
        }

        return resourceTypes;
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

    public CustomID getPlayerId() { return playerId; }
}
