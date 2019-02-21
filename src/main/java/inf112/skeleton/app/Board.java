package inf112.skeleton.app;

import java.util.NoSuchElementException;

public class Board implements IBoard {

    /*
     * Board is saved in a 2D array containing integers.
     * This will change in the future,
     * but for now, here is the mapping for each number:
     * 0 - normal tile
     * 1 - player 
     */
    
	final int height;
	final int width;
	int[] grid;
	
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		this.grid = new int[height * width];
	}
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getTile(int x, int y) {
		return grid[getIndex(x, y)];
	}

	@Override
	public void setTile(int x, int y, int tileIndex) {
		grid[getIndex(x, y)] = tileIndex;
	}

	@Override
	public boolean validXCoordinate(int x) {
	    return x >= 0 && x < width;	
	}
	
	@Override
	public boolean validYCoordinate(int y) {
		return y >= 0 && y < height;
	}

	@Override
	public void moveHorizontal(int x) {
		int playerLocation = findPlayer();
		if (validXCoordinate(findXCoordinate(playerLocation) + x)) {
			grid[playerLocation] = 0;
			grid[playerLocation + x] = 1;
		}		
	}

	@Override
	public void moveVertical(int y) {
		int playerLocation = findPlayer();
		if (validYCoordinate(findYCoordinate(playerLocation) + y)) {
			grid[playerLocation] = 0;
			grid[playerLocation + (y * width)] = 1;
		}
	}
	
	/* 
	 * Konverterer x,y-posisjonen til riktig indeks på brettet
	 */
	private int getIndex(int x, int y) { 
		return y * width + x;
	}
	
	/*
	 * Setter spiller foreløpig bare som 1 før vi får inn Player
	 */
	private int findPlayer() { 
		for(int i = 0; i < height * width; i++) {
			if(grid[i] == 1)
				return i;
		}
		throw new NoSuchElementException("There is no player in the grid");
	}
	
	/* 
	 * Konverterer index til x-posisjon
	 */
	public int findXCoordinate(int index) {
		return index % width;	
	}
	
	/* 
	 * Konverterer index til y-posisjon
	 */
	public int findYCoordinate(int index) {
		return index / width;
	}
}
