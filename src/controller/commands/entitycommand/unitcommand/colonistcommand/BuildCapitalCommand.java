package controller.commands.entitycommand.unitcommand.colonistcommand;

import controller.commands.CommandType;
import model.entities.EntityType;
import model.entities.unit.Colonist;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class BuildCapitalCommand extends ColonistCommand {
    private CustomID customID;

    public BuildCapitalCommand(Colonist colonist) {
        super(colonist, 2);
        super.setCommandType(CommandType.BUILD_CAPITAL);
        this.customID = colonist.getPlayerId();
    }

    public CustomID getCustomID() {
        return customID;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getColonist().buildCapital(customID);
            return true;
        }
        return false;
    }
}
