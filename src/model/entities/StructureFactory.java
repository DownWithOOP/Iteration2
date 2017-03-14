package model.entities;

import controller.CommandRelay;
import model.entities.structure.*;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class StructureFactory {

    private CommandRelay commandRelay;

    public StructureFactory(CommandRelay commandRelay) {
        this.commandRelay = commandRelay;
    }

    public Structure getStructure(EntityType structureType, CustomID customID, String id, int locationX, int locationY) {
        if (structureType == null) {
            return null;
        }
        switch(structureType) {
            case CAPITAL:
                return new Capital(commandRelay, customID, id, locationX, locationY);
            case FARM:
                return new Farm(commandRelay, customID, id, locationX, locationY);
            case FORT:
                return new Fort(commandRelay, customID, id, locationX, locationY);
            case MINE:
                return new Mine(commandRelay, customID, id, locationX, locationY);
            case OBSERVATIONTOWER:
                return new ObservationTower(commandRelay, customID, id, locationX, locationY);
            case POWERPLANT:
                return new PowerPlant(commandRelay, customID, id, locationX, locationY);
            case UNIVERSITY:
                return new University(commandRelay, customID, id, locationX, locationY);
            default:
                return null;
        }
    }
}
