package model.cycling.modes;

import model.cycling.iterators.RallyPointTypeIterator;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class RallyPointMode extends Mode {

    public RallyPointMode() { super(new RallyPointTypeIterator()); }

    @Override
    public String toString() { return "RALLY POINT"; }
}
