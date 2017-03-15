package controller.commands;

import java.util.function.Consumer;

/**
 * Created by jordi on 2/20/2017.
 */
public interface Command {

    boolean execute();

    void setCommandType(CommandType commandType);
    CommandType getCommandType();

}
