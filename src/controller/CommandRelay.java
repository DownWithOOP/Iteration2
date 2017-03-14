package controller;

import controller.commands.Direction;
import model.GameModel;
import model.common.Location;
import model.entities.unit.FighterUnit;

/**
 * Created by Jonathen on 3/14/2017.
 *
 * Used by Commandables to talk to GameModel
 */
public class CommandRelay {

    private GameModel model;

    public CommandRelay(GameModel model) {
        this.model = model;
    }

    public void notifyModelOfAttack(Location location, int damage) {
        model.applyDamageToEntitiesByLocation(location, damage);
    }

    public void notifyModelOfUnitUpdate() {

    }

    public void notifyModelOfUnitJoiningArmy(FighterUnit fighterUnit, int armyNumber) {
        model.addFighterUnitToArmy(fighterUnit, armyNumber);
    }
}
