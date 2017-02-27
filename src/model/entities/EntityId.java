package model.entities;

/**
 * Created by cduica on 2/25/17.
 */
public class EntityId {

    private EntityType entityType;

    public EntityId(EntityType entityType){
        this.entityType = entityType;
    }

    public EntityType getEntityType(){ return entityType; }
}
