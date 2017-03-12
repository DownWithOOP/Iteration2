package model.cycling.iterators;

import model.cycling.type.ArmyType;
import model.cycling.type.Type;
import utilities.id.IdType;

/**
 * Created by Jonathen on 3/11/2017.
 * TODO make this cycle through entire army/battle group/reinforcement types
 */
public class ArmyTypeIterator extends TypeIterator {

    private static final IdType[] idArray = {IdType.ARMY};
    private static final ArmyType[] typeArray = {new ArmyType()};

    public ArmyTypeIterator() {
        super(idArray, typeArray);
    }
}
