package inf112.skeleton.app.Tiles;

import inf112.skeleton.app.Direction;

/**
 * the same laser spans over multiple tiles
 */
public interface IBoardLaser extends ITile {

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
