package controller.commands.entitycommand.unitcommand;

import model.entities.unit.Unit;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class MoveUnitCommand extends UnitCommand {
    private int newX;
    private int newY;

    public MoveUnitCommand(Unit unit, int newX, int newY) {
        super(unit);
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public void execute() {
        unit.moveUnit(newX, newY);
    }
}
