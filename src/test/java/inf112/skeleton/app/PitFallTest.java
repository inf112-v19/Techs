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
    	checkPositionIfPit(new Vector2(1, 6));  //Check if tile contains Pit
    	assertEquals(new Vector2(1, 4), board.getPlayerLocation("Player"));  
    } 
    
    @Test
    public void movePlayerToCheckpointAfterPitFall() {
    	Vector2 startPos = new Vector2(9, 15);
    	board.addPlayerToBoard(startPos, "Player");
    	checkPositionIfPit(new Vector2(3, 15));
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
    public void checkPlayerBackupUpdating() {
    	Vector2 startPos = new Vector2(1, 4);
    	PlayerToken player = new PlayerToken("Player", "assets/BlueRobotSpriteSheet.png", startPos);
    	board.addPlayerToBoard(startPos, player.getName());
    	board.movePlayer("Player", Direction.NORTH);
    	player.passCheckpoint();
    	assertEquals(player.getBackupPosition(), new Vector2(1, 5));
    }
    
    /** Funkar ikkje foreløpig
    @Test
    public void checkIfTwoPlayersCanMoveBackToSameBackup() {
    	Vector2 startPos = new Vector2(1, 4);
    	PlayerToken player1 = new PlayerToken("Player1", "assets/BlueRobotSpriteSheet.png", startPos);
    	PlayerToken player2 = new PlayerToken("Player2", "assets/BlueRobotSpriteSheet.png", startPos);
    	board.addPlayerToBoard(startPos, player1.getName());
    	board.addPlayerToBoard(startPos, player2.getName());
    	board.movePlayer("Player1", Direction.NORTH);
    	board.movePlayer("Player1", Direction.NORTH);
    	board.movePlayer("Player2", Direction.NORTH);
    	board.movePlayer("Player2", Direction.NORTH);
    	System.out.println("P1: " + board.getPlayerLocation("Player1"));
    	System.out.println("P2: " + board.getPlayerLocation("Player2"));
    	assertEquals(player1.getBackupPosition(), player2.getBackupPosition());
    	assertEquals(board.getPlayerLocation("Player1"), board.getPlayerLocation("Player2"));
    	assertEquals(board.getPlayerLocation("Player1"), player1.getBackupPosition());
    }
    */
    
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
     * check of tile contains pit
     * @param position
     */
    public void checkPositionIfPit(Vector2 position) {
    	assertTrue(board.cellContainsLayer((int)position.x, (int)position.y, "Pit"));
    }
    
    public void checkPositionIfCheckpoint(Vector2 position) {
    	assertTrue(board.cellContainsLayer((int)position.x, (int)position.y, "Checkpoints"));
    }
}
