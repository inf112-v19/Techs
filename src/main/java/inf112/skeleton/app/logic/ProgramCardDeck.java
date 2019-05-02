package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.ProgramCard;

import java.util.ArrayList;
import java.util.Collections;

public class ProgramCardDeck {
	ArrayList<IProgramCard> deck;
	
	public ProgramCardDeck() {
		this.deck = new ArrayList<>();
		makeDeck();
		shuffle();
	}

	/**
	 * Clears the deck with program cards.
	 */
	public void clearDeck() {
		deck.clear();
	}

	/**
	 * Creates a new program card of a given CardType
	 * @param type The type of card that is created
	 * @param priority The priority of the program cards
	 * @return The new program card
	 */
	public IProgramCard createCard(CardType type, int priority) {
		return new ProgramCard(type, priority, getNumRotation(type), getNumMovement(type));
	}

	/**
	 * Deals out the correct amount of program cards to a player based on its health.
	 * @return The arraylist of program cards dealt to the player
	 */
	public ArrayList<IProgramCard> dealToPlayer() {
		int playerHealth = 10;
		ArrayList<IProgramCard> playerHand = new ArrayList<>();
		for(int i = 0; i < playerHealth - 1; i++) {
			if(deck.isEmpty()) {
				System.out.println("Deck is empty... Player hand currently got " + i + " cards.");
				return playerHand;
			} else {
				playerHand.add(getTopCard());
			}
		}
		return playerHand;
	}

	/**
	 * Checks if the deck is empty
	 * @return True if deck is empty, otherwise false
	 */
	public boolean deckIsEmpty() {
		return this.deck.isEmpty();
	}

	/**
	 * Gets the size of deck.
	 * @return The number of program cards in the deck
	 */
	public int deckSize() {
		return this.deck.size();
	}

	/**
	 * Gets the number of tiles moved that is given in some of the program cards.
	 * @param cardType The type of card from where ou want the information
	 * @return The number of tiles moved in a given card
	 */
	public int getNumMovement(CardType cardType) {
		if (cardType == CardType.MOVEMENT_1)
			return 1;
		if (cardType == CardType.MOVEMENT_2)
			return 2;
		if (cardType == CardType.MOVEMENT_3)
			return 3;
		if (cardType == CardType.MOVEMENT_BACK)
			return -1;
		return 0;
	}

	/**
	 * Gets a number representing the number of clockwise rotation on board that is given some of the program cards.
	 * @param cardType The type of card from where you want the information
	 * @return The number of clockwise rotations
	 */
	public int getNumRotation(CardType cardType){
		if (cardType == CardType.ROTATE_LEFT)
			return -1;
		if (cardType == CardType.ROTATE_RIGHT)
			return 1;
		if (cardType == CardType.U_TURN)
			return 2;
		return 0;
	}

	/**
	 * Gets the top card of the deck of program cards and decrements the deck size. If decks is empty, a new deck is made.
	 * @return The top card of the deck
	 */
	public IProgramCard getTopCard() {
		if (deck.isEmpty())
			resetDeck();
		return deck.remove(deckSize() - 1);
	}

	/**
	 * Makes the deck in which the program cards gets picked from
	 */
	public void makeDeck() {
			for(int p = 80; p <= 420; p += 20) {
			deck.add(createCard(CardType.ROTATE_RIGHT, p));
			}
		
			for(int p = 70; p <= 410; p += 20) {
				deck.add(createCard(CardType.ROTATE_LEFT, p));
			}
			
			for(int p = 10; p <= 60; p += 10) {
				deck.add(createCard(CardType.U_TURN, p));
			}
			
			for(int p = 490; p <= 660; p += 10) {
				deck.add(createCard(CardType.MOVEMENT_1, p));
			}
			
			for(int p = 670; p <= 780; p += 10) {
				deck.add(createCard(CardType.MOVEMENT_2, p));
			}
			
			for(int p = 790; p <= 840; p += 10) {
				deck.add(createCard(CardType.MOVEMENT_3, p));
			}
			
			for(int p = 430; p <= 480; p += 10) {
				deck.add(createCard(CardType.MOVEMENT_BACK, p));
			}
		
	}

	/**
	 * Clears the deck, makes a new deck and then shuffles the new program cards in the new deck.
	 */
	public void resetDeck() {
		clearDeck();
		makeDeck();
		shuffle();

	}

	/**
	 * Shuffles the deck of program cards
	 */
	public void shuffle() {
		Collections.shuffle(deck); 
	}

}
