package inf112.skeleton.app;

import com.badlogic.gdx.Screen;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.logic.Direction;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.PlayerToken;
import inf112.skeleton.app.screens.Board;

import java.util.ArrayList;
import java.util.Arrays;
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
        //turn(0);
    }

    public void startPickingCards(){
        for (int i = 0; i < numPlayers; i++) {
            //boardCards.newTurn();
            //donePickingCards(boardCards.);
        }
    }

    public void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer, BoardCards boardCards){
        //System.out.println(cardsCurrentPlayer.toString());
        int currentPlayer = turns % numPlayers;
        playersCards.put(currentPlayer, cardsCurrentPlayer);
        turns++;
        turn(turns % numPlayers, boardCards);
    }

    public void createNewPlayer(){
        //boardCards.createNewPlayer();
    }

    public void turn(int playerTurn, BoardCards boardCards){
        if (playerTurn == 0){
            ArrayList<IProgramCard> cards1 = playersCards.get(0);
            ArrayList<IProgramCard> cards2 = playersCards.get(1);

            System.out.println(cards1.toString());
            System.out.println(cards2.toString());
            for (int i = 0; i < 5; i++) {
                if (cards1.get(0).getPriority() > cards2.get(0).getPriority()) {
                    doTurn(cards1.remove(0), 0, boardCards);
                    doTurn(cards2.remove(0), 1, boardCards);
                }
                else {
                    doTurn(cards2.remove(0), 1, boardCards);
                    doTurn(cards1.remove(0), 0, boardCards);
                }
            }

            //boardCards.getBoardLogic().getPlayersList().get(0).getFacingDirection();
            //boardCards.getBoardLogic().getPlayersList().get(0).rotatePlayer();
            //boardCards.movePlayer("playerOne", )
            //boardCards.movePlayer("playerOne", cards1.get(0).getDirection());
        }
    }
    
    private void doTurn(IProgramCard programCard, int currentPlayer, BoardCards boardCards) {
        System.out.println(programCard.getCardType());
        if (programCard.getDirection() != 0) {
            boardCards.getBoardLogic().getPlayersList().get(currentPlayer).rotatePlayer(programCard.getDirection());
        }
        else
            for (int i = 0; i < programCard.getMovement(); i++)
                boardCards.getBoardLogic().getPlayersList().get(currentPlayer).moveInFacingDirection();
    }

}
