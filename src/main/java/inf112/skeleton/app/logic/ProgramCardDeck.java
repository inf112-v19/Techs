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
	
	public void clearDeck() {
		deck.clear();
	}
	
	
	/**
	 * Skal lage en ny deck kort og sletta den gammle
	 */
	public void resetDeck() {
		clearDeck();
		makeDeck();
		shuffle();
		
	}
	
	public IProgramCard getTopCard() {
		if(deck.isEmpty()) return null;
		IProgramCard topcard = deck.get(deck.size()-1);
		deck.remove(topcard);
		return topcard;
		
	}
	

	
	
	/**
	 * Create new ProgramCards
	 * @param type
	 * @param priority
	 * @return
	 */
	public IProgramCard createCard(CardType type, int priority) {
		return new ProgramCard(type, priority);
	}
	
	public boolean deckIsEmpty() {
		return this.deck.isEmpty();
	}
	
	public int deckSize() {
		return this.deck.size();
	}
	
	public void printDeck() {
		System.out.println(deck.size());
        for (IProgramCard card : deck) {
            System.out.println(card.toString());
        }
	}
	
	/**
	 * metode for å dele ut kort til en spiller
	 * foreløpig står playeerHealth alltid til 10 før me får ein metode for den
	 * @param player
	 * @return
	 */
	public ArrayList<IProgramCard> dealToPlayer() {
			int playerHealth = 10;
			ArrayList<IProgramCard> playerHand = new ArrayList<>();
			for(int i = 0; i < playerHealth - 1; i++) {
				if(deck.isEmpty()) {
	                System.out.println("Deck is empty... Playerhand currently got " + i + " cards.");
	                return playerHand;
				} else {
					playerHand.add(getTopCard());				
				}
	}
			return playerHand;
	}
	
	
	 
	
}
