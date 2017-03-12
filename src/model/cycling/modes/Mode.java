package model.cycling.modes;

import controller.commands.CommandType;
import model.cycling.iterators.TypeIterator;
import model.cycling.type.Type;
import utilities.id.IdType;

/**
 * Created by Jonathen on 3/11/2017.
 */
public abstract class Mode {

    private TypeIterator typeItr;

    protected Mode(TypeIterator typeItr) { this.typeItr = typeItr; }

    public Type getCurrentType() { return typeItr.getCurrentType(); }
    public CommandType getCurrentCommand() { return typeItr.getCurrentCommand(); }
    @Override
    public abstract String toString();
}
