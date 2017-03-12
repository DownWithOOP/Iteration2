package model.cycling.iterators;

import model.cycling.type.EntityType;
import utilities.id.IdType;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class UnitTypeIterator extends TypeIterator{

    private static final IdType[] idArray = {IdType.RANGED, IdType.COLONIST, IdType.EXPLORER, IdType.MELEE, IdType.WORKER};
    private static final EntityType[] typeArray = {new EntityType(), new EntityType(), new EntityType(), new EntityType(), new EntityType()};

    public UnitTypeIterator() {
        super(idArray, typeArray);
    }
}
