package model;

/**
 * Created by Konrad on 2/17/2017.
 */
public class GameModel {

    private Player[] playersList;
    private int activePlayerIndex; // this is the player whose current turn it is

    public GameModel(int numberOfPlayers) {
        // TODO check for valid number of playersList
        // for the moment, lets create 2 playersList
        playersList = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            Player temp = new Player();
            playersList[i] = temp;
        }

        // when game starts, player 1 is starting
        this.activePlayerIndex = 0;
        playersList[activePlayerIndex].startTurn();
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
}
