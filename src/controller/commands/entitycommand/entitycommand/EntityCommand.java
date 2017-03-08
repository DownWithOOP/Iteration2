package controller.commands.entitycommand.entitycommand;

import controller.commands.entitycommand.AbstractCommand;
import model.entities.Entity;

/**
 * Created by cduica on 3/8/17.
 */
public abstract class EntityCommand extends AbstractCommand {
    protected Entity entity;

    public EntityCommand(Entity entity, int numTurns){
        super(numTurns);
        this.entity = entity;
    }

}
