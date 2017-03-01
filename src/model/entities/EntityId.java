package model.entities;

import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by cduica on 2/25/17.
 */
public class EntityId extends CustomID {

    private CustomID playerId;

    public EntityId(IdType idType, CustomID playerId, String id) {
        super(idType, id);
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId.getId();
    }


    public boolean equals(EntityId entityId) {
        return (super.equals(entityId) && playerId.equals(entityId.getPlayerId()));
    }
}
