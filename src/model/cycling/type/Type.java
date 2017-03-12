package model.cycling.type;

import controller.availablecommands.Commandable;
import controller.commands.CommandType;

/**
 * Created by Jonathen on 3/11/2017.
 */
public abstract class Type {

    protected int commandIndex;

    public abstract Commandable getCurrentInstance();
    public abstract CommandType getCurrentCommand();
}
