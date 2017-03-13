package controller.commands.entitycommand.unitcommand;

import model.entities.unit.Unit;

/**
 * Created by LesliesLaptop on 3/8/17.
 */
public class MoveUnitCommand extends UnitCommand {
    private int newX;
    private int newY;

    public MoveUnitCommand(Unit unit, int newX, int newY) {
        super(unit, 1);
        this.newX = newX;
        this.newY = newY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    @Override
    public boolean execute() {
        if(super.execute()) {
            super.getUnit().moveUnit(getNewX(), getNewY());
            return true;
        }
        return false;
    }
}
