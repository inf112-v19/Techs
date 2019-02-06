package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Board implements IBoard {

	final int height;
	final int width;
	int[] Grid;
	
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		Grid = new int[height * width];
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
	public int getTile(int x, int y) { //Returner int foreløpig når vi ikke har item
		
		// TODO Auto-generated method stub
		return Grid[getIndex(x, y)];
	}

	@Override
	public void setTile(int x, int y, int tileIndex) {
		Grid[getIndex(x, y)] = tileIndex;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validXCoordinate(int x) {
		return !(x < 0 || x > width);	
	}
	
	@Override
	public boolean validYCoordinate(int y) {
		return !(y < 0 || y > height);
	}

	@Override
	public void moveHorizontal(int x) {
		int playerLocation = findPlayer();
		if (validXCoordinate(findXCoordinate(playerLocation))) {
			Grid[playerLocation] = 0;
			Grid[playerLocation + x] = 1;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveVertical(int y) {
		// TODO Auto-generated method stub
		int playerLocation = findPlayer();
		if (validYCoordinate(findYCoordinate(playerLocation))) {
			Grid[playerLocation] = 0;
			Grid[playerLocation + (y * width)] = 1;
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
			if(Grid[i] == 1)
				return i;
		}
		throw new NoSuchElementException("There is no player in the grid");
	}
	
	public int findXCoordinate(int index) {
		return index % width;	
	}
	
	public int findYCoordinate(int index) {
		return index / width;
	}
	
	public void addPlayer(int x, int y) {
		Grid[getIndex(x, y)] = 1;
	}
}
