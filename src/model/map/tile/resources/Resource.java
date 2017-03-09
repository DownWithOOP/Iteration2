package model.map.tile.resources;

/**
 * Created by cduica on 2/25/17.
 */
public class Resource {

    private ResourceType resourceType;
    private int level;

    public Resource(ResourceType resourceType, int level){
       this.resourceType = resourceType;
       this.level = level;
    }

    public ResourceType getResourceType(){
        return resourceType;
    }

    //consumes some resource
    public void consumeResource(double percent){

    }

    public int getLevel() {
        return level;
    }

}
