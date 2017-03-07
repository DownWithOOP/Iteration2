package model;

import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import utilities.id.CustomID;

/**
 * Created by jordi on 3/6/2017.
 * <p>
 * Keeps track of the active player (this might change)
 * Keeps track of the active unit and the active cursor
 */
public class ActiveState {
    static Cursor cursor;
    static Commandable activeCommandable;
    static CommandType activeCommand;

    public ActiveState() {

    }

    public void update(Commandable commandable) {
        activeCommandable = commandable;
    }

    public void update(CommandType commandType) {
        activeCommand = commandType;
    }

    
}
