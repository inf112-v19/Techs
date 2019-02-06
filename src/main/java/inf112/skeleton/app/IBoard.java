package inf112.skeleton.app;

public interface IBoard {

    /**
     * A method that returns the height of the board
     *
     * @return height of board
     */
    int getHeight();

    /**
     * A method that returns the width of the board
     *
     * @return width of board
     */
    int getWidth();

    /**
     * A method to get the type of tile that is on the coordinate (x,y).
     *
     * @param x - the x-coordinate
     * @param y - the y-coordinate
     * @return the tile on coordinate (x,y)
     */
     int getTile(int x, int y);

    /**
     * A method to set the type fo tile that is on the coordinate (x,y).
     *
     * @param x - the x-coordinate
     * @param y - the y-coordinate
     */
    void setTile(int x, int y, int tileIndex);

    /**
     * A method that checks if a tile (y) on the board is a valid location.
     * 0 <= x < getWidth()
     *
     * @param x - the x-coordinate
     * @return true if inside borders of board, otherwise false
     */
    boolean validXCoordinate(int x);
    
    /**
     * A method that checks if a tile (y) on the board is a valid location.
     * 0 <= y < getHeight()
     *
     * @param y - the y-coordinate
     * @return true if inside borders of board, otherwise false
     */
    boolean validYCoordinate(int y);
    
    /**
     * A method that moves the tiles horizontal
     * 
     * 
     * @param x - the x-cordinate
     */
    void moveHorizontal(int x);
    	
    
    /**
     * A method that moves the tiles vertical
     * 
     * 
     * @param y
     */
    void moveVertical(int y); 
}

