package inf112.skeleton.app;

import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardLogic;
import inf112.skeleton.app.logic.Direction;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;


public class BoardLogicTest {

    @Before
    void setUp() {
        TiledMap map = new TmxMapLoader(new ExternalFileHandleResolver()).load("assets/RoboRallyMap.tmx");
        BoardLogic board = new BoardLogic(map);
    }

    @Test
    public void movePlayerTest() {
        board.addPlayerToBoard(new Vector2(0,0), "player1");
        board.movePlayer("player1", Direction.NORTH);
        assertEquals(board.getPlayerLocation("player1"), new Vector2(0,1));
    }

    @After
    void stopTest() {
        
    }
}
