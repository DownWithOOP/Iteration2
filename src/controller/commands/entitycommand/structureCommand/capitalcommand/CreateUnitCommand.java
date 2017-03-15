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
    private String id;

    public CreateUnitCommand(Capital capital, EntityType entityToCreate) {
        super(capital, 3);
        super.setCommandType(CommandType.CREATE_UNIT);
        this.entityToCreate = entityToCreate;
        this.customId = capital.getPlayerId();
        this.id = "0";
    }

    public EntityType getEntityToCreate() {
        return entityToCreate;
    }

    public CustomID getCustomId() {
        return customId;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getCapital().createUnit(getEntityToCreate(), getCustomId(), getId());
            return true;
        }
        return false;
    }
}
