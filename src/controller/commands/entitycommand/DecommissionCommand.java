package controller.commands.entitycommand;

import controller.commands.Command;
import model.entities.Entity;

/**
 * Created by jordi on 2/28/2017.
 */
public class DecommissionCommand implements Command {
    Entity entity;
    public DecommissionCommand(Entity entity){
        this.entity=entity;
    }

    @Override
    public void execute() {
        entity.decommission();
    }
}
