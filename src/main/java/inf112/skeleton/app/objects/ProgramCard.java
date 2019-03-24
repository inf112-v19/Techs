package inf112.skeleton.app.objects;

import inf112.skeleton.app.logic.CardType;

public class ProgramCard implements IProgramCard {

	CardType cardType;
	int priority;
	boolean locked;

	public ProgramCard(CardType cardType, int priority) {
		this.cardType = cardType;
		this.priority = priority;
		this.locked = false;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

	@Override
	public CardType getCardType() {
		return this.cardType;
	}
	
	@Override
	public String toString() {
		return priority + " " + cardType.toString();
	}
	
	public void setLocked() {
		locked = true;
	}
	
	public void setUnlocked() {
		locked = false;
	}

}
