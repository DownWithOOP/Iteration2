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
    public int consumeResource(double percent){
        int amount = (int) (level*percent);
        level = level - amount;
        return amount;
    }

    public void addResource(int level){
        this.level += level;
    }

    public int getLevel() {
        return level;
    }

}
