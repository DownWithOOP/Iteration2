package model.entities;

import controller.commands.Direction;

/**
 * Created by jordi on 2/24/2017.
 */
public interface Fighter {
    void attack(Direction direction);
    void defend(Direction direction);
}
