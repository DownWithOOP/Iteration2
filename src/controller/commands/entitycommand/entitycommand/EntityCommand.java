package controller.commands.entitycommand.entitycommand;

import controller.commands.Command;
import model.entities.Entity;

/**
 * Created by jordi on 3/1/2017.
 */
public abstract class EntityCommand implements Command {
    protected Entity entity;

    public EntityCommand(Entity entity){
        this.entity=entity;
    }


}
