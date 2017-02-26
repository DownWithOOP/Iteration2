package model;

import controller.availablecommands.Commandable;
import controller.commands.Command;

/**
 * Created by Jonathen on 2/25/2017.
 */
public class Selection {

    //TODO initialize command as well once EntityOwnership is set up to cycle actions
    public Selection(Commandable initialCommandable){
        selectedCommandable = initialCommandable;
    }

    Commandable selectedCommandable;
    Command selectedCommand;

    public void updateSelectedCommandable(Commandable newCommandable){
        selectedCommandable = newCommandable;
    }

}
