package model.player;

//import controller.commands.ActionModifiers;
//import model.common.Location;
import controller.CommandRelay;
import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import controller.commands.CycleDirection;
import model.ArmyMode;
import model.EntityIdManager;
import model.Mode;
import model.RallyPoint;
import model.RenderInformation.*;
import model.common.Location;
import model.entities.Entity;
import model.entities.EntityId;
import model.entities.EntityType;
import model.entities.Stats.UnitStats;
import model.entities.UnitFactory;
import model.entities.structure.Capital;
import model.entities.structure.Structure;

import model.entities.unit.*;

import model.entities.unit.Colonist;
import model.entities.unit.Explorer;
import model.entities.unit.Melee;
import model.entities.unit.Unit;
import utilities.ObserverInterfaces.MapObserver;
import utilities.ObserverInterfaces.UnitObserver;
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
    //TODO change types of lists
    List<List<Entity>> unitList;          //Ranged=0, Melee=1, Colonist=2, Explorer=3
    List<Army> armyList;
    List<Entity> armySubModeList;
    List<List<Entity>> structureList;         //base=0
    List<List<Entity>> currentModeList;
    List<RallyPoint> rallyPointList;

    Mode modes[] = Mode.values();
    ArmyMode armySubModes[] = ArmyMode.values();

    int typeRestriction = 10;
    int unitCap = 25;
    private RallyPoint selectedRallyPoint = null;
    private Army selectedArmy = null;

    //TODO don't hardcode the type indices and number of types
    private final int rangedIndex = 0;
    private final int meleeIndex = 1;
    private final int colonistIndex = 2;
    private final int explorerIndex = 3;
    private final int unitTypeNumber = 5;

    private final int capitalIndex = 0;
    private final int farmIndex = 1;
    private final int fortIndex = 2;
    private final int mineIndex = 3;
    private final int observationTowerIndex = 4;
    private final int powerPlantIndex = 5;
    private final int universityIndex = 6;
    private final int structureTypeNumber = 7;

    private int cycleTypeIndex = 0;
    private int cycleInstanceIndex = 0;
    private int cycleCommandIndex = 0;
    private int selectedArmyIndex = 0;
    private int cycleModeIndex = 1; //start in UNIT mode
    private CustomID playerId;

    private CommandRelay commandRelay;

    private UnitFactory unitFactory;

    private EntityIdManager idManager;

    public EntityOwnership(CustomID playerId, CommandRelay commandRelay, int startingX, int startingY ) {
        unitList = new ArrayList<>(5);
        armyList = new ArrayList<>(typeRestriction);
        structureList = new ArrayList<>(1);
        rallyPointList= new ArrayList<>(typeRestriction);

        this.unitFactory = new UnitFactory(commandRelay);
        this.commandRelay = commandRelay;
        this.idManager = new EntityIdManager();

        this.playerId = playerId;

        initializeLists();
        initializeUnits(startingX, startingY);
        changeMode(modes[cycleModeIndex]);


        System.out.println("End of E.O. constructor; cycle type index is: " + cycleTypeIndex);
    }

    private void initializeUnits(int startingX, int startingY) {
        //TODO change the id number
        addUnit(IdType.EXPLORER, unitFactory.getEntity(EntityType.EXPLORER, playerId,"0", startingX, startingY));
        addUnit(IdType.EXPLORER, unitFactory.getEntity(EntityType.EXPLORER, playerId,"1", startingX+1, startingY));
        addUnit(IdType.COLONIST, unitFactory.getEntity(EntityType.COLONIST, playerId,"0", startingX, startingY-1));
    }

    private void initializeLists() {
        for (int i = 0; i < unitTypeNumber; i++) {
            unitList.add(new ArrayList<>(typeRestriction));
        }
        for (int i = 0; i < structureTypeNumber; i++) {
            structureList.add(new ArrayList<>(typeRestriction));
        }
    }

    public void setUnitObservers(UnitObserver unitObserver, MapObserver mapObserver){
        for(int i = 0; i < unitList.size(); i++){
            for(int j = 0; j < unitList.get(i).size(); j++){
                ((Unit) unitList.get(i).get(j)).registerUnitObserver(unitObserver);
                ((Unit) unitList.get(i).get(j)).registerMapObserver(mapObserver);
            }
        }
    }

    //TODO remove this
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
                removeEntity(unitList.get(2).get(0)); //remove colonist
                addEntity(unitFactory.getEntity(EntityType.MELEE, playerId, "0", (int)entity.getLocation().getX(), (int)entity.getLocation().getY()));
                addEntity(unitFactory.getEntity(EntityType.MELEE, playerId, "1", (int)entity.getLocation().getX(), (int)entity.getLocation().getY()));
                result = addToIndex(structureList, capitalIndex, entity);
                break;
            case FARM:
                result = addToIndex(structureList, farmIndex, entity);
                break;
            case FORT:
                result = addToIndex(structureList, fortIndex, entity);
                break;
            case MINE:
                result = addToIndex(structureList, mineIndex, entity);
                break;
            case OBSERVATION_TOWER:
                result = addToIndex(structureList, observationTowerIndex, entity);
                break;
            case POWER_PLANT:
                result = addToIndex(structureList, powerPlantIndex, entity);
                break;
            case UNIVERSITY:
                result = addToIndex(structureList, universityIndex, entity);
                break;
        }
        return result;
    }

    public boolean createArmy() {
        System.out.println("army list size " + armyList.size());
        Army newArmy = new Army(commandRelay, playerId, idManager.getArmyId(), 0, 0);
        armyList.add(newArmy);
        rallyPointList.add(new RallyPoint(commandRelay, new Location(0,0), newArmy));
        System.out.println("army list size after add" + armyList.size());
        return true;
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
                    commandRelay.notifyModelOfUnitUpdate();
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

    public Commandable cycleType(CycleDirection direction) {
        cycleInstanceIndex = 0;
        Entity temp = null;

        if (getCurrentMode() == Mode.RALLY_POINT) {
            //raly point doesn't cycle
            return selectedRallyPoint;
        }

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
        System.out.println("cycle type index " + cycleTypeIndex);
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
        System.out.println("current mode list in inc type " + currentModeList);
        while (i != cycleTypeIndex) {
            if (i >= listSize) {
                i %= listSize;
            }
            if (!currentModeList.get(i).isEmpty()) {
                System.out.println("setting cycle index to " + i);
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
        System.out.println("current mode list in inc type " + currentModeList);
        while (i != cycleTypeIndex) {
            if (i < 0) {
                i=listSize-1;
            }
            if (!currentModeList.get(i).isEmpty()) {
                System.out.println("setting cycle index to " + i);
                cycleTypeIndex = i;
                return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex);
            }
            i--;
        }
        return null;
    }
    public CommandType cycleCommand(CycleDirection direction) {
        if (getCurrentMode() == Mode.RALLY_POINT) {
            if (selectedRallyPoint == null) {
                return null;
            }
            if (direction == CycleDirection.INCREMENT) {
                cycleCommandIndex = next(selectedRallyPoint.getIterableCommandsSize(), cycleCommandIndex);
            }
            if (direction == CycleDirection.DECREMENT) {
                cycleCommandIndex = previous(selectedRallyPoint.getIterableCommandsSize(), cycleCommandIndex);
            }
            return selectedRallyPoint.getIterableCommand(cycleCommandIndex);
        }
        if (currentModeList == null || currentModeList.get(cycleTypeIndex).size()==0) {
            return null;
        }
        //TODO: Instead getEntityCommands() you could use getIterableCommands, why are you assuming that you are only going to iterate through Entities, Rallypoints also have commands
        if (direction == CycleDirection.INCREMENT) {
            cycleCommandIndex = next(currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getIterableCommandsSize(), cycleCommandIndex);
        }
        if (direction == CycleDirection.DECREMENT) {
            cycleCommandIndex = previous(currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getIterableCommandsSize(), cycleCommandIndex);
        }
        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getIterableCommand(cycleCommandIndex);
    }

    public Commandable cycleInstance(CycleDirection direction) {

       if (getCurrentMode() == Mode.RALLY_POINT) {
            return cycleInstanceOfRallyPoint(direction);
       }
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
        if (rallyPointList.isEmpty()) {
            return null;
        }
        if (direction == CycleDirection.INCREMENT) {
            cycleInstanceIndex = next(rallyPointList.size(), cycleInstanceIndex);
        }
        if (direction == CycleDirection.DECREMENT) {
            cycleInstanceIndex = previous(rallyPointList.size(), cycleInstanceIndex);
        }
        selectedArmyIndex = cycleInstanceIndex;
        selectedArmy = armyList.get(selectedArmyIndex);
        selectedRallyPoint = rallyPointList.get(cycleInstanceIndex);
        return selectedRallyPoint;

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

    private Entity changeMode(Mode currentMode) {
        resetIndices();
        System.out.println("cycle type index " + cycleTypeIndex);
        System.out.println("changing mode to " + currentMode);
        switch (currentMode) {
            case ARMY:
                if (!armyList.isEmpty() && armyList.size() - 1 >= selectedArmyIndex) {
                    selectedArmy = armyList.get(selectedArmyIndex);
                    currentModeList = selectedArmy.getSubModeLists();
                    selectedRallyPoint = null;
                }
                else {
                    currentModeList = null;
                }
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
                if (!rallyPointList.isEmpty() && rallyPointList.size() -1 >= selectedArmyIndex) {
                    selectedRallyPoint = rallyPointList.get(selectedArmyIndex);
                    currentModeList = null;
                }
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
        cycleCommandIndex = 0;
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
                UnitRenderObject temp = new UnitRenderObject(unit.getEntityId(), (int)(unit.getLocation().getX()), (int)(unit.getLocation().getY()), unitStats);
                renderInfo.addUnit(temp);
            }
        }
        for (int i = 0; i < armyList.size(); i++) {
            Army army = armyList.get(i);
            ArmyRenderObject armyRenderObject;
            if (selectedArmyIndex == i) {
                armyRenderObject = new ArmyRenderObject(army.getEntityId(), rallyPointList.get(selectedArmyIndex).getLocation(), true);
            }
            else {
                armyRenderObject = new ArmyRenderObject(army.getEntityId(), rallyPointList.get(selectedArmyIndex).getLocation(), false);
            }

            for (Entity entity : army.getBattleGroup()) {
                Unit unit = (Unit) entity; //we know that everything in army is a unit
                UnitStats unitStats = unit.getUnitStats().clone(); // deep clone so as not to mess anything up
                armyRenderObject.addUnitToBattleGroup(new UnitRenderObject(unit.getEntityId(), (int)(unit.getLocation().getX()), (int)(unit.getLocation().getY()), unitStats));
            }
            for (Entity entity : army.getReinforcements()) {
                Unit unit = (Unit) entity; //we know that everything in army is a unit
                UnitStats unitStats = unit.getUnitStats().clone(); // deep clone so as not to mess anything up
                armyRenderObject.addUnitToReinforcements(new UnitRenderObject(unit.getEntityId(), (int)(unit.getLocation().getX()), (int)(unit.getLocation().getY()), unitStats));
            }
            renderInfo.addArmy(armyRenderObject);
        }
        return renderInfo;
    }

    public StructureRenderInformation returnStructureRenderInformation() {
        StructureRenderInformation renderInfo = new StructureRenderInformation();
        for (List<Entity> list : structureList) {
            for (Entity entity : list) {
                Structure structure = (Structure) entity;
                StructureRenderObject temp = new StructureRenderObject( structure.getEntityId(), structure.getEntityType(),(int)(structure.getLocation().getX()),(int)(structure.getLocation().getY()), structure.getStructureStats());
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
        System.out.println("return status info says that command is " + getCurrentCommand());

        return renderInfo;
    }

    public CommandType getCurrentCommand() {
        if (getCurrentMode() == Mode.RALLY_POINT) {
            if (selectedRallyPoint != null) {
                return selectedRallyPoint.getIterableCommand(cycleCommandIndex);
            }
            else {
                return null;
            }
        }
        else if (currentModeList == null) {
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
        return currentModeList.get(cycleTypeIndex).get(cycleInstanceIndex).getIterableCommand(cycleCommandIndex);
    }

    /**
     * @return current instance
     */
    public Commandable getCurrentInstance(){
        if (getCurrentMode() == Mode.RALLY_POINT) {
            return selectedRallyPoint;
        }
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

        if (getCurrentMode() == Mode.ARMY) {
            return armySubModes[cycleTypeIndex].toString();
        }
        else {
            Commandable currentCommandable = getCurrentInstance();
            try {
                return ( (Entity) currentCommandable).getEntityType().toString();
            }
            catch (ClassCastException | NullPointerException e ) {
                return null;
            }
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
    public Mode getCurrentMode() {
        return modes[cycleModeIndex];
    }

    public void applyDamageToEntitiesOnLocation(Location location, int damage) {
        List<FighterUnit> unitsToDamage = new ArrayList<>();
        List<Structure> structuresToDamage = new ArrayList<>();

        for (List<Entity> list : unitList) {
            for (Entity entity : list) {
                //TODO this violates TDA uggggggggggh
                if (entity.getLocation().equals(location)) {
                    //We ONLY care about fighter units
                    try {
                        unitsToDamage.add((FighterUnit) entity);
                    }
                    catch (ClassCastException e) {
                        //do nothing, we don't damage units w/o health
                    }
                }
            }
        }

        for (List<Entity> list : structureList) {
            for (Entity entity : list) {
                if (entity.getLocation().equals(location)) {
                    structuresToDamage.add((Structure) entity);
                }
            }
        }

        int damageToApply = damage/(unitsToDamage.size() + structuresToDamage.size());
        for (FighterUnit unitTakingDamage : unitsToDamage) {
            unitTakingDamage.takeDamage(damageToApply);
        }
        for (Structure structureTakingDamage : structuresToDamage) {
            structureTakingDamage.takeDamage(damageToApply);
        }

    }

    public Commandable getEntity(EntityId commandableId) {
        System.out.println("Getting entity based on id");
         for (List<Entity> list : unitList) {
             for (Entity entity : list) {
                 System.out.println(entity.toString());
                 if (commandableId.equals(entity.getEntityId())) {
                     return entity;
                 }
             }
         }
         System.out.println("no entity found based on id");
         return null;
    }

    public void addExistingFighterUnitToArmy(FighterUnit fighterUnit, int armyNumber) {
        selectedArmyIndex = armyNumber;
        armyList.get(selectedArmyIndex).registerUnit(fighterUnit);
    }

    public void addRallyPoint(RallyPoint rallyPoint, int armyNumber) {
        selectedArmyIndex = armyNumber;
        System.out.println("adding rally point for army number " + selectedArmyIndex);
        System.out.println(rallyPointList);
        rallyPointList.remove(selectedArmyIndex); //rally point list will have a dummy rally point while army has no units
        rallyPointList.add(selectedArmyIndex, rallyPoint);
        System.out.println(rallyPointList);
    }
}
