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
    public void addPlayerToBoard() {
        board.addPlayerToBoard(new Vector2(1,1), "Player");
        assertEquals(new Vector2(1,1), board.getPlayerLocation("Player"));
    }
    
    @Test
    public void movePlayerToStartPositionAfterPitFall() {
    	board.addPlayerToBoard(new Vector2(0,3), "Player");
    	Vector2 startPos = new Vector2(0, 3);
    	board.movePlayer("Player", Direction.NORTH);
    	board.movePlayer("Player", Direction.NORTH);
    	assertTrue(board.cellContainsLayer(0, 5, "Pit"));
    	assertEquals(startPos, board.getPlayerLocation("Player"));  	  	
    }
    
    @Test
    public void movePlayerToCheckPointAfterPitFall() {
    	Vector2 startPos = new Vector2(0, 3);
    	PlayerToken player = new PlayerToken("Player", "assets/BlueRobotSpriteSheet.png", startPos);
    	board.addPlayerToBoard(startPos, player.getName());
    	board.movePlayer("Player", Direction.NORTH);
    	player.passCheckpoint();
    	board.movePlayer("Player", Direction.NORTH);
    	assertTrue(board.cellContainsLayer(0, 5, "Pit"));
    	assertEquals(player.getBackupPosition(), new Vector2(0, 4));
    	//assertEquals(player.getBackupPosition(), board.getPlayerLocation("Player"));
    }
    
    @Test
    public void checkPlayerBackupUpdating() {
    	Vector2 startPos = new Vector2(0, 3);
    	PlayerToken player = new PlayerToken("Player", "assets/BlueRobotSpriteSheet.png", startPos);
    	board.addPlayerToBoard(startPos, player.getName());
    	board.movePlayer("Player", Direction.NORTH);
    	player.passCheckpoint();
    	board.movePlayer("Player", Direction.NORTH);
    	assertEquals(player.getBackupPosition(), new Vector2(0, 4));
    }
}
