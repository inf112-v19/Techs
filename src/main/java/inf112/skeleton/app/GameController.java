package inf112.skeleton.app;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {

    private ArrayList<ArrayList<IProgramCard>> programCards;
    //player 1 har pos 0 og så videre
    private ArrayList<PlayerToken> playerTokens;
    private int numPlayers;
    private RoboRally roboRally;
    private BoardCards boardCards;

    private PlayerToken player1;
    private PlayerToken player2;

    private HashMap<Integer, ArrayList<IProgramCard>> playersCards;

    public GameController(int numPlayers, RoboRally roboRally){
        this.numPlayers = numPlayers;
        this.roboRally = roboRally;
        boardCards = new BoardCards(roboRally);
        turn(0);
    }

    public void createNewPlayer(){
        //boardCards.createNewPlayer();
    }

    public void turn(int playerTurn){

    }

    public Screen getScreen(){
        return boardCards;
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
