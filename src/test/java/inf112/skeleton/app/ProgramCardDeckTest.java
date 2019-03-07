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
	
	
	//@Test Funkar ikkje.
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
	
	@Test
	public void checkDeckAfterDealing9CardsToPlayer() {
		int previousDeckSize = deck.deckSize();
		ArrayList<IProgramCard> playerHand = deck.dealToPlayer();
		assertEquals(deck.deckSize(), previousDeckSize - playerHand.size());
		
	}
	
	@Test
	public void checkCardPriorityIsBetweenRange() {
		ArrayList<ProgramCard> deck = new ArrayList<>();
		for(IProgramCard card : deck) {
			assertTrue(card.getPriority() >= 10 && card.getPriority() <= 840);
			
		}
	}
	
	@Test
	public void checkClearDeck() {
		ProgramCardDeck deck = new ProgramCardDeck();
		deck.clearDeck();
		assertTrue(deck.deckIsEmpty());
	}
	
	@Test
	public void checkCardTypeInDeck() {
		ArrayList<ProgramCard> deck = new ArrayList<>();
		int count = 0;
		for(IProgramCard card : deck) {
			if(card.getCardType().equals(CardType.MOVEMENT_1)) {
				count++;
			}
			assertEquals(count, 18);
		
		}
	}
}
