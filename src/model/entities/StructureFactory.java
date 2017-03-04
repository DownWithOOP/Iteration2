package model.entities;

import model.entities.structure.*;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class StructureFactory {
    public Structure getStructure(EntityType structureType) {
        if (structureType == null) {
            return null;
        }
        switch(structureType) {
            case CAPITAL:
                return new Capital();
            case FARM:
                return new Farm();
            case FORT:
                return new Fort();
            case MINE:
                return new Mine();
            case OBSERVATIONTOWER:
                return new ObservationTower();
            case POWERPLANT:
                return new PowerPlant();
            case UNIVERSITY:
                return new University();
            default:
                return null;
        }
    }
}
