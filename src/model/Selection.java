package model;

import controller.availablecommands.Commandable;
import controller.commands.Command;
import controller.commands.CommandType;

/**
 * Created by Jonathen on 2/25/2017.
 */
public class Selection {

    //TODO initialize activeCommand as well once EntityOwnership is set up to cycle actions
    public Selection(Commandable initialCommandable){
        selectedCommandable = initialCommandable;
    }

    Commandable selectedCommandable;
    CommandType selectedCommand;

    public void updateSelectedCommand(CommandType newCommand) {
        selectedCommand = newCommand;
    }

    public void updateSelectedCommandable(Commandable newCommandable){
        selectedCommandable = newCommandable;
    }

}
