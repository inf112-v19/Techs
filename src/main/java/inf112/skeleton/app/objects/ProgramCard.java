package inf112.skeleton.app.objects;

import inf112.skeleton.app.logic.CardType;
import inf112.skeleton.app.logic.Direction;

import java.util.ArrayList;

public class ProgramCard implements IProgramCard {

	CardType cardType;
	int priority;
	boolean locked;
	Direction direction;

	public ProgramCard(CardType cardType, int priority, Direction direction) {
		this.cardType = cardType;
		this.priority = priority;
		this.locked = false;
		this.direction = direction;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

	@Override
	public ArrayList<Direction> getDirection() {
		return null;
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
