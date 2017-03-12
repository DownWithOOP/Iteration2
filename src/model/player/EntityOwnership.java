package model.player;

//import controller.commands.ActionModifiers;
//import model.common.Location;
import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import controller.commands.CycleDirection;
import model.cycling.modes.Mode;
import model.RallyPoint;
import model.RenderInformation.*;
import model.cycling.modes.ModeType;
import model.entities.Entity;
import model.entities.Stats.UnitStats;
import model.entities.structure.Capital;
import model.entities.structure.Structure;
import model.entities.unit.Colonist;
import model.entities.unit.Explorer;
import model.entities.unit.Melee;
import model.entities.unit.Unit;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles relationships between a Player and all of their Entities. The responsibilities of this class include
 * 1) Maintaining status of all current Entitys the Player owns
 * 2) Cycling through Entitys when told to by the Player
 *
 * TODO use commandable instead of entity for currentModeList so that rally points (and armies? idk if armies are entities) can be cycled
 * TODO don't return nulls, throw exceptions instead so that methods calling this class's methods don't have to null check
 */
public class EntityOwnership {
    //TODO not hardcode these indices
    List<List<Entity>> unitList;          //Ranged=0, Melee=1, Colonist=2, Explorer=3
    //TODO make this army actually be an army
    List<List<Entity>> structureList;         //base=0
    List<List<Entity>> currentModeList;
    List<RallyPoint> rallyPointList;

    ModeType modes[] = ModeType.values();

    int typeRestriction = 10;
    int unitCap = 25;
    private RallyPoint selectedRallyPoint=null;
    //TODO use selected army
    //TODO don't hardcode the type indices and number of types
    private final int rangedIndex = 0;
    private final int meleeIndex = 1;
    private final int colonistIndex = 2;
    private final int explorerIndex = 3;
    private final int unitTypeNumber = 5;
    private final int structureTypeNumber = 1;

    private int cycleTypeIndex = 0;
    private int cycleInstanceIndex = 0;
    private int cycleCommandIndex = 0;
    private int selectedArmyIndex = 0;
    private int cycleModeIndex = 1; //start in UNIT mode
    private CustomID playerId;

    EntityOwnership(CustomID playerId, int startingX, int startingY ) {
        unitList = new ArrayList<>(5);
        //armyList = new ArrayList<>(10);
        structureList = new ArrayList<>(1);
        rallyPointList= new ArrayList<>(20);
        initializeLists();
        initializeUnits(startingX, startingY);
        initializeStructures();
        changeMode(modes[cycleModeIndex]);
        this.playerId = playerId;

    }

    private void initializeStructures() {
        //TODO change the id number,
        // TODO strucuture should be intialized by colonist later
        //addStructure(IdType.CAPITAL, new Capital(playerId,"idnumber"));
    }

    private void initializeUnits(int startingX, int startingY) {
        //TODO change the id number
        addUnit(IdType.EXPLORER, new Explorer(playerId,"0", startingX, startingY));
        addUnit(IdType.EXPLORER, new Explorer(playerId,"1", startingX, startingY));
        addUnit(IdType.COLONIST, new Colonist(playerId,"0", startingX, startingY));
    }

    private void initializeLists() {
        for (int i = 0; i < unitTypeNumber; i++) {
            unitList.add(new ArrayList<>());
        }
        for (int i = 0; i < structureTypeNumber; i++) {
            structureList.add(new ArrayList<>());
        }
    }

    public boolean addEntity(Entity entity) {
        IdType entityType = entity.getEntityType();
        boolean returnValue = false;

        //TODO handle adding army

        returnValue = addStructure(entityType, entity);
        if (returnValue == false) {
            returnValue = addUnit(entityType, entity);
        }
        return returnValue;
    }

    public boolean addStructure(IdType entityType, Entity entity) {
        boolean result = false;
        switch (entityType) {
            case CAPITAL:
                result = addToIndex(structureList, 0, entity);
                break;
        }
        return result;
    }


    private boolean addToIndex(List<List<Entity>> entityList, int index, Entity entity) {
        if (entityList.get(index).isEmpty() || entityList.get(index).size() < typeRestriction && !entityList.get(index).contains(entity)) {
            entityList.get(index).add(entity);
            return true;
        }
        return false;
    }

    private boolean addUnit(IdType entityType, Entity entity) {
        int totalSize = 0;
        boolean returnValue = false;
        for (int i = 0; i < unitList.size(); i++) {
            totalSize += unitList.get(i).size();
        }
        if (totalSize < unitCap) {

            switch (entityType) {
                case COLONIST:
                    returnValue = addToIndex(unitList, colonistIndex, entity);
                    break;
                case EXPLORER:
                    returnValue = addToIndex(unitList, explorerIndex, entity);
                    break;
                case MELEE:
                    returnValue = addToIndex(unitList, meleeIndex, entity);
                    break;
                case RANGED:
                    returnValue = addToIndex(unitList, rangedIndex, entity);
                    break;
            }
        }
        return returnValue;
    }


    public static int next(int size, int index) {

        index++;
        index %= size;

        return index;
    }

    public static int previous(int size, int index) {
        index--;
        if (index < 0) {
            index = size - 1;
        }
        return index;
    }

    public Entity cycleType(CycleDirection direction) {
        cycleInstanceIndex = 0;
        Entity temp = null;
        //TODO need both?
        if (currentModeList == null) {
            System.out.println("Cycle type cannot do anything b/c currentModeList is null");
            return null;
        }
        if (currentModeList.isEmpty()) {
            System.out.println("Cycle type cannot do anything b/c currentModeList is empty");
            return null;
        }
        //TODO improve the cycling algorithm so we don't have to do this
        //We can now assume that we will cycle types, so check that there are types to cycle through
        if (!checkForInstancesInType()) {
            System.out.println("There are no instances of any type for the current mode");
            return null;
        }
        if (direction == CycleDirection.INCREMENT) {
            temp=incrementType();
        }
        if (direction == CycleDirection.DECREMENT) {
            temp=decrementType();
        }
        return temp;
    }

    //TODO improve itrs so this isn't necessary
    //Returns false if the type has no instances on the board currently
    //Returns true otherwise
    private boolean checkForInstancesInType() {
        boolean doInstancesExist = false;
        for (int i = 0; i < currentModeList.size(); i++) {
            if (!currentModeList.get(i).isEmpty()) {
                doInstancesExist = true;
            }
        }
        return doInstancesExist;
    }

    private Entity incrementType() {
        int i = cycleTypeIndex + 1;
        int listSize = currentModeList.size();
        while (i != cycleTypeIndex) {
            if (i >= listSize) {
                i %= listSize;
            }
            if (!currentModeList.get(i).isEmpty()) {
                cycleTypeIndex = i;
                return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
            }
            i++;
        }
        return null;
    }

    private Entity decrementType() {
        int i = cycleTypeIndex - 1;
        int listSize = currentModeList.size();
        while (i != cycleTypeIndex) {
            if (i < 0) {
                i=listSize-1;
            }
            if (!currentModeList.get(i).isEmpty()) {
                cycleTypeIndex = i;
                return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
            }
            i--;
        }
        return null;
    }
    public CommandType cycleCommand(CycleDirection direction) {
        if (currentModeList == null || currentModeList.get(cycleTypeIndex).size()==0) {
            return null;
        }
        if (direction == CycleDirection.INCREMENT) {
            cycleCommandIndex = next(currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getEntityCommands().size(), cycleCommandIndex);
        }
        if (direction == CycleDirection.DECREMENT) {
            cycleCommandIndex = previous(currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getEntityCommands().size(), cycleCommandIndex);
        }
        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getEntityCommand(cycleCommandIndex);
    }

    public Entity cycleInstance(CycleDirection direction) {

        if (currentModeList == null || currentModeList.get(cycleTypeIndex).size()==0) {
            return null;
        }
        if (direction == CycleDirection.INCREMENT) {
            cycleInstanceIndex = next(currentModeList.get(cycleTypeIndex).size(), cycleInstanceIndex);
        }
        if (direction == CycleDirection.DECREMENT) {
            cycleInstanceIndex = previous(currentModeList.get(cycleTypeIndex).size(), cycleInstanceIndex);
        }
        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
    }

    public RallyPoint cycleInstanceOfRallyPoint(CycleDirection direction){
        if (rallyPointList == null) {
            return null;
        }
        if (direction == CycleDirection.INCREMENT) {
            cycleInstanceIndex = next(rallyPointList.size(), cycleInstanceIndex);
        }
        if (direction == CycleDirection.DECREMENT) {
            cycleInstanceIndex = previous(rallyPointList.size(), cycleInstanceIndex);
        }
        return rallyPointList.get(cycleInstanceIndex);

    }

    public Entity cycleMode(CycleDirection direction){
        if (direction == CycleDirection.INCREMENT){
            cycleModeIndex=next(modes.length,cycleModeIndex);
        }
        if (direction == CycleDirection.DECREMENT){
            cycleModeIndex=previous(modes.length,cycleModeIndex);
        }
        return changeMode(modes[cycleModeIndex]);
    }

    private Entity changeMode(ModeType currentMode) {
        resetIndices();
        switch (currentMode) {
            case ARMY:
                //selectedArmy = armyList.get(selectedArmyIndex);
                //if (selectedArmy != null) {
                    //currentModeList = selectedArmy.getCircleTypeList();
                //}
                currentModeList = null;
                selectedRallyPoint=null;
                break;
            case UNIT:
                currentModeList = unitList;
                selectedRallyPoint= null;
                break;
            case STRUCTURE:
                currentModeList = structureList;
                selectedRallyPoint=null;
                break;
            case RALLY_POINT:
                //TODO handle rally points
                //selectedRallyPoint=rallyPointList.get(0);
                currentModeList=null;
                break;
        }
        return returnEntityOnModeChange();
    }

    //TODO switch army

    public RallyPoint getRallypoint(){
        return selectedRallyPoint;
    }

    private Entity returnEntityOnModeChange() {
        if (currentModeList != null &&!currentModeList.isEmpty()) {
            for (int i = 0; i < currentModeList.size(); i++) {
                if (!currentModeList.get(i).isEmpty()) {
                    cycleTypeIndex = i;
                    return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
                }
            }
        }
        return null;
    }

    private void resetIndices() {
        selectedArmyIndex = 0;
        cycleTypeIndex = 0;
        cycleInstanceIndex = 0;
    }

    public void removeEntity(Entity entity){
        IdType entityType= entity.getEntityType();
        switch (entityType){
            //case ARMY:
                //armyList.remove(entity);
                //rallyPointList.remove(((Army)entity).getRallyPoint());
                //break;
            case CAPITAL:
                structureList.get(0).remove(entity);
                break;
            case RANGED:
                unitList.get(rangedIndex).remove(entity);
                break;
            case COLONIST:
                unitList.get(colonistIndex).remove(entity);
                break;
            case EXPLORER:
                unitList.get(explorerIndex).remove(entity);
                break;
            case MELEE:
                unitList.get(meleeIndex).remove(entity);
                break;
        }
    }

    public UnitRenderInformation returnUnitRenderInformation() {
        UnitRenderInformation renderInfo = new UnitRenderInformation();
        for (List<Entity> list : unitList) {
            for (Entity entity : list) {
                Unit unit = (Unit) entity;
                UnitStats unitStats = unit.getUnitStats().clone(); // deep clone so as not to mess anything up
                UnitRenderObject temp = new UnitRenderObject(unit.getEntityType(), (int)(unit.getLocation().getX()), (int)(unit.getLocation().getY()), unitStats);
                renderInfo.addUnit(temp);
            }
        }
        return renderInfo;
    }

    public StructureRenderInformation returnStructureRenderInformation() {
        StructureRenderInformation renderInfo = new StructureRenderInformation();
        for (List<Entity> list : structureList) {
            for (Entity entity : list) {
                Structure structure = (Structure) entity;
                StructureRenderObject temp = new StructureRenderObject( structure.getEntityType(),(int)(structure.getLocation().getX()),(int)(structure.getLocation().getY()));
                renderInfo.addStructure(temp);
            }
        }
        return renderInfo;
    }

    public StatusRenderInformation returnStatusRenderInformation() {
        StatusRenderInformation renderInfo = new StatusRenderInformation();
        renderInfo.updateModeString(getCurrentMode());
        renderInfo.updateTypeString(getCurrentType());
        renderInfo.updateInstanceString(getCurrentInstance());
        renderInfo.updateCommandString(getCurrentCommand());

        return renderInfo;
    }

    //TODO change this method so that it doesn't return structures in order to render them
    public List<Structure> getStructure(){
        List<Structure> renderList= new ArrayList<>();
        for (List<Entity> list:
                structureList) {
            for (Entity ent:
                    list) {
                Structure temp=(Structure)ent;
                renderList.add(temp);
            }
        }
        return renderList;
    }

    public CommandType getCurrentCommand() {
        if (currentModeList == null) {
            System.out.println("No current mode list available");
            return null;
        }
        else if (currentModeList.get(cycleTypeIndex) == null) {
            System.out.println("No current type list available");
            return null;
        }
        else if (currentModeList.get(cycleTypeIndex).isEmpty()) {
            System.out.println("Type list is empty");
            return null;
        }
        else if (currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex) == null) {
            System.out.println("Instance list is empty");
            return null;
        }
        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getEntityCommand(cycleCommandIndex);
    }

    /**
     * @return current instance
     */
    public Commandable getCurrentInstance(){
        if (currentModeList == null) {
            System.out.println("No current mode list available");
            return null;
        }
        else if (currentModeList.get(cycleTypeIndex) == null) {
            System.out.println("No current type list available");
            return null;
        }
        else if (currentModeList.get(cycleTypeIndex).isEmpty()) {
            System.out.println("Type list is empty");
            return null;
        }

        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
    }

    //TODO get rid of this; only for debugging purposes
    public String getCurrentType() {
        if (currentModeList == null) {
            System.out.println("No current mode list available");
            return null;
        }
        else if (currentModeList.get(cycleTypeIndex) == null) {
            System.out.println("No current type list available");
            return null;
        }
        else if (currentModeList.get(cycleTypeIndex).isEmpty()) {
            System.out.println("Type list is empty");
            return null;
        }

        Commandable currentCommandable = getCurrentInstance();
        try {
            return ( (Entity) currentCommandable).getEntityType().toString();
        }
        catch (ClassCastException e) {
            return null;
        }
    }

    public void executeCommands() {
        for (int i = 0; i < unitList.size(); i++) {
            for (int j = 0; j < unitList.get(i).size(); j++) {
                unitList.get(i).get(j).executeQueue();
                //TODO: Notify this method if createCapital command (or any create ___ command) is performed
            }
        }
        for (int i = 0; i < structureList.size(); i++) {
            for (int j = 0; j < structureList.get(i).size(); j++) {
                structureList.get(i).get(j).executeQueue();
            }
        }
    }

    //TODO get army
    //public List<Army> getArmy(){
        //return armyList;
    //}

    //TODO get rid of this; only for debugging purposes...actually maybe not b/c view
    public ModeType getCurrentMode() {
        return modes[cycleModeIndex];
    }


    public static void main(String[] args) {
        EntityOwnership entityOwnership = new EntityOwnership(new CustomID(IdType.PLAYER,"hello"), 5, 5);
        //Army army = new Army(new Player("hello", new Map()), new Location(1, 2));
        //Army army1 = new Army(new Player("world", new Map()), new Location(3, 2));
        Melee melee = new Melee(new CustomID(IdType.PLAYER,"hello"),"5",1,1);
        Melee melee1 = new Melee(new CustomID(IdType.PLAYER,"hello"),"5",1,2);
        Melee melee2 = new Melee(new CustomID(IdType.PLAYER,"hello"),"5",1,3);
        Melee melee3 = new Melee(new CustomID(IdType.PLAYER,"hello"),"5",2,2);
        Melee melee4 = new Melee(new CustomID(IdType.PLAYER,"hello"),"5",0,0);
        Explorer explorer1 = new Explorer(new CustomID(IdType.PLAYER,"hello"),"5",0,1);
        Capital base = new Capital(new CustomID(IdType.PLAYER,"hello"),"5", 2,2);

        boolean check = false;
        check = entityOwnership.addEntity(explorer1);
        check = entityOwnership.addEntity(melee);
        check = entityOwnership.addEntity(melee1);
        check = entityOwnership.addEntity(melee2);
        check = entityOwnership.addEntity(melee3);
        check = entityOwnership.addEntity(melee4);
        check = entityOwnership.addEntity(base);

        //TODO actually test army
        //TODO change into assert statements?
        Entity entity = entityOwnership.changeMode(ModeType.ARMY);
        Entity entity1 = entityOwnership.changeMode(ModeType.UNIT);
        System.out.println("currentmodelist after unit cycle" + entityOwnership.currentModeList);
        Melee entity2 = (Melee) entityOwnership.cycleInstance(CycleDirection.INCREMENT);
        System.out.println("returned entity after instance cycle" + entity2);
        System.out.println("cycleinstanceindex after instance cycle" + entityOwnership.cycleInstanceIndex);
        entity2 = (Melee) entityOwnership.cycleInstance(CycleDirection.INCREMENT);
        entity2 = (Melee) entityOwnership.cycleInstance(CycleDirection.INCREMENT);
        entity2 = (Melee) entityOwnership.cycleInstance(CycleDirection.DECREMENT);
        entity = entityOwnership.changeMode(ModeType.ARMY);
        entity = entityOwnership.cycleInstance(CycleDirection.INCREMENT);
        entity = entityOwnership.cycleInstance(CycleDirection.INCREMENT);
        entity = entityOwnership.cycleInstance(CycleDirection.INCREMENT);
        entity = entityOwnership.cycleInstance(CycleDirection.DECREMENT);
        entity = entityOwnership.cycleInstance(CycleDirection.INCREMENT);
        entity = entityOwnership.changeMode(ModeType.UNIT);

        entity1 = entityOwnership.cycleType(CycleDirection.DECREMENT);
        System.out.println("returned entity after type cycle decrement " + entity1);
        entity = entityOwnership.cycleType(CycleDirection.INCREMENT);
        System.out.println("returned entity after type cycle increment " + entity);

        System.out.println("does getCurrentInstance work? " + entityOwnership.getCurrentInstance());

        entityOwnership.removeEntity(melee);

        Entity enti3 = entityOwnership.changeMode(ModeType.STRUCTURE);

        //entityOwnership.switchArmy(ActionModifiers.one); TODO test switch army
        System.out.println("check " + check);
        System.out.println("unitlist " + entityOwnership.unitList);
        System.out.println("structurelist " + entityOwnership.structureList);
        System.out.println("currentmodelist " + entityOwnership.currentModeList);
        System.out.println("selectedrallypoint " + entityOwnership.selectedRallyPoint);
        System.out.println("rangedindex " + entityOwnership.rangedIndex);
        System.out.println("meleeindex " + entityOwnership.meleeIndex);
        System.out.println("colonistindex " + entityOwnership.colonistIndex);
        System.out.println("explorerindex " + entityOwnership.explorerIndex);

        System.out.println("cycletypeindex " + entityOwnership.cycleTypeIndex);
        System.out.println("cycleinstanceindex " + entityOwnership.cycleInstanceIndex);
        System.out.println("selectedarmyindex " + entityOwnership.selectedArmyIndex);
        System.out.println("cyclemodeindex " + entityOwnership.cycleModeIndex);
   }

}
