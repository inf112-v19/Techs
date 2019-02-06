package inf112.skeleton.app;

public interface IPlayer extends IItem {
    
    /**
     * A method that returns the health of the robot player. It is used when checking the
     * number of program cards you are dealt and the number of locked registers in each round.
     * @return
     */
    int getHealth();

    /**
     * A method that sets the health of the robot player. It is used at the start of a game or if an
     * option card gives more health (Option card is not on the plan yet)
     */
    void setHealth();
}
