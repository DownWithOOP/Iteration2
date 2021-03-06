package controller.commands.modifiers;

import controller.commands.Direction;
import model.entities.EntityType;
import model.entities.structure.StructureType;
import model.entities.unit.Unit;
import model.entities.unit.UnitType;

/**
 * Created by jordi on 3/1/2017.
 */
public class Modifier {
    public Direction direction;
    public int number;
    public EntityType entityType;
    //public EntityType structureType;
    public Unit unit;
    private ModifierType modifierType;

    public Modifier(Direction direction) {
        this.direction = direction;
        modifierType=ModifierType.DIRECTION;
    }

    public Modifier(int number) {
        this.number = number;
        modifierType= ModifierType.NUMBER;
    }

    public Modifier(EntityType entityType) {
        this.entityType = entityType;
        modifierType=ModifierType.ENTITY_TYPE;
    }

//    public Modifier(EntityType structureType) {
//        this.structureType = structureType;
//        modifierType=ModifierType.STRUCTURE_TYPE;
//    }

    public Modifier(Unit unit) {
        this.unit = unit;
        modifierType = ModifierType.UNIT;
    }

    public ModifierType getModifierType(){
        return modifierType;
    }
}
