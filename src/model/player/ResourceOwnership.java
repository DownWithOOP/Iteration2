package model.player;

import model.map.tile.resources.Resource;
import utilities.id.CustomID;

import java.util.ArrayList;
import java.util.List;

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

    public void addEnergyResource(Resource energyResource){

    }

    public void addOreResource(Resource oreResource){

    }

    public void addFoodResource(Resource foodResource){

    }

    public Resource allocateEnergyResource(){
        return null;
    }

    public Resource allocateOreResource(){
        return null;
    }

    public Resource allocateFoodResource(){
        return null;
    }

}
