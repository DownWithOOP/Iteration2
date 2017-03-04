package model.entities;

import model.entities.structure.*;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class StructureFactory {
    public Structure getStructure(EntityType structureType, CustomID customID, String id) {
        if (structureType == null) {
            return null;
        }
        switch(structureType) {
            case CAPITAL:
                return new Capital(customID, id);
            case FARM:
                return new Farm(customID, id);
            case FORT:
                return new Fort(customID, id);
            case MINE:
                return new Mine(customID, id);
            case OBSERVATIONTOWER:
                return new ObservationTower(customID, id);
            case POWERPLANT:
                return new PowerPlant(customID, id);
            case UNIVERSITY:
                return new University(customID, id);
            default:
                return null;
        }
    }
}
