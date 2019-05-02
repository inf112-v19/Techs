package inf112.skeleton.app;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.logic.BoardLogic;
import inf112.skeleton.app.logic.Direction;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;


public class CheckpointTests {
    
    private static Application application;
    private TiledMap map;
    private BoardLogic board;

    private Vector2 startPos1 = new Vector2(1, 1);
    private Vector2 deathPos1 = new Vector2(1, 5);

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
    public void checkPositionContainsCheckpoint() {
        assertTrue(board.cellContainsLayer(1, 2, "Checkpoints"));
        assertTrue(board.cellContainsLayer(3, 2, "Checkpoints"));
        assertTrue(board.cellContainsLayer(2, 3, "Checkpoints"));
    }
        
    @Test
    public void backupPositionUpdatedAfterFirstCheckpoint() {
        board.addPlayerToBoard(startPos1, deathPos1, "Player", false); //startposition
        board.movePlayer("Player", Direction.NORTH); //Checkpoint (1, 1)
        board.checkAllCheckpoints();
        assertEquals(new Vector2(1, 2), board.getPlayerByName("Player").getBackupPosition());
    }

    @Test
    public void backupPositionUpdatedAfterSecondCheckpoint() {
        board.addPlayerToBoard(startPos1, deathPos1, "Player", false); //startposition
        board.movePlayer("Player", Direction.NORTH); //Checkpoint (1, 1)
        board.checkAllCheckpoints();
        board.movePlayer("Player", Direction.EAST);
        board.movePlayer("Player", Direction.EAST);
        board.checkAllCheckpoints();
        assertEquals(new Vector2(3, 2), board.getPlayerByName("Player").getBackupPosition());
    }

    @Test
    public void backupPositionUpdatedAfterThirdCheckpoint() {
        board.addPlayerToBoard(startPos1, deathPos1, "Player", false); //startposition
        board.movePlayer("Player", Direction.NORTH); //Checkpoint (1, 1)
        board.checkAllCheckpoints();
        board.movePlayer("Player", Direction.EAST);
        board.movePlayer("Player", Direction.EAST);
        board.checkAllCheckpoints();
        board.movePlayer("Player", Direction.NORTH);
        board.movePlayer("Player", Direction.WEST);
        board.checkAllCheckpoints();
        assertEquals(new Vector2(2, 3), board.getPlayerByName("Player").getBackupPosition());
    }

    @Test
    public void notProcessThirdCheckpointBeforeSecondCheckpoint() {
        board.addPlayerToBoard(startPos1, deathPos1, "Player", false);
        board.movePlayer("Player", Direction.NORTH);
        board.checkAllCheckpoints();
        board.movePlayer("Player", Direction.EAST);
        board.movePlayer("Player", Direction.NORTH);
        board.checkAllCheckpoints();
        assertEquals(new Vector2(1,2), board.getPlayerByName("Player").getBackupPosition());
    }

    @Test
    public void notMakeNewBackupAtNonCheckpointTiles() {
        board.addPlayerToBoard(startPos1, deathPos1, "Player", false);
        board.movePlayer("Player", Direction.EAST);
        board.checkAllCheckpoints();
        assertEquals(new Vector2(1,1), board.getPlayerByName("Player").getBackupPosition());
    }
}