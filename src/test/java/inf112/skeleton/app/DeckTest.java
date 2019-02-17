package inf112.skeleton.app;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

}
