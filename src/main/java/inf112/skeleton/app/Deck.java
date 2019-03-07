package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	ArrayList<ProgramCard> deck;
	
	public Deck() {
		this.deck = new ArrayList<>();
		makeDeck();
		shuffle();
	}
	
	/**
	 * Fills the deck
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
	 * shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(deck); 
	}
	
	
	public void resetDeck() {

	}
	
	public ProgramCard getTopCard() {
		return deck.remove(0);
	}
	
	
	/**
	 * Create new ProgramCards
	 * @param type
	 * @param priority
	 * @return
	 */
	public ProgramCard createCard(CardType type, int priority) {
		return new ProgramCard(type, priority);
	}
	
	public boolean deckIsEmpty() {
		return this.deck.isEmpty();
	}
	
	public int deckSize() {
		return this.deck.size();
	}
	
	
	
	
}
