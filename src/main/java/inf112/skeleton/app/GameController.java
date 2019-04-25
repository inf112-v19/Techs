package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


//i høyre hjørne vises det liv/herter til hver av spillerne som spiller sidelegst
//tydelig at en spiller har valgt kort
//det skal være mulig å gå tilbake når du har valgt kort

public class GameController implements IGameController{
    private int numPlayers;
    private int turns;
    // Keeps track of how many times a player has moved (used to detect when endOfTurn should run)
    private int movesDone = 0;  
    private HashMap<Integer, ArrayList<IProgramCard>> playersCards;
    private HashMap<Integer, String> playerString;
    private HashMap<Integer, IProgramCard> firstCards;
    private HashMap<IProgramCard, Integer> firstCardsInverse;

    private ArrayList<Vector2> startPosition = new ArrayList<>();
    
    private ArrayList<ArrayList<IProgramCard>> cardsPlayedInRegister = new ArrayList<>();
    
    public GameController(int numPlayers, BoardCards boardCards){
        this.turns = 0;
        this.numPlayers = numPlayers;
        playersCards = new HashMap<>();
        playerString = new HashMap<>();

        setStartPosition();

        for (int i = 0; i < numPlayers; i++) {
            String playerName = "player " + (i+1);
            boardCards.addPlayerToBoard(startPosition.get(i), playerName);
            playerString.put(i, playerName);
            
            cardsPlayedInRegister.add(new ArrayList<IProgramCard>());
        }

    }

    @Override
    public void setStartPosition(){
        startPosition.add(new Vector2(6,1));
        startPosition.add(new Vector2(7,1));
        startPosition.add(new Vector2(4,2));
        startPosition.add(new Vector2(9,2));
        startPosition.add(new Vector2(2,3));
        startPosition.add(new Vector2(10,3));
        startPosition.add(new Vector2(1,4));
        startPosition.add(new Vector2(12,4));
    }

    // This runs when the player has chosen 5 cards and presses ENTER
    @Override
    public void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer, BoardCards boardCards){
        playersCards.put(getNumberOfCurrentPlayer(), cardsCurrentPlayer);
        turns++;
        // Checks if all players have chosen their cards
        if (turns % numPlayers == 0)
            boardCards.setAllPlayersDonePickingCards(true);
    }

    @Override
    public void movePlayers(BoardCards boardCards){
        //makes it only possible to move player if he has cards on hand
        if (firstCards == null){
            firstCards = new HashMap<>();
            firstCardsInverse = new HashMap<>();
        }

        if (firstCards.isEmpty()) {
            for (int currentPlayer = 0; currentPlayer < numPlayers; currentPlayer++) {
                firstCardsInverse.put(playersCards.get(currentPlayer).get(0), currentPlayer);
                firstCards.put(currentPlayer, playersCards.get(currentPlayer).remove(0));
            }
        }

        moveOnePlayer(boardCards);
        
        movesDone++;
        if(movesDone % numPlayers == 0) {
            System.out.println("Do end of turn");
            boardCards.processEndOfTurns();
        }
        
        
        if (playersCards.get(0).isEmpty() && firstCards.isEmpty()) {
            boardCards.setAllPlayersDonePickingCards(false);
            movesDone = 0;
        }

    }

    @Override
    public void moveOnePlayer(BoardCards boardCards){
        IProgramCard priorityCard = Collections.max(firstCards.values());
        firstCards.remove(firstCardsInverse.get(priorityCard));
        movePlayer(priorityCard, firstCardsInverse.get(priorityCard), boardCards);
    }

    @Override
    public void movePlayer(IProgramCard programCard, int currentPlayer, BoardCards boardCards) {
        if (programCard.getDirection() != 0) {
            //boardCards.getBoardLogic().getPlayersList().get(currentPlayer).rotatePlayer(programCard.getDirection());
            boardCards.rotatePlayer(playerString.get(currentPlayer), programCard.getDirection());
        }
        else if (programCard.getMovement() == -1) {
            boardCards.movePlayerBackwards(playerString.get(currentPlayer));
        }
        else {
            for (int i = 0; i < programCard.getMovement(); i++) {
                //boardCards.getBoardLogic().getPlayersList().get(currentPlayer).moveInFacingDirection();
                boardCards.movePlayerForward(playerString.get(currentPlayer));
                //boardCards.movePlayerForward(playerString.get(i));
            }
        }
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
    
    public int getNumberOfCurrentPlayer() {
        return turns % numPlayers;
    }
    
    public String getCurrentPlayerByName() {
    	return playerString.get(getNumberOfCurrentPlayer());
    }
    
    public void setCardsThatWerePlayedInRegister(ArrayList<IProgramCard> cards) {
        cardsPlayedInRegister.get(getNumberOfCurrentPlayer()).clear();
        for(IProgramCard card : cards) {
            cardsPlayedInRegister.get(getNumberOfCurrentPlayer()).add(card);
        }    
    }
    
    public ArrayList<IProgramCard> getCardsThatWerePlayedLastTurn() {
        return cardsPlayedInRegister.get(getNumberOfCurrentPlayer());
    }
}
