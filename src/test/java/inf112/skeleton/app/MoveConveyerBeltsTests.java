package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;

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

public class MoveConveyerBeltsTests {
    
    private static Application application;

    private BoardLogic circleBoard;    

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
        circleBoard = new BoardLogic(circleMap);
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
    public void playerRotates() {
        circleBoard.addPlayerToBoard(new Vector2(1, 0), "testPlayerOne");
        circleBoard.moveConveyorBelts();
        
        assertEquals(Direction.WEST, circleBoard.getPlayerRotation("testPlayerOne"));
    }
}



















