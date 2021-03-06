package model.entities;

import controller.CommandRelay;
import controller.availablecommands.Commandable;
import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.entitycommand.entitycommand.PowerUpCommand;
import controller.commands.entitycommand.unitcommand.AbandonArmyCommand;
import controller.commands.entitycommand.unitcommand.MoveUnitCommand;
import model.common.Location;
import model.entities.Stats.Stats;
import model.entities.unit.Melee;
import model.entities.unit.Unit;
import model.map.tile.resources.Resource;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.*;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Entity extends Commandable {

    protected EntityId entityId;
    protected CustomID playerId;
    //private Queue<Command> commandQueue = new ArrayDeque<>();
    //private Command currentCommand;
    private boolean isPoweredDown;
    private Location location;
    protected Stats entityStats;

    protected static ArrayList<CommandType> entityCommand = new ArrayList<>();

    static {
        entityCommand.add(CommandType.CANCEL_QUEUE);
        entityCommand.add(CommandType.DECOMMISSION);
        entityCommand.add(CommandType.POWER_DOWN);
        entityCommand.add(CommandType.POWER_UP);
    }

    //  TODO:TAKE IN THE MOCK UP MAP, TAKE IN THE PLAYER ID

    /**
     * @param playerId
     */
    public Entity(CommandRelay commandRelay, CustomID playerId, String id, int locationX, int locationY) {
        super(commandRelay);
        setId(playerId, id);
        entityStats = setEntityStats();
        this.playerId = playerId;
        this.isPoweredDown = false;
        location = new Location(locationX,locationY); // starting location of entity
//        entityCommand.add(CommandType.CANCEL_QUEUE);
//        entityCommand.add(CommandType.DECOMMISSION);
//        entityCommand.add(CommandType.POWER_DOWN);
//        entityCommand.add(CommandType.POWER_UP);
        addAllCommands(entityCommand);

    }

    private void setId(CustomID playerId, String id) {
        IdType idType = getIdType();
        entityId = new EntityId(idType, playerId, id);
    }

    protected abstract IdType getIdType();

    protected abstract Stats setEntityStats();

    public Stats getStats() {
        return this.entityStats;
    }

    public IdType getEntityType() {
        return this.entityId.getIdType();
    }

    public Location getLocation() {
        return location;
    }

    protected void setLocation(int x, int y){
        location.setLocation(x, y);
    }

    public abstract void decommission();

//    public void addToQueue(Command command) {
//        commandQueue.add(command);
//    }
//
//    public void cancelQueue() {
//        while(!commandQueue.isEmpty()){
//            commandQueue.poll();
//        }
//    }
//    public void cancelQueue() {
//
//    }
//
//    public void executeQueue(){0
//        if(currentCommand == null){
//            //System.out.println("hell0");
//            if(!commandQueue.isEmpty()){
//                System.out.println("Not yet kiddo");
//                currentCommand = commandQueue.poll();
//            }
//        }
//        if (currentCommand != null) {
//            if(currentCommand.execute()){
//                System.out.println("Command got executed");
//                currentCommand = commandQueue.poll();
//            }
//        }
//    }

    public void powerUp() {
        System.out.println("powerUP");
        if (isPoweredDown()) {
            int originalUpkeep = entityStats.getDefaultUpkeep();
            entityStats.setUpkeep(originalUpkeep);
            setPoweredDown(false);
        }
    }

    public void powerDown() {
        System.out.println("powerDown");
        if (!isPoweredDown()) {
            int upkeep = entityStats.getUpkeep();
            int loweredUpkeep = Math.round((int) (upkeep * .25));
            entityStats.setUpkeep(loweredUpkeep);
            setPoweredDown(true);
        }

    }

    public abstract void receiveResource(Resource resource);

    public abstract void consumeResources();

    public boolean isPoweredDown() {
        return isPoweredDown;
    }

    public void setPoweredDown(boolean isPoweredDown) {
        this.isPoweredDown = isPoweredDown;
    }

    public EntityId getEntityId(){
        return entityId;
    }
    public CustomID getPlayerId(){
        return playerId;
    }

    // Retrieve the currently selected command the specific entity type can perform
    public CommandType getEntityCommand(int cycleCommandIndex) {
        return entityCommand.get(cycleCommandIndex);
    }

    public ArrayList<CommandType> getEntityCommands() {
        return entityCommand;
    }

//    public Command getCurrentCommand() {
//        return currentCommand;
//    }

    @Override
    public String toString() { return entityId.toString();}

    public static void main(String[] args){


        //Entity e = new Melee(new CustomID(IdType.MELEE, "id"), "id1", 0, 0);

//        Command command = new MoveUnitCommand((Unit) e, 1, 0);
//        e.addToQueue(command);
//
//        e.executeQueue();
//        e.executeQueue();
//        e.getLocation();
    }

}
