package controller.commands.entitycommand.unitcommand.colonistcommand;

import model.entities.unit.Colonist;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class BuildCapitalCommand extends ColonistCommand {
    private CustomID customID;
    private String id;

    public BuildCapitalCommand(Colonist colonist, CustomID customId, String id) {
        super(colonist, 5);
        this.customID = customId;
        this.id = id;
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
