package model.entities.unit;

import model.entities.EntityType;
import model.entities.StructureFactory;
import model.entities.structure.Structure;

/**
 * Created by jordi on 2/24/2017.
 */
public class Worker extends Unit {
    private StructureFactory structureFactory;

    public Worker() {
        super(EntityType.WORKER);
    }

    public Structure buildStructure(EntityType entityType) {
        if (!entityType.equals(EntityType.CAPITAL)) {
            return structureFactory.getStructure(entityType);
        }
        return null;
    }

    @Override
    void abandonArmy() {

    }

    @Override
    void joinArmy() {

    }
}
