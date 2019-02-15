package inf112.skeleton.app;

public class ProgramCard implements IProgramCard {

	CardType cardType;
	int priority;

	public ProgramCard(CardType cardType, int priority) {
		cardType = this.cardType;
		priority = this.priority;
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
