package model.entities;

/**
 * Created by LesliesLaptop on 2/4/17.
 */
public class EntityId {
    private EntityType entityType;

    public EntityId(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
