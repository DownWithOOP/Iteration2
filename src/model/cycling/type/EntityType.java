package model.cycling.type;

import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import model.entities.Entity;
import utilities.id.CustomID;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class EntityType extends Type{

    private Map<CustomID, Entity> entities = new TreeMap<>();

    private Entity currentEntity;

    public EntityType() {

    }

    @Override
    public Commandable getCurrentInstance() {
        return null;
    }

    @Override
    public CommandType getCurrentCommand() {
        return null;
    }

    public void addEntity(Entity newEntity) {
        entities.put(newEntity.getPlayerId(), newEntity);
    }
}
