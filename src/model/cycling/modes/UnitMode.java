package model.cycling.modes;

import model.cycling.iterators.UnitTypeIterator;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class UnitMode extends Mode{

    public UnitMode() { super(new UnitTypeIterator()); }

    @Override
    public String toString() { return "UNIT"; }
}
