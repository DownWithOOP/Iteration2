package model.cycling.iterators;

import model.cycling.type.EntityType;
import utilities.id.IdType;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class StructureTypeIterator extends TypeIterator {

    private static final IdType[] idArray       =   {IdType.CAPITAL, IdType.FARM, IdType.FORT, IdType.MINE, IdType.OBSERVATION_TOWER, IdType.POWER_PLANT, IdType.UNIVERSITY};
    private static final EntityType[] typeArray =   {new EntityType(), new EntityType(), new EntityType(), new EntityType(), new EntityType(), new EntityType(), new EntityType()};

    public StructureTypeIterator() {

        super(idArray, typeArray);
    }
}
