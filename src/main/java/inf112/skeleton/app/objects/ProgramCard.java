package inf112.skeleton.app.objects;

import inf112.skeleton.app.logic.CardType;
import inf112.skeleton.app.logic.Direction;

import java.util.ArrayList;

public class ProgramCard implements IProgramCard {

	CardType cardType;
	int priority;
	boolean locked;
	int rotations;
	int movement;

	public ProgramCard(CardType cardType, int priority, int rotations, int movement) {
		this.cardType = cardType;
		this.priority = priority;
		this.locked = false;
		this.rotations = rotations;
		this.movement = movement;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

	@Override
	public int getDirection() {
		return this.rotations;
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
