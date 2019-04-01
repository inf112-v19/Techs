package inf112.skeleton.app.objects;

import inf112.skeleton.app.logic.CardType;
import inf112.skeleton.app.logic.Direction;

import java.util.ArrayList;

public interface IProgramCard extends Comparable<IProgramCard> {
	
	
	/**
	 * A method that returns the type of the programcard
	 * @return
	 */
	CardType getCardType();
	
	/**
	 * A method that returns the priority of the programcard
	 * @return
	 */
	int getPriority();

	/**
	 *
	 * @return Rotates player 90 degrees clockwise for each numberOfTimes.
	 * 90 degrees counterclockwise when numberOfTimes is negative.
	 */
	int getDirection();

	/**
	 *
	 * @return number of tiles the player is moved forward by card. -1 is one tile back.
	 */
	int getMovement();

	String toString();
	
	/**
	 * Må vel laga ein boolean metode elns som seier om eit kort er låst eller ikkje seinare?
	 */
	
	

}
