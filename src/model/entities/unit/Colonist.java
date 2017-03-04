package model.entities.unit;

import model.entities.EntityType;
import model.entities.StructureFactory;
import model.entities.structure.Capital;
import model.entities.structure.Structure;

/**
 * Created by jordi on 2/24/2017.
 */
public class Colonist extends Unit{
    private StructureFactory capitalFactory;

    public Colonist() {
        super(EntityType.COLONIST);
    }

    @Override
    void abandonArmy() {

    }

    @Override
    void joinArmy() {

    }

    public Structure buildCapital() {
        return capitalFactory.getStructure(EntityType.CAPITAL);
    }
}
