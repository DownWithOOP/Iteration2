package controller;

import controller.commands.Direction;
import model.GameModel;
import model.common.Location;

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

    public void notifyModelOfAttack(Location location, Direction direction, int damage) {
        //model.applyDamageToEntitiesByLocation(location, direction, damage);
    }

    public void notifyModelOfUnitUpdate() {

    }
}
