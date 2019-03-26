package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameController implements IGameController{
    private ArrayList<ArrayList<IProgramCard>> programCards;
    //player 1 has index 0 and so on.
    private ArrayList<PlayerToken> playerTokens;
    private int numPlayers;
    //total number of turns
    private int turns;
    private HashMap<Integer, ArrayList<IProgramCard>> playersCards;

    public GameController(int numPlayers, BoardCards boardCards){
        this.turns = 0;
        this.numPlayers = numPlayers;
        playersCards = new HashMap<>();
        boardCards.addPlayerToBoard(new Vector2(0,0), "playerOne");
        boardCards.addPlayerToBoard(new Vector2(1,1), "playerTwo");
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

        ArrayList<IProgramCard> firstCard = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int currentPlayer = 0; currentPlayer < numPlayers; currentPlayer++) {
                firstCard.add(playersCards.get(currentPlayer).remove(0));
            }
            while (!firstCard.isEmpty()){
                int minIndex = firstCard.indexOf(Collections.min(firstCard));
                movePlayer(firstCard.remove(minIndex), minIndex, boardCards);
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
