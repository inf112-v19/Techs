package inf112.skeleton.app;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.logic.BoardLogic;
import inf112.skeleton.app.logic.Direction;
import inf112.skeleton.app.screens.Board;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;


public class BoardLogicTest {
    
    private static Application application;
    private TiledMap noWallMap;
    private TiledMap wallMap;
    private BoardLogic noWallBoard;
    private BoardLogic wallBoard;

    
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
        
        noWallMap = new TmxMapLoader().load("assets/testMaps/mapNoWalls.tmx");
        wallMap = new TmxMapLoader().load("assets/testMaps/mapAllWalls.tmx");
        
        noWallBoard = new BoardLogic(noWallMap);
        wallBoard = new BoardLogic(wallMap);
    }

    @Test
    public void addPlayerToBoard() {
        noWallBoard.addPlayerToBoard(new Vector2(0,0), new Vector2(2,4), "newPlayer", false);
        assertEquals(new Vector2(0,0), noWallBoard.getPlayerLocation("newPlayer"));
    }
    
    @Test
    public void movePlayerTest() {
        noWallBoard.addPlayerToBoard(new Vector2(1,1), new Vector2(2, 4), "PlayerOne", false);
        assertEquals(new Vector2(1,1), noWallBoard.getPlayerLocation("PlayerOne"));
        noWallBoard.movePlayer("PlayerOne", Direction.NORTH);
        assertEquals(new Vector2(1,2), noWallBoard.getPlayerLocation("PlayerOne"));
        noWallBoard.movePlayer("PlayerOne", Direction.WEST);
        assertEquals(new Vector2(0,2), noWallBoard.getPlayerLocation("PlayerOne"));
        noWallBoard.movePlayer("PlayerOne", Direction.SOUTH);
        assertEquals(new Vector2(0,1), noWallBoard.getPlayerLocation("PlayerOne"));
        noWallBoard.movePlayer("PlayerOne", Direction.EAST);        
        assertEquals(new Vector2(1,1), noWallBoard.getPlayerLocation("PlayerOne"));
    }

    @Test
    public void wallCollisionTest() {
        wallBoard.addPlayerToBoard(new Vector2(1,1), new Vector(2,4), "PlayerOne", false);
		assertEquals(new Vector2(1,1), wallBoard.getPlayerLocation("PlayerOne"));
		wallBoard.movePlayer("PlayerOne", Direction.NORTH);
		assertEquals(new Vector2(1,1), wallBoard.getPlayerLocation("PlayerOne"));
		wallBoard.movePlayer("PlayerOne", Direction.WEST);
		assertEquals(new Vector2(1,1), wallBoard.getPlayerLocation("PlayerOne"));
		wallBoard.movePlayer("PlayerOne", Direction.SOUTH);
		assertEquals(new Vector2(1,1), wallBoard.getPlayerLocation("PlayerOne"));
		wallBoard.movePlayer("PlayerOne", Direction.EAST);
		assertEquals(new Vector2(1,1), wallBoard.getPlayerLocation("PlayerOne"));
    }

    @Test
    public void playerRotationTest() {
        noWallBoard.addPlayerToBoard(new Vector2(1,1), new Vector2(2, 4), "PlayerOne", false);
		assertEquals(Direction.NORTH, noWallBoard.getPlayerRotation("PlayerOne"));
		noWallBoard.rotatePlayer("PlayerOne", 1);
		assertEquals(Direction.EAST, noWallBoard.getPlayerRotation("PlayerOne"));
		noWallBoard.rotatePlayer("PlayerOne", 1);
		assertEquals(Direction.SOUTH, noWallBoard.getPlayerRotation("PlayerOne"));
		noWallBoard.rotatePlayer("PlayerOne", 1);
		assertEquals(Direction.WEST, noWallBoard.getPlayerRotation("PlayerOne"));
		noWallBoard.rotatePlayer("PlayerOne", 1);
		assertEquals(Direction.NORTH, noWallBoard.getPlayerRotation("PlayerOne"));
    }
}
