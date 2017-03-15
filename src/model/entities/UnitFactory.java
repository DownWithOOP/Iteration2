package model.entities;

import controller.CommandRelay;
import model.EntityIdManager;
import model.entities.unit.*;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/3/17.
 */
public class UnitFactory {

    private CommandRelay commandRelay;

    private EntityIdManager entityIdManager;

    public UnitFactory(CommandRelay commandRelay) {
        this.commandRelay = commandRelay;
        entityIdManager = EntityIdManager.getInstance();
    }

    public Unit getEntity(EntityType unitType, CustomID customID, int locationX, int locationY) {
        if (unitType == null) {
            return null;
        }
        switch(unitType) {
            case COLONIST:
                return new Colonist(commandRelay, customID, entityIdManager.getColonistId(), locationX, locationY);
            case EXPLORER:
                return new Explorer(commandRelay, customID, entityIdManager.getExplorerId(), locationX, locationY);
            case RANGED:
                return new Ranged(commandRelay, customID, entityIdManager.getRangedId(), locationX, locationY);
            case MELEE:
                return new Melee(commandRelay, customID, entityIdManager.getMeleeId(), locationX, locationY);
            case WORKER:
                return new Worker(commandRelay, customID, entityIdManager.getWorkerId(), locationX, locationY);
            // TODO: Figure out what Army is inheriting/implementing
//           case ARMY:
//              return new Army();
            default:
                return null;
        }
    }
}
