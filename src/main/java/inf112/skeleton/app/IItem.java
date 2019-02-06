package inf112.skeleton.app;

public interface IItem {

    /**
     * the name of an Item might be as simple as "checkpoint"
     *
     * @return name of item type
     */
    String getName();

    /**
     *
     * @return symbol to item
     * this might for the time being be a simple unicode character
     */
    String getSymbol();

    /**
     *
     * @return priority of item
     * items with a higher priority ends up on top visually
     */
    int getWeight();

}
