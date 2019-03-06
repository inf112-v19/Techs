package inf112.skeleton.app;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProgramCardDeckTest {
	
	private ProgramCardDeck deck = new ProgramCardDeck();
	
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
		ProgramCardDeck deck2 = new ProgramCardDeck();
		assertFalse(deck.equals(deck2));
	}
	
	
	@Test
	public void checkResetDeck() {
		ProgramCardDeck deck = new ProgramCardDeck();
		ProgramCardDeck deck1 = deck;
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
