package inf112.skeleton.app;

public class ProgramCard implements IProgramCard {

	CardType cardType;
	int priority;

	public ProgramCard(CardType cardType, int priority) {
		this.cardType = cardType;
		this.priority = priority;
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

}
