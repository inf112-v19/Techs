package inf112.skeleton.app;

import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {
    private ArrayList<ArrayList<IProgramCard>> programCards;
    //player 1 har pos 0 og så videre
    private ArrayList<PlayerToken> playerTokens;
    private int numPlayers;
    private int turns;
    private HashMap<Integer, ArrayList<IProgramCard>> playersCards;

    public GameController(int numPlayers){
        this.turns = 0;
        this.numPlayers = numPlayers;
        playersCards = new HashMap<>();
    }

    public void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer, BoardCards boardCards){
        int currentPlayer = turns % numPlayers;
        playersCards.put(currentPlayer, cardsCurrentPlayer);
        turns++;
        turn(turns % numPlayers, boardCards);
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
        }
    }
    
    private void doTurn(IProgramCard programCard, int currentPlayer, BoardCards boardCards) {
        if (programCard.getDirection() != 0) {
            boardCards.getBoardLogic().getPlayersList().get(currentPlayer).rotatePlayer(programCard.getDirection());
        }
        else
            for (int i = 0; i < programCard.getMovement(); i++)
                boardCards.getBoardLogic().getPlayersList().get(currentPlayer).moveInFacingDirection();
    }

}
