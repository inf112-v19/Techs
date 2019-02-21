package inf112.skeleton.app;

public interface IPlayer {

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
    void setHealth(int health);

    /**
     * A method that gets the weight of the player. The weight is used to determine the priority of the
     * elements on each tile.
     * @return
     */
    int getWeight();

    /**
     * A method that sets the weight of the player, if it is wished.
     * @param weight
     */
    void setWeight(int weight);

    /**
     * A method to get the
     * @return
     */
    int getPlayerIndexOnGrid();

    /**
     * A method to set the index on grid in which the player is on. Must be updated as the player moves.
     * @param indexOnGrid
     */
    void setPlayerIndexOnGrid(int indexOnGrid);
}
