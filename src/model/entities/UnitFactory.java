package model.entities;

import model.entities.unit.*;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class UnitFactory {
    public Unit getEntity(EntityType unitType, CustomID customID, String id, int locationX, int locationY) {
        if (unitType == null) {
            return null;
        }
        switch(unitType) {
            case COLONIST:
                return new Colonist(customID, id, locationX, locationY);
            case EXPLORER:
                return new Explorer(customID, id, locationX, locationY);
            case RANGED:
                return new Ranged(customID, id, locationX, locationY);
            case MELEE:
                return new Melee(customID, id, locationX, locationY);
            case WORKER:
                return new Worker(customID, id, locationX, locationY);
            // TODO: Figure out what Army is inheriting/implementing
//           case ARMY:
//              return new Army();
            default:
                return null;
        }
    }
}
