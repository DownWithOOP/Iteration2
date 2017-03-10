package model.entities;

import model.entities.structure.*;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class StructureFactory {
    public Structure getStructure(EntityType structureType, CustomID customID, String id, int locationX, int locationY) {
        if (structureType == null) {
            return null;
        }
        switch(structureType) {
            case CAPITAL:
                return new Capital(customID, id, locationX, locationY);
            case FARM:
                return new Farm(customID, id, locationX, locationY);
            case FORT:
                return new Fort(customID, id, locationX, locationY);
            case MINE:
                return new Mine(customID, id, locationX, locationY);
            case OBSERVATIONTOWER:
                return new ObservationTower(customID, id, locationX, locationY);
            case POWERPLANT:
                return new PowerPlant(customID, id, locationX, locationY);
            case UNIVERSITY:
                return new University(customID, id, locationX, locationY);
            default:
                return null;
        }
    }
}
