package inf112.skeleton.app;

import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.objects.IProgramCard;

import java.util.ArrayList;

public interface IGameController {

    /**
     * The use of this method is intended for BoardCards
     * When a player is done picking cards BoardCards send the cards to GameController that saves them
     * @param cardsCurrentPlayer ProgramCards to player
     * @param boardCards The screen for the board
     */
    void donePickingCards(ArrayList<IProgramCard> cardsCurrentPlayer, BoardCards boardCards);

    /**
     *
     * @param playerTurn
     * @param boardCards
     */
    void turn(int playerTurn, BoardCards boardCards);

    void doTurn(IProgramCard programCard, int currentPlayer, BoardCards boardCards);

    ArrayList<IProgramCard> getProgramCardToPlayer(int player);

}