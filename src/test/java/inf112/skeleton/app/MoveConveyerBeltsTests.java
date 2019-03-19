package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

public class MoveConveyerBeltsTests {
    
    private BoardLogic boardLogic;
    private Sprite robotSprite;
    private TiledMap map;    

    @Before
    public void setUp() {
        robotSprite = new Sprite (new Texture("assets/GreenRobot.png"));
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        boardLogic = new BoardLogic(robotSprite, 0, map);
    }
    
    @Test
    public void testPlayerMovesToTheRight() {
        robotSprite = null;
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        boardLogic = new BoardLogic(robotSprite, 0, map);

        
        boardLogic.addPlayerToBoard(new Vector2(0,1), "testPlayer");
        

        boardLogic.moveConveyorBelts();
        assertEquals(new Vector2(0,2), boardLogic.getPlayerLocation("testPlayer"));
    }
}
