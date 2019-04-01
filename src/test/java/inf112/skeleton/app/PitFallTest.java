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
import inf112.skeleton.app.objects.PlayerToken;
import inf112.skeleton.app.screens.Board;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;


public class PitFallTest {
    
    private static Application application;
    private TiledMap map;
    private BoardLogic board;

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
        
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");        
        board = new BoardLogic(map);
    }
    
    @Test
    public void movePlayerToStartpositionAfterPitFall() {
    	board.addPlayerToBoard(new Vector2(1, 4), "Player"); //StartPosition (1, 4)
    	board.movePlayer("Player", Direction.NORTH);
    	board.movePlayer("Player", Direction.NORTH);
    	checkPositionIfContainsPit(new Vector2(1, 6));  //Check if tile contains Pit
    	assertEquals(new Vector2(1, 4), board.getPlayerLocation("Player"));  
    } 
    
    @Test
    public void movePlayerToCheckpointAfterPitFall() {
    	Vector2 startPos = new Vector2(9, 15);
    	board.addPlayerToBoard(startPos, "Player");
    	checkPositionIfContainsPit(new Vector2(3, 15));
    	board.movePlayer("Player", Direction.WEST); //(8, 15)
    	board.checkAllCheckpoints(); 
    	board.movePlayer("Player", Direction.WEST); //(7, 15)
    	board.movePlayer("Player", Direction.WEST); //(6, 15)
    	board.movePlayer("Player", Direction.WEST); //(5, 15)
    	board.movePlayer("Player", Direction.WEST); //(4, 15)
    	board.movePlayer("Player", Direction.WEST); //Pitfall (3, 15)
    	assertEquals(new Vector2(8, 15), board.getPlayerLocation("Player"));
    }
    
    @Test
    public void checkIfTwoPlayersCanMoveToSameBackup() {
    	board.addPlayerToBoard(new Vector2(1, 4), "Player1");
    	board.addPlayerToBoard(new Vector2(1, 4), "Player2");
    	board.movePlayer("Player1", Direction.NORTH);
    	board.movePlayer("Player1", Direction.NORTH);
    	board.movePlayer("Player2", Direction.NORTH);
    	board.movePlayer("Player2", Direction.NORTH);
    	assertEquals(board.getPlayerLocation("Player1"), board.getPlayerLocation("Player2"));
    }
    
    
    /**
     * Tests that player moves to backup when moving out of board.
     */
    @Test
    public void moveToBackupIfOutOfBoardTest() {
    	board.addPlayerToBoard(new Vector2(2,2), "Player"); 
    	board.movePlayer("Player", Direction.WEST);
    	board.movePlayer("Player", Direction.WEST); //(0,2) out of board
    	assertEquals(board.getPlayerLocation("Player"), new Vector2(2, 2));
    }
    
    /**
     * check if a position contains pit
     * @param position
     */
    public void checkPositionIfContainsPit(Vector2 position) {
    	assertTrue(board.cellContainsLayer((int)position.x, (int)position.y, "Pit"));
    }
    
}
