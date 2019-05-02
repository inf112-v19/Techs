package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.logic.Direction;
import inf112.skeleton.app.objects.PlayerToken;

public class PlayerTokenTest {

    private static final String SPRITE = "assets/YellowRobotSpriteSheet.png";
    private static Application application;
    
    private PlayerToken player;
    

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
        
        player = new PlayerToken("testPlayer", SPRITE, new Vector2(1,1), new Vector2(2, 4), false);
    }
    
    @Test
    public void getName() {
        assertEquals("testPlayer", player.getName());
    }
    @Test
    public void playerFacesNorthAtStart() {
        assertEquals(Direction.NORTH, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerOnce() {
        player.rotatePlayer(1);
        assertEquals(Direction.EAST, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerTwice() {
        player.rotatePlayer(2);
        assertEquals(Direction.SOUTH, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerThreeTimes() {
        player.rotatePlayer(3);
        assertEquals(Direction.WEST, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerFourTimes() {
        player.rotatePlayer(4);
        assertEquals(Direction.NORTH, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerOnceOpposite() {
        player.rotatePlayer(-1);
        assertEquals(Direction.WEST, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerTwiceOpposite() {
        player.rotatePlayer(-2);
        assertEquals(Direction.SOUTH, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerThreeTimesOpposite() {
        player.rotatePlayer(-3);
        assertEquals(Direction.EAST, player.getFacingDirection());
    }
    @Test
    public void rotatePlayerFourTimesOpposite() {
        player.rotatePlayer(-4);
        assertEquals(Direction.NORTH, player.getFacingDirection());
    }
    @Test
    public void playerMovesNorth() {
        player.moveDirection(Direction.NORTH);
        assertEquals(1, player.getXPosition());
        assertEquals(2, player.getYPosition());
    }
    @Test
    public void playerMovesSouth() {
        player.moveDirection(Direction.SOUTH);
        assertEquals(1, player.getXPosition());
        assertEquals(0, player.getYPosition());
    }
    @Test
    public void playerMovesWest() {
        player.moveDirection(Direction.WEST);
        assertEquals(0, player.getXPosition());
        assertEquals(1, player.getYPosition());
    }
    @Test
    public void playerMovesEast() {
        player.moveDirection(Direction.EAST);
        assertEquals(2, player.getXPosition());
        assertEquals(1, player.getYPosition());
    }
}
