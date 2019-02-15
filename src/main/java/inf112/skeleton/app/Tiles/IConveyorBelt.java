package inf112.skeleton.app.Tiles;

import inf112.skeleton.app.Direction;

public interface IConveyorBelt extends ITile {

    /**
     *
     * @return how many tiles the player on the conveyor belt moves
     */
    int getSpeed();

    void setSpeed(int speed);

    /**
     *
     * @return direction of conveyor belt
     */
    Direction getDirection();

    void setDirection(Direction direction);
}
