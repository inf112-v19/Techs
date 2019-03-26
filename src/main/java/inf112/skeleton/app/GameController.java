package inf112.skeleton.app;

import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController implements IGameController{
    private ArrayList<ArrayList<IProgramCard>> programCards;
    //player 1 has index 0 and so on.
    private ArrayList<PlayerToken> playerTokens;
    private int numPlayers;
    //total number of turns
    private int turns;
    private HashMap<Integer, ArrayList<IProgramCard>> playersCards;

    public GameController(int numPlayers){
        this.turns = 0;
        this.numPlayers = numPlayers;
        playersCards = new HashMap<>();
    }

    @Override
    public void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer, BoardCards boardCards){
        int currentPlayer = turns % numPlayers;
        playersCards.put(currentPlayer, cardsCurrentPlayer);
        turns++;
        if (turns % numPlayers == 0)
            movePlayers(boardCards);
    }

    @Override
    public void movePlayers(BoardCards boardCards){
        ArrayList<IProgramCard> cards1 = playersCards.get(0);
        ArrayList<IProgramCard> cards2 = playersCards.get(1);

        for (int i = 0; i < 5; i++) {
            if (cards1.get(0).getPriority() > cards2.get(0).getPriority()) {
                movePlayer(cards1.remove(0), 0, boardCards);
                movePlayer(cards2.remove(0), 1, boardCards);
            }
            else {
                movePlayer(cards2.remove(0), 1, boardCards);
                movePlayer(cards1.remove(0), 0, boardCards);
            }
        }
    }

    @Override
    public void movePlayer(IProgramCard programCard, int currentPlayer, BoardCards boardCards) {
        if (programCard.getDirection() != 0) {
            boardCards.getBoardLogic().getPlayersList().get(currentPlayer).rotatePlayer(programCard.getDirection());
        }
        else
            for (int i = 0; i < programCard.getMovement(); i++)
                boardCards.getBoardLogic().getPlayersList().get(currentPlayer).moveInFacingDirection();
    }

    @Override
    public ArrayList<IProgramCard> getProgramCardToPlayer(int player) {
        if (player >= playersCards.keySet().size() || player < 0){
            throw new IllegalArgumentException("Player does not exist");
        }
        return playersCards.get(player);
    }

    @Override
    public int getTurns() {
        return turns;
    }

}
