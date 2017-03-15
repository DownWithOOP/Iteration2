package controller.commands.entitycommand.structureCommand.capitalcommand;

import controller.commands.CommandType;
import model.entities.EntityType;
import model.entities.structure.Capital;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class CreateUnitCommand extends CapitalCommand {
    private EntityType entityToCreate;
    private CustomID customId;

    public CreateUnitCommand(Capital capital, EntityType entityToCreate) {
        super(capital, 3);
        super.setCommandType(CommandType.CREATE_UNIT);
        this.entityToCreate = entityToCreate;
        this.customId = capital.getPlayerId();
    }

    public EntityType getEntityToCreate() {
        return entityToCreate;
    }

    public CustomID getCustomId() {
        return customId;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getCapital().createUnit(getEntityToCreate(), getCustomId());
            return true;
        }
        return false;
    }
}
