package model.entities;

import controller.availablecommands.Commandable;
import model.entities.Stats.Stats;

/**
 * Created by jordi on 2/24/2017.
 */
public class Entity extends Commandable {

    private EntityId entityId;
    private boolean isPoweredDown;

    public Entity(EntityType entityType){
        entityId = new EntityId(entityType);
        this.isPoweredDown = false; // entity is powered up by default
    }

    public EntityId getEntityId(){
        return entityId;
    }
    public void getLocation(){

    }

    public void decommision(){

    }
    public void addToQueue(){

    }
    public void cancelQueue(){

    }
    public void powerUp(Stats entityStats) {
        if (isPoweredDown()) {
            int originalUpkeep = entityStats.getDefaultUpkeep();
            entityStats.setUpkeep(originalUpkeep);
            setPoweredDown(false);
        }
    }
    public void powerDown(Stats entityStats) {
        if (!isPoweredDown()) {
            int upkeep = entityStats.getUpkeep();
            int loweredUpkeep = Math.round((int)(upkeep * .25));
            entityStats.setUpkeep(loweredUpkeep);
            setPoweredDown(true);
        }

    }

    public boolean isPoweredDown() {
        return isPoweredDown;
    }

    public void setPoweredDown(boolean isPoweredDown) {
        this.isPoweredDown = isPoweredDown;
    }
}
