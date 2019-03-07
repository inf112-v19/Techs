package inf112.skeleton.app;

public class ProgramCard implements IProgramCard {

	CardType cardType;
	int priority;
	boolean locked;

	public ProgramCard(CardType cardType, int priority) {
		cardType = this.cardType;
		priority = this.priority;
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
		return cardType.toString() + priority;
	}
	
	public void setLocked() {
		locked = true;
	}
	
	public void setUnlocked() {
		locked = false;
	}

}
