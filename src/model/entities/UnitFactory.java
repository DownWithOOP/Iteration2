package model.entities;

import controller.CommandRelay;
import model.entities.unit.*;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class UnitFactory {

    private CommandRelay commandRelay;

    public UnitFactory(CommandRelay commandRelay) {
        this.commandRelay = commandRelay;
    }

    public Unit getEntity(EntityType unitType, CustomID customID, String id, int locationX, int locationY) {
        if (unitType == null) {
            return null;
        }
        switch(unitType) {
            case COLONIST:
                return new Colonist(commandRelay, customID, id, locationX, locationY);
            case EXPLORER:
                return new Explorer(commandRelay, customID, id, locationX, locationY);
            case RANGED:
                return new Ranged(commandRelay, customID, id, locationX, locationY);
            case MELEE:
                return new Melee(commandRelay, customID, id, locationX, locationY);
            case WORKER:
                return new Worker(commandRelay, customID, id, locationX, locationY);
            // TODO: Figure out what Army is inheriting/implementing
//           case ARMY:
//              return new Army();
            default:
                return null;
        }
    }
}
