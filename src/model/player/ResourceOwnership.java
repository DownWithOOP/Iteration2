package model.player;

import model.map.tile.resources.Resource;
import model.map.tile.resources.ResourceType;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by cduica on 3/9/17.
 */
public class ResourceOwnership {

    private CustomID playerID;
    private Resource energyResources;
    private Resource oreResources;
    private Resource foodResources;

    ResourceOwnership(CustomID playerID){
        this.playerID = playerID;
    }

    public void addResource(Resource resource){

        int currLevel;

        switch(resource.getResourceType()){
            case ENERGY:
                if(energyResources == null) {
                    energyResources = resource;
                } else {
                    currLevel = energyResources.getLevel();
                    currLevel += resource.getLevel();
                    energyResources = new Resource(resource.getResourceType(), currLevel);
                }
                break;
            case ORE:
                if(oreResources == null){
                    oreResources = resource;
                } else {
                    currLevel = oreResources.getLevel();
                    currLevel += resource.getLevel();
                    oreResources = new Resource(resource.getResourceType(), currLevel);
                }
                break;
            case FOOD:
                if(foodResources == null){
                    foodResources = resource;
                } else {
                    currLevel = foodResources.getLevel();
                    currLevel += resource.getLevel();
                    foodResources = new Resource(resource.getResourceType(), currLevel);
                }
                break;
            case EMPTY:
                break;
        }

    }

    public Resource allocateEnergyResource(){
        int level = energyResources.consumeResource(0.10);

        return new Resource(ResourceType.ENERGY, level);
    }

    public Resource allocateOreResource(){
        int level = oreResources.consumeResource(0.10);

        return new Resource(ResourceType.ORE, level);
    }

    public Resource allocateFoodResource(){
        int level = foodResources.consumeResource(0.10);

        return new Resource(ResourceType.FOOD, level);
    }

    public static void main(String[] args){
        ResourceOwnership resourceOwnership = new ResourceOwnership(new CustomID(IdType.PLAYER, "f"));

        resourceOwnership.addResource(new Resource(ResourceType.FOOD, 100));
        resourceOwnership.addResource(new Resource(ResourceType.ORE, 100));
        resourceOwnership.addResource(new Resource(ResourceType.ENERGY, 100));

        resourceOwnership.addResource(new Resource(ResourceType.ORE, 10));
        Resource test = resourceOwnership.allocateEnergyResource();

    }

}
