package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.IItem;

/**
 * the same laser spans over multiple tiles
 */
public interface IBoardLaser extends IItem {

    /**
     *
     * @return how far the laser shoots
     */
    int getDistance();

    void setDistance(int distance);

    /**
     *
     * @return direction of the laser
     */
    Direction getDirection();

    void setDirection(Direction direction);
}
