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


public class PitFallTest {
    
    private static Application application;
    private TiledMap map;
    private BoardLogic board;

    private Vector2 startPos1 = new Vector2(1, 2);
    private Vector2 deathPos1 = new Vector2(1, 5);

    private Vector2 startPos2 = new Vector2(3, 2);
    private Vector2 deathPos2 = new Vector2(2,5);

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
        
        map = new TmxMapLoader().load("assets/TestMaps/mapPitFalls.tmx");
        board = new BoardLogic(map);
    }

    @Test
    public void checkPositionIfContainsPit() {
        assertTrue(board.cellContainsLayer(3, 3, "Pit"));
    }
    
    @Test
    public void movePlayerToDeathPositionAfterPitFall() {
    	board.addPlayerToBoard(this.startPos1, this.deathPos1, "Player"); //StartPosition (1, 4)
    	board.movePlayer("Player", Direction.NORTH);
    	board.movePlayer("Player", Direction.EAST);
        board.movePlayer("Player", Direction.EAST);
    	board.checkPitfalls();
    	assertEquals(this.deathPos1, board.getPlayerLocation("Player"));
    }
    
    @Test
    public void checkIfTwoPlayersMovesToTheirDeathPositions() {
    	board.addPlayerToBoard(this.startPos1, this.deathPos1, "Player1");
    	board.addPlayerToBoard(this.startPos2, this.deathPos2, "Player2");
    	board.movePlayer("Player1", Direction.WEST);
    	board.movePlayer("Player2", Direction.EAST);
    	assertEquals(deathPos1, board.getPlayerLocation("Player1"));
    	assertEquals(deathPos2, board.getPlayerLocation("Player2"));
    }
    
    /**
     * Tests that player moves to backup when moving out of board.
     */
    @Test
    public void moveToDeathPositionIfOutOfBoardTest() {
    	board.addPlayerToBoard(startPos1, deathPos1, "Player");
    	board.movePlayer("Player", Direction.WEST);
        assertEquals(deathPos1, board.getPlayerLocation("Player"));
    }


    @Test
    public void PlayerLosesHealthIfOutOfBoard() {
        board.addPlayerToBoard(startPos1, deathPos1, "Player");
        board.movePlayer("Player", Direction.WEST);
        assertEquals(2, board.getPlayerByName("Player").getDamageToken());
        assertEquals(2, board.getPlayerByName("Player").getHealth());

    }
    
}
