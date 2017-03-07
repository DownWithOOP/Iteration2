package model;

import model.map.Map;
import utilities.ObserverInterfaces.MapObserver;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

/**
 * Created by Konrad on 2/17/2017.
 */
public class GameModel {

    private Player[] playersList;
    private int activePlayerIndex; // this is the player whose current turn it is
    private Map masterMap; // the map that will have the global map, each player will have their own map as well

    public GameModel(int numberOfPlayers, MapObserver observer, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver) {

        playersList = new Player[numberOfPlayers];
        // we create the master map
        this.masterMap = initializeMap();
        // right now just for 2 players, we give them the starting locations of their units
        // current map is 24 wide(x), and 12 high (y)
        // first player will start at  (6,4)
        // second player will start at (17,7)

        for (int i = 0; i < numberOfPlayers; i++) {
            Player temp;
            if(i ==0){ // player 1
                temp = new Player(masterMap,observer, unitObserver, structureObserver, statusObserver, 6,4); // TODO, give players unique maps
            } else if( i== 1){
                temp = new Player(masterMap,observer, unitObserver, structureObserver, statusObserver, 17,7); // TODO, give players unique maps
            } else {
                temp = new Player(masterMap,observer, unitObserver, structureObserver, statusObserver, 0,0); // TODO give players unique maps
            }
            playersList[i] = temp;
        }
        // when game starts, player 1 is starting
        this.activePlayerIndex = 0;
        System.out.println("-------------------------------------------");
        playersList[activePlayerIndex].startTurn();
    }

    // will be used to initialize the map at the start of of the game
    private Map initializeMap(){
        Map map = new Map();
        return map;
    }

    public void endTurn() {
        playersList[activePlayerIndex].endTurn();
        activePlayerIndex = nextPlayerIndex(activePlayerIndex);
        playersList[activePlayerIndex].startTurn();
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
}

