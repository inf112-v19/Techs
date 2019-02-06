package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class BoardTest {
    
    private Board board;
    private int width = 5;
    private int height = 6;
    
    @Before
    public void setUp() {
        board = new Board(height, width);
    }
    
    @Test
    public void addPlayerAtXOneYOne() {
        board.addPlayer(1, 1);
        assertEquals(board.getTile(1, 1), 1);
    }
    
    @Test
    public void addPlayerAtXThreeYFive() {
        board.addPlayer(3, 5);
        assertEquals(board.getTile(3, 5), 1);
    }
    
    @Test
    public void movePlayerOneToTheRight() {
        board.addPlayer(1, 1);
        board.moveHorizontal(1);
        assertEquals(board.getTile(2, 1), 1);
        assertEquals(board.getTile(1, 1), 0);
    }
    
    @Test
    public void movePlayerOneToTheLeft() {
        board.addPlayer(1, 1);
        board.moveHorizontal(-1);
        assertEquals(board.getTile(0, 1), 1);
        assertEquals(board.getTile(1, 1), 0);
    }
    
    @Test
    public void movePlayerOneUp() {
        board.addPlayer(1, 1);
        board.moveVertical(1);
        assertEquals(board.getTile(1, 2), 1);
        assertEquals(board.getTile(1, 1), 0);
    }
    
    @Test
    public void movePlayerOneDown() {
        board.addPlayer(1, 1);
        board.moveVertical(-1);
        assertEquals(board.getTile(1, 0), 1);
        assertEquals(board.getTile(1, 1), 0);
    }
    
    @Test
    public void createEmptyBoard() {
        assertEquals(board.getTile(3, 3), 0);
    }
    
    @Test
    public void getHeightReturnsRightValue() {
        assertEquals(board.getHeight(), height);
    }
    
    @Test
    public void getWidthReturnsRightValue() {
        assertEquals(board.getWidth(), width);
    }
    
    @Test
    public void findCorrectXCoordinateWhenAtZero() {
        assertEquals(board.findXCoordinate(0), 0);
    }
    
    @Test
    public void findCorrectXCoordinateWhenAtOne() {
        assertEquals(board.findXCoordinate(1), 1);
    }
    
    @Test
    public void findCorrectXCoordinateWhenAtFive() {
        assertEquals(board.findXCoordinate(5), 0);
    }
    
    @Test
    public void findCorrectXCoordinateWhenAtTwentyNine() {
        assertEquals(board.findXCoordinate(29), 4);
    }
    
    @Test
    public void findCorrectYCoordinateWhenAtZero() {
        assertEquals(board.findYCoordinate(0), 0);
    }
    
    @Test
    public void findCorrectYCoordinateWhenAtOne() {
        assertEquals(board.findYCoordinate(1), 0);
    }
    
    @Test
    public void findCorrectYCoordinateWhenAtFive() {
        assertEquals(board.findYCoordinate(5), 1);
    }
    
    @Test
    public void findCorrectYCoordinateWhenAtTwentyNine() {
        assertEquals(board.findYCoordinate(29), 5);
    }
}
