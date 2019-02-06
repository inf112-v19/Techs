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
	public boolean validCoordinate(int x, int y) {
		return !((x < 0 || x > width)||(y < 0 || y > height));	
	}

	@Override
	public void moveHorizontal(int x) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveVertical(int y) {
		// TODO Auto-generated method stub
		
	}
	
	private int getIndex(int x, int y) {
		return y * width + x;
	}
	
	private int findPlayer() {
		for(int i = 0; i < height * width; i++) {
			if(Grid[i] == 1)
				return i;
		}
		throw new NoSuchElementException("There is no player in the grid");
	}
	
	
	

}
