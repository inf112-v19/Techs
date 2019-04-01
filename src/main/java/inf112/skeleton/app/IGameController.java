package inf112.skeleton.app;

import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;

import java.util.ArrayList;

public interface IGameController {

    /**
     * The use of this method is intended for BoardCards
     * When a player is done picking cards BoardCards send the cards to GameController that saves them
     * @param cardsCurrentPlayer ProgramCards to player
     * @param boardCards The screen for the board and cards
     */
    void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer, BoardCards boardCards);

    /**
     *This method pics the card with highest priority and sends it to movePlayer
     * @param boardCards The screen for the board and cards
     */
    void movePlayers(BoardCards boardCards);

    /**
     * This method takes the information from programCard and
     * tells BoardLogic where to move the player.
     * @param programCard
     * @param currentPlayer the owner of the programCard
     * @param boardCards Screen
     */
    void movePlayer(IProgramCard programCard, int currentPlayer, BoardCards boardCards);

    /**
     * Intended for testing
     * @param player 0 to (numberOf players in the game - 1).
     *               0 is starting player. 1 is player after starting player and so on.
     * @return programCards to to the player
     */
    ArrayList<IProgramCard> getProgramCardToPlayer(int player);

    /**
     *
     * @return total numbers of turns. A turn is completed when a player has picked 5 cards.
     */
    int getTurns();

    void moveOnePlayer(BoardCards boardCards);

}