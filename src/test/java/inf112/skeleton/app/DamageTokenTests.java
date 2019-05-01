package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.logic.BoardLogic;

public class DamageTokenTests {
	
private static Application application;
private TiledMap map;
private BoardLogic board;
private TiledMap map1;
private BoardLogic board1;

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
	        map1 = new TmxMapLoader().load("assets/TestMaps/checkpointMap.tmx");
	        board1 = new BoardLogic(map1);
	        board = new BoardLogic(map);
	    }
	 
	 @Test
	 public void testLaserIncreaseDamage() {
		 board.addPlayerToBoard(new Vector2(11, 14), new Vector2(2, 18), "Player"); //placed on laser tile
		 board.activateLasersOnBoard();
		 assertEquals(1, board.getPlayerByName("Player").getDamageToken());
		 
	 }
	 
	 /**
	 @Test
	 public void testPlayerHaveTwoDamageTokensAfterPitfall() {
		 board.addPlayerToBoard(new Vector2(1, 5), "Player"); 
		 board.getPlayerByName("Player").moveDirection(Direction.NORTH); //Moves into pit
		 board.checkForDamageCleanup();
		 board.checkPlayersLife();
		 System.out.println(board.getPlayerByName("Player").getDamageToken());
		 System.out.println(board.getPlayerByName("Player").getHealth());
		 //assertEquals(2, board.getPlayerByName("Player").getDamageToken());
		 
	 }
	 */
	 
	 @Test 
	 public void looseHealthWhen10DamageTokensAndStartsWith2DamageTokens() {
		 board.addPlayerToBoard(new Vector2(11, 14), new Vector2(2, 18), "Player");
		 for(int i = 0; i < 10; i++) {
			 board.getPlayerByName("Player").addDamageToken();
		 }	 
		 board.getPlayerByName("Player").damageCleanup();
		 assertEquals(2, board.getPlayerByName("Player").getHealth());
		 assertEquals(2, board.getPlayerByName("Player").getDamageToken());
	 }
	 
	 @Test 
	 public void isDestroyedWhen0Health() { 
		 board.addPlayerToBoard(new Vector2(11, 14), new Vector2(2, 18), "Player");
		 for(int i = 3; i > 0; i--) {
			 for(int j = 0; j < 10; j++) {
			 board.getPlayerByName("Player").addDamageToken();
				board.getPlayerByName("Player").damageCleanup();
			 }	 
		 }
		 assertTrue(board.getPlayerByName("Player").checkDestroyedStatus());
	 }
	 
	 

}
