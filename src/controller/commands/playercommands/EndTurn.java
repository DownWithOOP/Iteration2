package controller.commands.playercommands;

import controller.Controller;
import controller.ControllerDispatch;
import controller.commands.Command;
import controller.commands.CommandType;
import model.GameModel;

/**
 * Created by Jonathen on 3/15/2017.
 */
public class EndTurn implements Command {

    private GameModel modelToActOn;
    private ControllerDispatch dispatchToActOn;

    public EndTurn(GameModel model, ControllerDispatch dispatch) {
        modelToActOn = model;
        dispatchToActOn = dispatch;
    }

    @Override
    public boolean execute() {
        modelToActOn.endTurn();
        dispatchToActOn.resetMap();
        return true;
    }

    @Override
    public void setCommandType(CommandType commandType) {
        //do nothin
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.END_TURN;
    }
}
