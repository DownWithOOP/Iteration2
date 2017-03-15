package controller.commands.entitycommand.unitcommand.colonistcommand;

import model.entities.EntityType;
import model.entities.unit.Colonist;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class BuildCapitalCommand extends ColonistCommand {
    private CustomID customID;
    private String id;

    public BuildCapitalCommand(Colonist colonist) {
        super(colonist, 2);
        this.customID = colonist.getPlayerId();
        this.id = "1";
    }

    public CustomID getCustomID() {
        return customID;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            getColonist().buildCapital(customID, id);
            return true;
        }
        return false;
    }
}
