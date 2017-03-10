package model.entities;

import controller.availablecommands.Commandable;
import controller.commands.Command;
import controller.commands.CommandType;
import model.common.Location;
import model.entities.Stats.Stats;
import utilities.id.CustomID;
import utilities.id.IdType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by jordi on 2/24/2017.
 */
public abstract class Entity extends Commandable {
    protected EntityId entityId;
    protected CustomID playerId;
    Queue<Command> commandQueue = new ArrayDeque<>();
    private boolean isPoweredDown;
    private Location location;
    protected Stats entityStats;

    static ArrayList<CommandType> entityCommand = new ArrayList<>();

    static {
        entityCommand.add(CommandType.CANCEL_QUEUE);
        entityCommand.add(CommandType.DECOMISSION);
        entityCommand.add(CommandType.POWER_DOWN);
        entityCommand.add(CommandType.POWER_UP);
    }

    //  TODO:TAKE IN THE MOCK UP MAP, TAKE IN THE PLAYER ID

    /**
     * @param playerId
     */
    public Entity(CustomID playerId, String id, int locationX, int locationY) {
        setId(playerId, id);
        entityStats = setEntityStats();
        this.playerId = playerId;
        this.isPoweredDown = false;
        location = new Location(locationX,locationY); // starting location of entity
        addAllCommands(entityCommand);
    }

    private void setId(CustomID playerId, String id) {
        IdType idType = getIdType();
        entityId = new EntityId(idType, playerId, id);
    }

    protected abstract IdType getIdType();

    protected abstract Stats setEntityStats();

    public IdType getEntityType() {
        return this.entityId.getIdType();
    }

    public Location getLocation() {
        return location;
    }

    public abstract void decommission();

    public void addToQueue() {

    }

    public void cancelQueue() {

    }

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
}
