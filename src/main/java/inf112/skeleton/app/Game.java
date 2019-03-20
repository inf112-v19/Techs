package inf112.skeleton.app;

import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;

public class Game {


    private ArrayList<ArrayList<IProgramCard>> programCards;
    private ArrayList<PlayerToken> playerTokens;
    private int numPlayers;
    private int playerTurn;

    public Game(int numPlayers){
        this.numPlayers = numPlayers;
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
