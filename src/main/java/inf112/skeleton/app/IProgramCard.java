package inf112.skeleton.app;

public interface IProgramCard {
	
	
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
	
	String toString();
	
	/**
	 * Må vel laga ein boolean metode elns som seier om eit kort er låst eller ikkje seinare?
	 */
	
	

}
