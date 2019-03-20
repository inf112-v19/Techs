package inf112.skeleton.app;

import java.util.ArrayList;

public class Game {


    private ArrayList<ArrayList<IProgramCard>> programCards;
    //player 1 har pos 0 og så videre
    private ArrayList<PlayerToken> playerTokens;
    private int numPlayers;
    //private int playerTurn;
    BoardCards boardCards = new BoardCards();

    public Game(int numPlayers){
        this.numPlayers = numPlayers;
        turn(0);
    }

    public void turn(int playerTurn){

    }

    //private
    /*
    trekke programkort
    robots move
        *priority
        * turn
    lasers
    checkpoints
     */
}
