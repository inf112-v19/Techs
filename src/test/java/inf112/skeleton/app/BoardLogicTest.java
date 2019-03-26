package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardLogic;
import inf112.skeleton.app.logic.Direction;
import inf112.skeleton.app.screens.Board;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class BoardLogicTest {
    private TiledMap noWallMap;
    private TiledMap wallMap;
    private BoardLogic board;
    
    LwjglApplication app;
    
    @Before
    void setUp() {
    	/*
    	noWallMap = new TmxMapLoader().load("assets/testMaps/mapNoWalls.tmx");
        wallMap = new TmxMapLoader().load("assets/testMaps/mapAllWalls.tmx");
        board.addPlayerToBoard(new Vector2(1,1), "PlayerOne");
        */
    	
    	/*
    	// This didn't work for some reason, app stays null
    	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = 480;
        cfg.height = 320;
        cfg.resizable = true;
        this.app = new LwjglApplication( new RoboRally(), cfg);
        */
    }
    
    @Test
    public void testingTest() {
    	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = 480;
        cfg.height = 320;
        cfg.resizable = true;
        LwjglApplication app = new LwjglApplication( new RoboRally(), cfg);
    	app.postRunnable(new Runnable() {
			@Override
			public void run() {
            	TiledMap noWallMap = new TmxMapLoader().load("assets/testMaps/mapNoWalls.tmx");
            	BoardLogic board = new BoardLogic(noWallMap);
            	board.addPlayerToBoard(new Vector2(0,0), "playerOne");
            	assertEquals(new Vector2(0,0), board.getPlayerLocation("playerOne"));
			}
    	});
    }

    @Test
    public void movePlayerTest() {
    	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = 480;
        cfg.height = 320;
        cfg.resizable = true;
        LwjglApplication app = new LwjglApplication( new RoboRally(), cfg);
    	app.postRunnable(new Runnable() {
			@Override
			public void run() {
            	TiledMap noWallMap = new TmxMapLoader().load("assets/testMaps/mapNoWalls.tmx");
            	BoardLogic board = new BoardLogic(noWallMap);
            	board = new BoardLogic(noWallMap);
                assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,1));
                board.movePlayer("PlayerOne", Direction.NORTH);
                assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,2));
                board.movePlayer("PlayerOne", Direction.WEST);
                assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(0,2));
                board.movePlayer("PlayerOne", Direction.SOUTH);
                assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(0,1));
                board.movePlayer("PlayerOne", Direction.EAST);
                assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,1));
			}
    	});
    }

    @Test
    public void wallCollisionTest() {
    	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = 480;
        cfg.height = 320;
        cfg.resizable = true;
        LwjglApplication app = new LwjglApplication( new RoboRally(), cfg);
    	app.postRunnable(new Runnable() {
			@Override
			public void run() {
				board = new BoardLogic(wallMap);
				assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,1));
				board.movePlayer("PlayerOne", Direction.NORTH);
				assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,1));
				board.movePlayer("PlayerOne", Direction.WEST);
				assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,1));
				board.movePlayer("PlayerOne", Direction.SOUTH);
				assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,1));
				board.movePlayer("PlayerOne", Direction.EAST);
				assertEquals(board.getPlayerLocation("PlayerOne"), new Vector2(1,1));
			}
    	});
    }

    @Test
    public void playerRotationTest() {
    	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = 480;
        cfg.height = 320;
        cfg.resizable = true;
        LwjglApplication app = new LwjglApplication( new RoboRally(), cfg);
    	app.postRunnable(new Runnable() {
			@Override
			public void run() {
				
				board = new BoardLogic(noWallMap);
				assertEquals(Direction.NORTH, board.getPlayerRotation("PlayerOne"));
				board.rotatePlayer("PlayerOne", 1);
				assertEquals(Direction.EAST, board.getPlayerRotation("PlayerOne"));
				board.rotatePlayer("PlayerOne", 1);
				assertEquals(Direction.SOUTH, board.getPlayerRotation("PlayerOne"));
				board.rotatePlayer("PlayerOne", 1);
				assertEquals(Direction.WEST, board.getPlayerRotation("PlayerOne"));
				board.rotatePlayer("PlayerOne", 1);
				assertEquals(Direction.NORTH, board.getPlayerRotation("PlayerOne"));
			}
    	});
    }
    
    /*
    @After
    public void disposeOfMap() {
        wallMap.dispose();
        noWallMap.dispose();
        // Probably not needed
        // board.testEnder();
    }*/
}
