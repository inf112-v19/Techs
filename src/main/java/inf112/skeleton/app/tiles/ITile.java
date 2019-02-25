package inf112.skeleton.app.tiles;

public interface ITile {

    /**
     *
     * The type of a tile. It might be as simple as "flag"
     *
     * @return type of tile
     */
    String getTileType();

    void setTileType(String name);

    /**
     *
     * This might be a simple unicode character, for the time being.
     *
     * @return symbol to item
     */
    String getSymbol();

    void setSymbol(String symbol);

    /**
     *
     * Item with a higher priority ends up on top visually.
     *
     * @return priority of item
     */
    int getTileWeight();

    void setTileWeight(int weight);

    /**
     *
     * A way to identify the type of tile it is, based on an int.
     *
     * @return the ID of the tile.
     */
    int getTileID();

    void setTileID(int id);

}
