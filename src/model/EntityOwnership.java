package model;

//import controller.commands.ActionModifiers;
//import model.common.Location;
import controller.availablecommands.Commandable;
import controller.commands.DirectionType;
import model.entities.Entity;
import model.entities.EntityType;
import model.entities.structure.Capital;
import model.entities.structure.Structure;
import model.entities.unit.Colonist;
import model.entities.unit.Explorer;
import model.entities.unit.Melee;
import model.entities.unit.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles relationships between a Player and all of their Entities. The responsibilities of this class include
 * 1) Maintaining status of all current Entitys the Player owns
 * 2) Cycling through Entitys when told to by the Player
 */
public class EntityOwnership {
    //TODO not hardcode these indices
    List<List<Entity>> unitList;          //Ranged=0, Melee=1, Colonist=2, Explorer=3
    //TODO make this army actually be an army
    List<List<Entity>> structureList;         //base=0
    List<List<Entity>> currentModeList; //TODO refactor to a list of commandabls so that rallypoints can be handled like other cases
    List<RallyPoint> rallyPointList;
    Mode modeHolders[] = Mode.values();

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
    private int selectedArmyIndex = 0;
    private int cycleModeIndex = 1; //start in UNIT mode

    EntityOwnership() {
        unitList = new ArrayList<>(5);
        //armyList = new ArrayList<>(10);
        structureList = new ArrayList<>(1);
        rallyPointList= new ArrayList<>(20);
        initializeLists();
        initializeUnits();
        initializeStructures();
        changeMode(modeHolders[cycleModeIndex]);
    }

    private void initializeStructures() {
        addStructure(EntityType.CAPITAL, new Capital());
    }

    private void initializeUnits() {
        addUnit(EntityType.EXPLORER, new Explorer());
        addUnit(EntityType.EXPLORER, new Explorer());
        addUnit(EntityType.COLONIST, new Colonist());
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
        EntityType entityType = entity.getEntityId().getEntityType();
        boolean returnValue = false;

        //TODO handle adding army

        returnValue = addStructure(entityType, entity);
        if (returnValue == false) {
            returnValue = addUnit(entityType, entity);
        }
        return returnValue;
    }

    public boolean addStructure(EntityType entityType, Entity entity) {
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

    private boolean addUnit(EntityType entityType, Entity entity) {
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

    public Entity cycleType(DirectionType direction) {
        cycleInstanceIndex = 0;
        Entity temp = null;
        if (currentModeList == null) {
            return null;
        }
        if (direction == DirectionType.INCREMENT) {
            temp=incrementType();
        }
        if (direction == DirectionType.DECREMENT) {
            temp=decrementType();
        }
        return temp;
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


    public Entity cycleInstance(DirectionType direction) {

        if (currentModeList == null || currentModeList.get(cycleTypeIndex).size()==0) {
            return null;
        }
        if (direction == DirectionType.INCREMENT) {
            cycleInstanceIndex = next(currentModeList.get(cycleTypeIndex).size(), cycleInstanceIndex);
        }
        if (direction == DirectionType.DECREMENT) {
            cycleInstanceIndex = previous(currentModeList.get(cycleTypeIndex).size(), cycleInstanceIndex);
        }
        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
    }

    public RallyPoint cycleInstanceOfRallyPoint(DirectionType direction){
        if (rallyPointList == null) {
            return null;
        }
        if (direction == DirectionType.INCREMENT) {
            cycleInstanceIndex = next(rallyPointList.size(), cycleInstanceIndex);
        }
        if (direction == DirectionType.DECREMENT) {
            cycleInstanceIndex = previous(rallyPointList.size(), cycleInstanceIndex);
        }
        return rallyPointList.get(cycleInstanceIndex);

    }

    public Entity cycleMode(DirectionType direction){
        if (direction == DirectionType.INCREMENT){
            cycleModeIndex=next(modeHolders.length,cycleModeIndex);
        }
        if (direction == DirectionType.DECREMENT){
            cycleModeIndex=previous(modeHolders.length,cycleModeIndex);
        }
        return changeMode(modeHolders[cycleModeIndex]);
    }

    private Entity changeMode(Mode currentMode) {
        System.out.println("CHANGING mode to " + currentMode);
        resetIndices();
        switch (currentMode) {
            case ARMY:
                //selectedArmy = armyList.get(selectedArmyIndex);
                //if (selectedArmy != null) {
                    //currentModeList = selectedArmy.getCircleTypeList();
                //}
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
                //currentModeList=null;
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
        EntityType entityType= entity.getEntityId().getEntityType();
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

    //TODO change this method so that it doesn't return units in order to render them
    public List<Unit> getUnit(){
        List<Unit> renderList= new ArrayList<>();
        for (List<Entity> list:
                unitList) {
            for (Entity ent:
                    list) {
                Unit temp=(Unit)ent;
                renderList.add(temp);
            }
        }
        return renderList;
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

    /**
     * @return current instance
     */
    public Commandable getCurrentInstance(){
        System.out.println("GETTING current instance");
        System.out.println("instance index " + cycleInstanceIndex);
        System.out.println("type index " + cycleTypeIndex);
        System.out.println("current mode list " + currentModeList);
        System.out.println("type list " + currentModeList.get(cycleTypeIndex));
        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
    }

    //TODO get army
    //public List<Army> getArmy(){
        //return armyList;
    //}


    public static void main(String[] args) {
        EntityOwnership entityOwnership = new EntityOwnership();
        //Army army = new Army(new Player("hello", new Map()), new Location(1, 2));
        //Army army1 = new Army(new Player("world", new Map()), new Location(3, 2));
        Melee melee = new Melee();
        Melee melee1 = new Melee();
        Melee melee2 = new Melee();
        Melee melee3 = new Melee();
        Melee melee4 = new Melee();
        Explorer explorer1 = new Explorer();
        Capital base = new Capital();

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
        Entity entity = entityOwnership.changeMode(Mode.ARMY);
        Entity entity1 = entityOwnership.changeMode(Mode.UNIT);
        System.out.println("currentmodelist after unit cycle" + entityOwnership.currentModeList);
        Melee entity2 = (Melee) entityOwnership.cycleInstance(DirectionType.INCREMENT);
        System.out.println("returned entity after instance cycle" + entity2);
        System.out.println("cycleinstanceindex after instance cycle" + entityOwnership.cycleInstanceIndex);
        entity2 = (Melee) entityOwnership.cycleInstance(DirectionType.INCREMENT);
        entity2 = (Melee) entityOwnership.cycleInstance(DirectionType.INCREMENT);
        entity2 = (Melee) entityOwnership.cycleInstance(DirectionType.DECREMENT);
        entity = entityOwnership.changeMode(Mode.ARMY);
        entity = entityOwnership.cycleInstance(DirectionType.INCREMENT);
        entity = entityOwnership.cycleInstance(DirectionType.INCREMENT);
        entity = entityOwnership.cycleInstance(DirectionType.INCREMENT);
        entity = entityOwnership.cycleInstance(DirectionType.DECREMENT);
        entity = entityOwnership.cycleInstance(DirectionType.INCREMENT);
        entity = entityOwnership.changeMode(Mode.UNIT);

        entity1 = entityOwnership.cycleType(DirectionType.DECREMENT);
        System.out.println("returned entity after type cycle decrement " + entity1);
        entity = entityOwnership.cycleType(DirectionType.INCREMENT);
        System.out.println("returned entity after type cycle increment " + entity);

        System.out.println("does getCurrentInstance work? " + entityOwnership.getCurrentInstance());

        entityOwnership.removeEntity(melee);

        Entity enti3 = entityOwnership.changeMode(Mode.STRUCTURE);

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
