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

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LaserTests {

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

        map = new TmxMapLoader().load("assets/TestMaps/mapLasers.tmx");
        board = new BoardLogic(map);
    }

    @Test
    public void testLaserExistsOnBoard() {
        assertTrue(board.cellContainsLayer(0,0, "Laser"));
        assertTrue(board.cellContainsLayer(1,0, "Laser"));
        assertTrue(board.cellContainsLayer(2,0, "Laser"));
        assertFalse(board.cellContainsLayer(0,1, "Laser"));
        assertFalse(board.cellContainsLayer(1,1, "Laser"));
        assertFalse(board.cellContainsLayer(2,1, "Laser"));
        assertTrue(board.cellContainsLayer(0,2, "Laser"));
        assertTrue(board.cellContainsLayer(1,2, "Laser"));
        assertTrue(board.cellContainsLayer(2,2, "Laser"));
    }

    @Test
    public void OneDamageTokenReceivedAfterOneLaserShot() {
        board.addPlayerToBoard(new Vector2(1, 0), "Player");
        board.activateLasersOnBoard(); // First lasershot
        assertEquals(1, board.getPlayerByName("Player").getDamageToken());
    }

    @Test
    public void TwoDamageTokenReceivedAfterTwoLaserShot() {
        board.addPlayerToBoard(new Vector2(1, 0), "Player");
        board.activateLasersOnBoard(); // First lasershot
        board.activateLasersOnBoard(); // Second lasershot
        assertEquals(2, board.getPlayerByName("Player").getDamageToken());
    }

    @Test
    public void OneHealthRemovedIfTenDamageTokenAfterLaser() {
        board.addPlayerToBoard(new Vector2(1, 0), "Player");
        board.getPlayerByName("Player").setDamageToken(9);
        board.activateLasersOnBoard();
        board.checkForDamageCleanup();
        assertEquals(2, board.getPlayerByName("Player").getHealth());
        assertEquals(2, board.getPlayerByName("Player").getDamageToken());
    }

    @Test
    public void PlayerOnTileWhereLaserShootsGetsDamage() {
        board.addPlayerToBoard(new Vector2(0,0), "Player");
        board.activateLasersOnBoard();
        assertEquals(1, board.getPlayerByName("Player").getDamageToken());
    }

    @Test
    public void OnlyOnePlayerGetsDamageOnSameLaserAxisFromBoardLaser() {
        board.addPlayerToBoard(new Vector2(1,0), "PlayerOne");
        board.addPlayerToBoard(new Vector2(2,0), "PlayerTwo");
        board.activateLasersOnBoard();
        assertEquals(1, board.getPlayerByName("PlayerOne").getDamageToken());
        assertEquals(0, board.getPlayerByName("PlayerTwo").getDamageToken());
    }

    @Test
    public void BothPlayersLoseDamageIfShootingAtEachother() {
        board.addPlayerToBoard(new Vector2(0, 1), "PlayerOne");
        board.addPlayerToBoard(new Vector2(2, 1), "PlayerTwo");
        board.rotatePlayer("PlayerOne", 1);
        board.rotatePlayer("PlayerTwo", -1);
        board.shootPlayerLaser();
        assertEquals(1, board.getPlayerByName("PlayerOne").getDamageToken());
        assertEquals(1, board.getPlayerByName("PlayerTwo").getDamageToken());
    }

    @Test
    public void PlayerReceiveDamageTokenIfShotByOtherPlayer() {
        board.addPlayerToBoard(new Vector2(0, 1), "PlayerOne");
        board.addPlayerToBoard(new Vector2(2, 1), "PlayerTwo");
        board.rotatePlayer("PlayerOne", 1);
        board.shootPlayerLaser();
        assertEquals(0, board.getPlayerByName("PlayerOne").getDamageToken());
        assertEquals(1, board.getPlayerByName("PlayerTwo").getDamageToken());
    }

    @Test
    public void ReceivesTwoDamageIfShotByLaserAndPlayer() {
        board.addPlayerToBoard(new Vector2(0, 2), "PlayerOne");
        board.addPlayerToBoard(new Vector2(0, 1), "PlayerTwo");
        board.shootPlayerLaser();
        board.activateLasersOnBoard();
        assertEquals(2, board.getPlayerByName("PlayerOne").getDamageToken());
        assertEquals(0, board.getPlayerByName("PlayerTwo").getDamageToken());
    }

    @Test
    public void TwoPlayerShootingEachotherOnLaserOnBoardAxis() {
        board.addPlayerToBoard(new Vector2(0, 0), "PlayerOne");
        board.addPlayerToBoard(new Vector2(2, 0), "PlayerTwo");
        board.rotatePlayer("PlayerOne", 1);
        board.rotatePlayer("PlayerTwo", -1);
        board.shootPlayerLaser();
        board.activateLasersOnBoard();
        assertEquals(2, board.getPlayerByName("PlayerOne").getDamageToken());
        assertEquals(1, board.getPlayerByName("PlayerTwo").getDamageToken());
    }
}
