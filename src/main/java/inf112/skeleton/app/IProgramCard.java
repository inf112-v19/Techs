package inf112.skeleton.app;

public interface IProgramCard {
	
	
	/**
	 * A method that returns the programcards value of rotation
	 * @return
	 */
	Rotate getRotation(); 
	
	/**
	 * A method that returns the movement length of the programcard(-1, 0, 1, 2 or 3)
	 * @return
	 */
	int getMovement();
	
	/**
	 * A method that returns the priority of the programcard
	 * @return
	 */
	int getPriority();
	
	

}
