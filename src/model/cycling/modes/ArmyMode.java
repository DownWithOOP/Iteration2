package model.cycling.modes;

import model.cycling.iterators.ArmyTypeIterator;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class ArmyMode extends Mode{

    public ArmyMode() { super(new ArmyTypeIterator()); }

    @Override
    public String toString() { return "ARMY"; }
}
