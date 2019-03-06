package inf112.skeleton.app;

public enum CardType {
	
	ROTATE_LEFT,
	ROTATE_RIGHT,
	ROTATE_TURN,
	MOVEMENT_1,
	MOVEMENT_2,
	MOVEMENT_3,
	MOVEMENT_BACK;
	
	
	public String toString() {
		switch(this) {
		case ROTATE_LEFT: return "Turn left";
		case ROTATE_RIGHT: return "Turn right";
		case ROTATE_TURN: return "Turn 180 degrees";
		case MOVEMENT_1: return "Move 1 step forward";
		case MOVEMENT_2: return "Move 2 steps forward";
		case MOVEMENT_3: return "Move 3 step forward";
		case MOVEMENT_BACK: return "Move 1 step back";
		}
		throw new IllegalArgumentException("There exists no such type of card"); //Er detta riktig type exeption?
	}
}
