package model.cycling.iterators;

import controller.commands.CommandType;
import model.cycling.modes.*;
import utilities.id.IdType;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;

/**
 * Created by Jonathen on 3/11/2017.
 */
public class ModeIterator {

    private Map<ModeType, Mode> modes = new TreeMap();

    private Mode currentMode;
    private Iterator itr;


    public ModeIterator() {
        modes.put(ModeType.RALLY_POINT, new RallyPointMode());
        modes.put(ModeType.STRUCTURE, new StructureMode());
        modes.put(ModeType.UNIT, new UnitMode());
        modes.put(ModeType.ARMY, new ArmyMode());

        itr = modes.entrySet().iterator();
        currentMode = (Mode) itr.next();
        System.out.println(currentMode);
    }

    public Mode getCurrentMode() { return currentMode; }

    public IdType getCurrentType() { return currentMode.getCurrentType(); }

    public CommandType getCurrentCommand() { return currentMode.getCurrentCommand(); }

    //TODO change return type
    public void cycleMode() { currentMode = (Mode) itr.next();}
}
