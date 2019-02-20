package inf112.skeleton.app;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TestBoard implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    
    private ArrayList<TestPlayer> playersList;

    public void addPlayer(TestPlayer player) {
        playersList.add(player);
    }
    
    @Override
    public void show() {
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, .3f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
        
        playersList = new ArrayList<TestPlayer>();
        
        TestPlayer playerOne = new TestPlayer(new Sprite(new Texture("assets/GreenRobot.png")), "playerOne");
        playerOne.setSize(28, 28);
        playerOne.setPosition(0, 0);
        addPlayer(playerOne);
        
        
        TestPlayer playerTwo = new TestPlayer(new Sprite(new Texture("assets/GreenRobot.png")), "playerTwo");
        playerTwo.setSize(28, 28);
        playerTwo.setPosition(0,0);
        addPlayer(playerTwo);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        
        if(Gdx.input.isKeyJustPressed(Keys.UP)) {
            playersList.get(0).moveNorth();
        } else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            playersList.get(0).moveSouth();
        } else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            playersList.get(0).moveEast();
        }else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            playersList.get(0).moveWest();
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.W)) {
            playersList.get(1).moveNorth();
        } else if(Gdx.input.isKeyJustPressed(Keys.S)) {
            playersList.get(1).moveSouth();
        } else if(Gdx.input.isKeyJustPressed(Keys.D)) {
            playersList.get(1).moveEast();
        }else if(Gdx.input.isKeyJustPressed(Keys.A)) {
            playersList.get(1).moveWest();
        }
        
        renderer.getBatch().begin();
        for(TestPlayer player : playersList) {
            player.draw(renderer.getBatch());
        }
        renderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        map.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
