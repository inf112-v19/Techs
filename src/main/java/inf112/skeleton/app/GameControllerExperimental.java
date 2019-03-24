package inf112.skeleton.app;

import com.badlogic.gdx.Screen;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;
import java.util.HashMap;

public class GameControllerExperimental {
    private ArrayList<ArrayList<IProgramCard>> programCards;
    //player 1 har pos 0 og så videre
    private ArrayList<PlayerToken> playerTokens;
    private int numPlayers;
    private int turns;
    private HashMap<Integer, ArrayList<IProgramCard>> playersCards;

    public GameControllerExperimental(int numPlayers, RoboRally roboRally){
        this.turns = 0;
        this.numPlayers = numPlayers;
        playersCards = new HashMap<>();
        turn(0);
    }

    public void startPickingCards(){
        for (int i = 0; i < numPlayers; i++) {
            //boardCards.newTurn();
            //donePickingCards(boardCards.);
        }
    }

    public void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer){
        System.out.println(cardsCurrentPlayer.toString());
        int currentPlayer = turns % numPlayers;
        playersCards.put(currentPlayer, cardsCurrentPlayer);
        turns++;
        turn(turns % numPlayers);
    }

    public void createNewPlayer(){
        //boardCards.createNewPlayer();
    }

    public void turn(int playerTurn){

    }

}
