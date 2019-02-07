package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.IItem;

public interface IConveyorBelt extends IItem {

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
