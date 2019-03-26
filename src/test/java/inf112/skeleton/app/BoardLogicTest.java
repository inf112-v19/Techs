package inf112.skeleton.app;

import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardLogic;
import inf112.skeleton.app.logic.Direction;
import org.junit.After;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


public class BoardLogicTest {
    private TiledMap noWallMap;
    private TiledMap wallMap;
    private BoardLogic board;

    @Before
    void setUp() {
        noWallMap = new TmxMapLoader().load("assets/testMaps/mapNoWalls.tmx");
        wallMap = new TmxMapLoader().load("assets/testMaps/mapAllWalls.tmx");
        board.addPlayerToBoard(new Vector2(1,1), "PlayerOne");
    }

    @Test
    public void movePlayerTest() {
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

    @Test
    public void wallCollisionTest() {
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

    @Test
    public void playerRotationTest() {
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

    @After
    public void disposeOfMap() {
        wallMap.dispose();
        noWallMap.dispose();
        // Probably not needed
//        board.testEnder();
    }
}
