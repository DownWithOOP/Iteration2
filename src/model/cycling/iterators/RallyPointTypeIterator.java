package model.cycling.iterators;

import model.cycling.type.RallyPointType;
import utilities.id.IdType;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class RallyPointTypeIterator extends TypeIterator {

    private static final IdType[] idArray = {IdType.RALLY_POINT};
    private static final RallyPointType[] typeArray = {new RallyPointType()};

    public RallyPointTypeIterator() {
        super(idArray, typeArray);
    }
}
