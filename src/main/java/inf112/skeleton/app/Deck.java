package inf112.skeleton.app;

import java.util.ArrayList;

public class Deck {
	ArrayList<ProgramCard> deck;
	
	public Deck() {
		this.deck = new ArrayList<>();
		makeDeck();
	}
	
	/**
	 * Fills the deck
	 */
	private void makeDeck() {
		for(int p = 80; p <= 420; p += 20) {
			deck.add(createCard(CardType.ROTATE_RIGHT, p));
		}
		
			for(int p = 70; p <= 410; p += 20) {
				deck.add(createCard(CardType.ROTATE_LEFT, p));
		}
			
			for(int p = 10; p <= 60; p += 10) {
				deck.add(createCard(CardType.ROTATE_TURN, p));
			}
			
			for(int p = 490; p <= 650; p += 10) {
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
	
	
}
