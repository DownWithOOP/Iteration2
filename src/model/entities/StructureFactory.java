package model.entities;

import controller.CommandRelay;
import model.EntityIdManager;
import model.entities.structure.*;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class StructureFactory {

    private CommandRelay commandRelay;

    private EntityIdManager entityIdManager;

    public StructureFactory(CommandRelay commandRelay) {
        this.commandRelay = commandRelay;
        this.entityIdManager = EntityIdManager.getInstance();
    }

    public Structure getStructure(EntityType structureType, CustomID customID, int locationX, int locationY) {
        if (structureType == null) {
            return null;
        }
        System.out.println("struct fact building " + structureType);
        switch(structureType) {
            case CAPITAL:
                return new Capital(commandRelay, customID, entityIdManager.getCapitalId(), locationX, locationY);
            case FARM:
                return new Farm(commandRelay, customID, entityIdManager.getFarmId(), locationX, locationY);
            case FORT:
                return new Fort(commandRelay, customID, entityIdManager.getFortId(), locationX, locationY);
            case MINE:
                return new Mine(commandRelay, customID, entityIdManager.getMineId(), locationX, locationY);
            case OBSERVATION_TOWER:
                return new ObservationTower(commandRelay, customID, entityIdManager.getObservationTowerId(), locationX, locationY);
            case POWER_PLANT:
                return new PowerPlant(commandRelay, customID, entityIdManager.getPowerPlantId(), locationX, locationY);
            case UNIVERSITY:
                return new University(commandRelay, customID, entityIdManager.getUniversityId(), locationX, locationY);
            default:
                return null;
        }
    }
}
