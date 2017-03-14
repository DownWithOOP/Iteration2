package model.entities.unit;

import controller.CommandRelay;
import utilities.id.CustomID;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class Soldier extends FighterUnit {

    /**
     * @param playerId
     * @param id
     * @param locationX
     * @param locationY
     */
    public Soldier(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay, playerId, id, locationX, locationY);
    }
}
