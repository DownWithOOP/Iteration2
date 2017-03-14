package controller.commands.modifiers;

import controller.commands.Direction;
import model.entities.structure.StructureType;
import model.entities.unit.UnitType;

/**
 * Created by jordi on 3/1/2017.
 */
public class Modifier {
    public Direction direction;
    public int number;
    public UnitType unitType;
    public StructureType structureType;
    private ModifierType modifierType;

    public Modifier(Direction direction) {
        this.direction = direction;
        modifierType=ModifierType.DIRECTION;
    }

    public Modifier(int number) {
        this.number = number;
        modifierType= ModifierType.NUMBER;
    }

    public Modifier(UnitType unitType) {
        this.unitType = unitType;
        modifierType=ModifierType.UNIT_TYPE;
    }

    public Modifier(StructureType structureType) {
        this.structureType = structureType;
        modifierType=ModifierType.STRUCTURE_TYPE;
    }

    public ModifierType getModifierType(){
        return modifierType;
    }
}
