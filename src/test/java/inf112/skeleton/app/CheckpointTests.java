package inf112.skeleton.app;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;


public class CheckpointTests {
    
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
        
        map = new TmxMapLoader().load("assets/TestMaps/checkpointMap.tmx");        
        board = new BoardLogic(map);
    }
        
        @Test
        public void testBackupPositionUpdatedAfterCheckpoint() {
        	checkPositionIfContainsCheckpoint(new Vector2(1, 1));
        	checkPositionIfContainsPit(new Vector2(2, 0));
        	board.addPlayerToBoard(new Vector2(2, 1), "Player", false); //startposition
            board.movePlayer("Player", Direction.WEST); //Checkpoint (1, 1)
            board.checkAllCheckpoints();
            board.movePlayer("Player", Direction.SOUTH);
            board.movePlayer("Player", Direction.EAST);       //pit (2, 0)    
            assertEquals(board.getPlayerLocation("Player"), new Vector2(1,1));    
        } 
        
        @Test
        public void testCantProcessCheckpoint2beforeCheckpoint1() {
        	checkPositionIfContainsCheckpoint(new Vector2(3, 1)); //checkpoint2 position
        	board.addPlayerToBoard(new Vector2(2, 1), "Player", false); //startposition
        	board.movePlayer("Player", Direction.EAST);       //checkpoint 2    
        	board.checkAllCheckpoints();
        	board.movePlayer("Player", Direction.WEST);          
        	board.movePlayer("Player", Direction.SOUTH); //pit position
        	assertEquals(board.getPlayerLocation("Player"), new Vector2(2,1)); //check that player moves to startposition       	
        }
        
        @Test
        public void testCanProcessCheckpoint2AfterCheckpoint1 () {
        	checkPositionIfContainsCheckpoint(new Vector2(1, 1)); //checkpoint1 position
        	checkPositionIfContainsCheckpoint(new Vector2(3, 1)); //checkpoint2 position
        	board.addPlayerToBoard(new Vector2(2, 1), "Player", false); //startposition
        	board.movePlayer("Player", Direction.WEST);
        	board.checkAllCheckpoints();        	
        	board.movePlayer("Player", Direction.EAST);       //checkpoint 2    
        	board.movePlayer("Player", Direction.EAST);       //checkpoint 2    
        	board.checkAllCheckpoints();
        	board.movePlayer("Player", Direction.WEST);
        	board.movePlayer("Player", Direction.SOUTH); //pit position
        	assertEquals(board.getPlayerLocation("Player"), new Vector2(3,1)); //check that player moves to checkpoint2       	
        }
        
        @Test
        public void testCantMakeNewBackupAtNonCheckpointTiles() {
        	checkPositionIfNotContainsCheckpoint(new Vector2(1, 0)); 
        	board.addPlayerToBoard(new Vector2(0, 0), "Player", false); //startposition
        	board.movePlayer("Player", Direction.EAST);           
        	board.checkAllCheckpoints();          
        	board.movePlayer("Player", Direction.EAST); //pit position
        	assertEquals(board.getPlayerLocation("Player"), new Vector2(0,0)); //check that player moves to startposition       	
        }
        
                      
        public void checkPositionIfContainsCheckpoint(Vector2 position) {
        	assertTrue(board.cellContainsLayer((int)position.x, (int)position.y, "Checkpoints"));
        }
        
        public void checkPositionIfNotContainsCheckpoint(Vector2 position) { 
        	assertTrue(!board.cellContainsLayer((int)position.x, (int)position.y, "Checkpoints"));
        }
        
        public void checkPositionIfContainsPit(Vector2 position) {
        	assertTrue(board.cellContainsLayer((int)position.x, (int)position.y, "Pit"));
        }
        
       
    
    
    
}