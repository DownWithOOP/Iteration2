package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2/17/2017.
 */
public class GameModel {

    private Player[] players;
    private int activePlayerIndex; // this is the player whose current turn it is

    public GameModel(int numberOfPlayers){
        // TODO check for valid number of players
        // for the moment, lets create 2 players
        players = new Player[numberOfPlayers];
        for(int i=0; i<numberOfPlayers; i++){
            Player temp = new Player();
            players[i] = temp;
        }

        // when game starts, player 1 is starting
        this.activePlayerIndex = 0;
    }

    public void endTurn(){
     activePlayerIndex=next(activePlayerIndex,this.players.length);
    }
    private int next(int index,int size){
        index= (index + 1) % size;
        return index;
    }


}
