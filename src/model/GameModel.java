package model;

<<<<<<< Updated upstream
import controller.AbstractObserver;
import model.map.Map;
import utilities.Renderer;

||||||| merged common ancestors
=======
import utilities.Renderer;

>>>>>>> Stashed changes
/**
 * Created by Konrad on 2/17/2017.
 */
public class GameModel {

    private Player[] playersList;
    private int activePlayerIndex; // this is the player whose current turn it is
    private Map masterMap; // the map that will have the global map, each player will have their own map as well

    public GameModel(int numberOfPlayers, AbstractObserver observer) {

        playersList = new Player[numberOfPlayers];
        // we create the master map
        this.masterMap = initializeMap();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player temp = new Player(masterMap,observer); // TODO, give players unique maps
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

    public void draw(Renderer r) {
        playersList[activePlayerIndex].draw(r);
    }
}
