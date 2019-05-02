package inf112.skeleton.app.objects;

import inf112.skeleton.app.logic.CardType;

public class ProgramCard implements IProgramCard {

	private CardType cardType;
	private int priority;
	private int rotations;
	private int movement;

	public ProgramCard(CardType cardType, int priority, int rotations, int movement) {
		this.cardType = cardType;
		this.priority = priority;
		this.rotations = rotations;
		this.movement = movement;
	}

	@Override
	public int compareTo(IProgramCard other) {
		if (priority > other.getPriority())
			return 1;
		else if (priority == other.getPriority())
			return 0;
		return -1;
	}

	@Override
	public CardType getCardType() {
		return this.cardType;
	}

	@Override
	public int getDirection() {
		return this.rotations;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

	@Override
	public int getMovement() {
		return movement;
	}

	@Override
	public String toString() {
		return priority + " " + cardType.toString();
	}

}
