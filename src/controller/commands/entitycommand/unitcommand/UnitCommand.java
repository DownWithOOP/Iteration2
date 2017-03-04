package controller.commands.entitycommand.unitcommand;

import controller.commands.Command;
import model.entities.unit.Unit;

import java.rmi.server.UnicastRemoteObject;

/**
 * Created by jordi on 3/1/2017.
 */
public abstract class UnitCommand implements Command{
    Unit unit;

    public UnitCommand(Unit unit){
        this.unit=unit;
    }
}
