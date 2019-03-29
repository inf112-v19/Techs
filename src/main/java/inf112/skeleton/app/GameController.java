package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameController implements IGameController{
    private int numPlayers;
    //total number of turns
    private int turns;
    //Integer = player, ArrayList<IProgramCard> cards to player. Key 0 is starting player, Key 1 is player
    //after starting player
    private HashMap<Integer, ArrayList<IProgramCard>> playersCards;
    private HashMap<Integer, String> playerString;

    public GameController(int numPlayers, BoardCards boardCards){
        this.turns = 0;
        this.numPlayers = numPlayers;
        playersCards = new HashMap<>();
        playerString = new HashMap<>();
        boardCards.addPlayerToBoard(new Vector2(1,1), "playerOne");
        playerString.put(0, "playerOne");
        boardCards.addPlayerToBoard(new Vector2(2,2), "playerTwo");
        playerString.put(1, "playerTwo");
    }

    @Override
    public void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer, BoardCards boardCards){
        int currentPlayer = turns % numPlayers;
        playersCards.put(currentPlayer, cardsCurrentPlayer);
        turns++;
        if (turns % numPlayers == 0)
            boardCards.setAllPlayersDonePickingCards(true);
    }

    @Override
    public void movePlayers(BoardCards boardCards){
        //makes it only possible to move player if he has cards on hand
        if (playersCards.get(0).isEmpty()){
            boardCards.setAllPlayersDonePickingCards(false);
            return;
        }

        HashMap<Integer, IProgramCard> firstCards = new HashMap<>();
        HashMap<IProgramCard, Integer> firstCardsInverse = new HashMap<>();
        IProgramCard PriorityCard;

        for (int currentPlayer = 0; currentPlayer < numPlayers; currentPlayer++) {
            firstCardsInverse.put(playersCards.get(currentPlayer).get(0), currentPlayer);
            firstCards.put(currentPlayer, playersCards.get(currentPlayer).remove(0));
        }

        while (!firstCards.isEmpty()){
            PriorityCard = Collections.min(firstCards.values());
            firstCards.remove(firstCardsInverse.get(PriorityCard));
            movePlayer(PriorityCard, firstCardsInverse.get(PriorityCard), boardCards);
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
                //boardCards.movePlayerForward(playerString.get(i));
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
