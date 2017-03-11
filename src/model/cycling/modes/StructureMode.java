package model.cycling.modes;

import model.cycling.iterators.StructureTypeIterator;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class StructureMode extends Mode{

    public StructureMode() {
        super(new StructureTypeIterator());
    }

    @Override
    public String toString() { return "STRUCTURE"; }
}
