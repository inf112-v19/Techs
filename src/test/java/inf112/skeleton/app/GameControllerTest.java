package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardLogic;
import org.junit.Test;

public class GameControllerTest {

    private TiledMap noWallMap;
    private TiledMap wallMap;
    private BoardLogic board;

    @Before
    public void setUp(){
        noWallMap = new TmxMapLoader().load("assets/testMaps/mapNoWalls.tmx");
        wallMap = new TmxMapLoader().load("assets/testMaps/mapAllWalls.tmx");
        //GameController gameController = new GameController(2);
        board.addPlayerToBoard(new Vector2(1,1), "PlayerOne");
        board.addPlayerToBoard(new Vector2(2,2), "PlayerTwo");
    }




}
