package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;

import inf112.skeleton.app.logic.BoardLogic;
import inf112.skeleton.app.logic.Direction;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

public class MoveConveyorBeltsTests {
    
    private static Application application;

    private BoardLogic circleBoard;
    private BoardLogic circleBoardRight;
    private BoardLogic circleBoardDouble;
    private BoardLogic doubleMovesFirstBoard;
    
    @Before
    public void setUp() {
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });
        
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        
        TiledMap circleMap = new TmxMapLoader().load("assets/testMaps/mapCircleConveyor.tmx");
        TiledMap circleMapRight = new TmxMapLoader().load("assets/testMaps/mapCircleConveyorRight.tmx");
        TiledMap circleMapDouble = new TmxMapLoader().load("assets/testMaps/mapDoubleCircleConveyor.tmx");
        TiledMap doubleMovesFirstMap = new TmxMapLoader().load("assets/testMaps/mapDoubleMovesFirst.tmx");
        circleBoard = new BoardLogic(circleMap);
        circleBoardRight = new BoardLogic(circleMapRight);
        circleBoardDouble = new BoardLogic(circleMapDouble);
        doubleMovesFirstBoard = new BoardLogic(doubleMovesFirstMap);
    }
    
    @Test
    public void playerMovesLeft() {
        circleBoard.addPlayerToBoard(new Vector2(1, 2), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(new Vector2(0, 2), circleBoard.getPlayerLocation("testPlayerOne"));
    }
    @Test
    public void playerMovesRight() {
        circleBoard.addPlayerToBoard(new Vector2(1, 0), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(new Vector2(2, 0), circleBoard.getPlayerLocation("testPlayerOne"));
    }
    @Test
    public void playerMovesDown() {
        circleBoard.addPlayerToBoard(new Vector2(0, 1), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(new Vector2(0, 0), circleBoard.getPlayerLocation("testPlayerOne"));
    }
    @Test
    public void playerMovesUp() {
        circleBoard.addPlayerToBoard(new Vector2(2, 1), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(new Vector2(2, 2), circleBoard.getPlayerLocation("testPlayerOne"));
    }
    @Test
    public void playerRotatesLeftBottomLeftCorner() {
        circleBoard.addPlayerToBoard(new Vector2(1, 0), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(Direction.WEST, circleBoard.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void playerRotatesLeftBottomRightCorner() {
        circleBoard.addPlayerToBoard(new Vector2(1, 0), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(Direction.WEST, circleBoard.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void playerRotatesLeftUpperRightCorner() {
        circleBoard.addPlayerToBoard(new Vector2(2, 1), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(Direction.WEST, circleBoard.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void playerRotatesLeftUpperLeftCorner() {
        circleBoard.addPlayerToBoard(new Vector2(1, 2), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        assertEquals(Direction.WEST, circleBoard.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void playerRotatesRightUpperLeftCorner() {
        circleBoardRight.addPlayerToBoard(new Vector2(0, 1), "testPlayerOne");
        circleBoardRight.moveConveyorBelts();
        assertEquals(Direction.EAST, circleBoardRight.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void playerRotatesRightBottomLeftCorner() {
        circleBoardRight.addPlayerToBoard(new Vector2(1, 0), "testPlayerOne");
        circleBoardRight.moveConveyorBelts();
        assertEquals(Direction.EAST, circleBoardRight.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void playerRotatesRightBottomRightCorner() {
        circleBoardRight.addPlayerToBoard(new Vector2(2, 1), "testPlayerOne");
        circleBoardRight.moveConveyorBelts();
        assertEquals(Direction.EAST, circleBoardRight.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void playerRotatesRightUpperRightCorner() {
        circleBoardRight.addPlayerToBoard(new Vector2(1, 2), "testPlayerOne");
        circleBoardRight.moveConveyorBelts();
        assertEquals(Direction.EAST, circleBoardRight.getPlayerRotation("testPlayerOne"));
    }
    @Test
    public void twoPlayersStraightForward() {
        circleBoard.addPlayerToBoard(new Vector2(0,0), "testPlayerOne");
        circleBoard.addPlayerToBoard(new Vector2(1,0), "testPlayerTwo");
        circleBoard.moveConveyorBelts();
        assertEquals(new Vector2(1,0), circleBoard.getPlayerLocation("testPlayerOne"));
        assertEquals(new Vector2(2,0), circleBoard.getPlayerLocation("testPlayerTwo"));
    }
    @Test
    public void twoPlayersOneGetsMovedUp() {
        circleBoard.addPlayerToBoard(new Vector2(1,0), "testPlayerOne");
        circleBoard.addPlayerToBoard(new Vector2(2,0), "testPlayerTwo");
        circleBoard.moveConveyorBelts();
        assertEquals(new Vector2(2,0), circleBoard.getPlayerLocation("testPlayerOne"));
        assertEquals(new Vector2(2,1), circleBoard.getPlayerLocation("testPlayerTwo"));
    }
    @Test
    public void threePlayersInALine() {
        circleBoard.addPlayerToBoard(new Vector2(0,0), "testPlayerOne");
        circleBoard.addPlayerToBoard(new Vector2(1,0), "testPlayerTwo");
        circleBoard.addPlayerToBoard(new Vector2(2,0), "testPlayerThree");
        circleBoard.moveConveyorBelts();
        assertEquals(new Vector2(1,0), circleBoard.getPlayerLocation("testPlayerOne"));
        assertEquals(new Vector2(2,0), circleBoard.getPlayerLocation("testPlayerTwo"));
        assertEquals(new Vector2(2,1), circleBoard.getPlayerLocation("testPlayerThree"));
    }
    @Test
    public void doubleConveyorBeltOnePlayerMoves() {
        circleBoardDouble.addPlayerToBoard(new Vector2(1, 2), "testPlayerOne");
        circleBoardDouble.moveConveyorBelts();
        assertEquals(new Vector2(0, 1), circleBoardDouble.getPlayerLocation("testPlayerOne"));
    }
    @Test
    public void doubleConveyorBeltTwoPlayerMove() {
        circleBoardDouble.addPlayerToBoard(new Vector2(1, 2), "testPlayerOne");
        circleBoardDouble.addPlayerToBoard(new Vector2(2, 2), "testPlayerTwo");
        circleBoardDouble.moveConveyorBelts();
        assertEquals(new Vector2(0, 1), circleBoardDouble.getPlayerLocation("testPlayerOne"));
        assertEquals(new Vector2(0, 2), circleBoardDouble.getPlayerLocation("testPlayerTwo"));
    }
    @Test
    public void doubleMovesFirst() {
        doubleMovesFirstBoard.addPlayerToBoard(new Vector2(1,0), "testPlayerOne");
        doubleMovesFirstBoard.addPlayerToBoard(new Vector2(2,1), "testPlayerTwo");
        doubleMovesFirstBoard.moveConveyorBelts();
        assertEquals(new Vector2(0, 1), doubleMovesFirstBoard.getPlayerLocation("testPlayerOne"));
        assertEquals(new Vector2(1, 1), doubleMovesFirstBoard.getPlayerLocation("testPlayerTwo"));

    }
}





















