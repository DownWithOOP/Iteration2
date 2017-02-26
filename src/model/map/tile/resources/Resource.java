package model.map.tile.resources;

/**
 * Created by cduica on 2/25/17.
 */
public class Resource {

    private ResourceType resourceType;
    private int level;

    public Resource(ResourceType resourceType){
       this.resourceType = resourceType;
       this.level = 100;
    }

    public ResourceType getResourceType(){
        return resourceType;
    }

}
