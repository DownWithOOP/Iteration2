package model.entities;

import model.entities.unit.*;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class UnitFactory {
    public Unit getEntity(EntityType unitType) {
        if (unitType == null) {
            return null;
        }
        switch(unitType) {
            case COLONIST:
                return new Colonist();
            case EXPLORER:
                return new Explorer();
            case RANGED:
                return new Ranged();
            case MELEE:
                return new Melee();
            case WORKER:
                return new Worker();
            // TODO: Figure out what Army is inheriting/implementing
//           case ARMY:
//              return new Army();
            default:
                return null;
        }
    }
}
