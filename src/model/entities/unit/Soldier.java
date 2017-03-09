package model.entities.unit;

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
    public Soldier(CustomID playerId, String id, int locationX, int locationY) {
        super(playerId, id, locationX, locationY);
    }
}
