package inf112.skeleton.app;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {
	
	private Deck deck = new Deck();
	
	@Test
	public void checkIfDeckIsEmpty() { 
		assertFalse(deck.deckIsEmpty());
	}
	
	
	@Test
	public void checkDeckSize() {
		//deck = new Deck();
		assertEquals(deck.deckSize(), 84);
	}
	
	
	@Test
	public void checkIfShuffled() {
		Deck deck2 = new Deck();
		assertFalse(deck.equals(deck2));
	}
	
	
	@Test
	public void checkResetDeck() {
		Deck deck1 = deck;
		deck.resetDeck();
		assertFalse(deck.equals(deck1)); 
	}
	
	
	@Test
	public void checkDeckDecreaseAfterGettingTopCard() {
		int previousDeckSize = deck.deckSize();
		deck.getTopCard();
		assertEquals(deck.deckSize(), previousDeckSize - 1);
	}
	

}
