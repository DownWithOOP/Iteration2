package model;

import controller.CommandRelay;
import controller.Observers.PlayerObservator;
import controller.availablecommands.Commandable;
import controller.commands.Direction;
import model.common.Location;
import model.entities.Entity;
import model.entities.EntityId;
import model.entities.structure.Structure;
import model.entities.unit.Army;
import model.entities.unit.FighterUnit;
import model.entities.unit.Unit;
import model.map.Map;
import model.player.Player;
import utilities.ObserverInterfaces.MapObserver;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;
import utilities.id.CustomID;

import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class GameModel {

    private Player[] playersList;
    private java.util.Map<CustomID, Player> playersMap = new HashMap<>();
    private int activePlayerIndex; // this is the player whose current turn it is
    private Map masterMap; // the map that will have the global map, each player will have their own map as well

    public GameModel(int numberOfPlayers, MapObserver observer, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver, PlayerObservator playerObservator) {

        playersList = new Player[numberOfPlayers];
        // we create the master map
        this.masterMap = initializeMap();
        // right now just for 2 players, we give them the starting locations of their units
        // current map is 36 wide(x), and 16 high (y)
        // first player will start at  (6,4)
        // second player will start at (24,10)

        for (int i = 0; i < numberOfPlayers; i++) {
            Player temp;
            if(i ==0){ // player 1
                temp = new Player(1,masterMap, new CommandRelay(this), observer, unitObserver, structureObserver, statusObserver, playerObservator, 6,4); // TODO, give players unique maps
            } else if( i== 1){
                temp = new Player(2,masterMap, new CommandRelay(this), observer, unitObserver, structureObserver, statusObserver, playerObservator, 6, 3); // TODO, give players unique maps
            } else {
                temp = new Player(i+1,masterMap, new CommandRelay(this), observer, unitObserver, structureObserver, statusObserver, playerObservator, 0,0); // TODO give players unique maps
            }
            playersList[i] = temp;
            playersMap.put(temp.getCustomID(), temp);
        }
        // when game starts, player 1 is starting
        this.activePlayerIndex = 0;
        playersList[activePlayerIndex].startTurn();
    }

    // will be used to initialize the map at the start of of the game
    private Map initializeMap(){
        Map map = new Map();
        return map;
    }

    public boolean endTurn() {
        playersList[activePlayerIndex].endTurn();
        activePlayerIndex = nextPlayerIndex(activePlayerIndex);
        playersList[activePlayerIndex].startTurn();
        return true;
    }

    private int nextPlayerIndex(int index) {
        index = (index + 1) % this.playersList.length;
        return index;
    }

    public Player getActivePlayer() {
        return playersList[activePlayerIndex];
    }

    public int getActivePlayerIndex() {
        return activePlayerIndex;
    }

    public void applyDamageToEntitiesByLocation(Location location, int damage) {
        CustomID playerBeingAttackedId = masterMap.getPlayerOnTile(location);
        System.out.println(playerBeingAttackedId + " is player being attacked");
        if (playersMap.containsKey(playerBeingAttackedId)) {
            Player playerBeingAttacked = playersMap.get(playerBeingAttackedId);
            if (playerBeingAttacked.getCustomID().equals(getActivePlayer().getCustomID())) {
                return; //don't attack yourself
            }
            else {
                System.out.println(playerBeingAttackedId + " is actually being attacked");
                playersMap.get(playerBeingAttackedId).applyDamageToEntitiesOnLocation(location, damage);
            }
        }
        else {
            //TODO whaaaaaaaaaaaaaaaaaaaaa?
            //nothing to attack you idiot
        }
    }

    public Commandable getEntity(EntityId commandableId) {
        return getActivePlayer().getEntity(commandableId);
    }

    public void addFighterUnitToArmy(FighterUnit fighterUnit, int armyNumber) {
        getActivePlayer().addFighterUnitToArmy(fighterUnit, armyNumber);
    }

    public void addRallyPoint(RallyPoint rallyPoint, int armyNumber) {
        getActivePlayer().addRallyPoint(rallyPoint, armyNumber);
    }

    public void addUnit(Unit unit) {
        getActivePlayer().addUnit(unit);
    }

    public void removeUnit(Unit unit) {
        getActivePlayer().removeUnit(unit);
    }

    public void addStructure(Structure structure) {
        getActivePlayer().addStructure(structure);
    }

    public void removeStructure(Structure structure) {
        getActivePlayer().removeStructure(structure);
    }

    public void updateTilePlayerId(CustomID playerId, Location location) {
        masterMap.setTilePlayerId(playerId, location);
    }

    public void addWorkersToArmy(Location location, EntityId armyId) {
        getActivePlayer().addWorkersToArmy(location, armyId);
    }
}

