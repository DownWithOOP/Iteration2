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

    // Lowers resource level and returns amount of resource mined
    public int mineResource() {
        int mined = (int)Math.random()*100;
        if (level - mined > 0) {
            level -= mined;
            return mined;
        }
        else {
            int temp = level;
            level = 0;
            return temp;
        }
    }

    public int getLevel() {
        return level;
    }

}
