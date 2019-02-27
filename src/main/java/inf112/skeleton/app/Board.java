package inf112.skeleton.app;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Board implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    
    private ArrayList<PlayerToken> playersList;
    private float robotSpriteScale = 28;
    private Sprite robotSprite;
    
    private MovePlayer movePlayerBrain;
    
    @Override
    public void show() {
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, .3f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
        playersList = new ArrayList<PlayerToken>();
        robotSprite = new Sprite (new Texture("assets/GreenRobot.png"));
        movePlayerBrain = new MovePlayer (this, playersList);

        addPlayerToBoard(new Vector2(0,0), "playerOne");
        addPlayerToBoard(new Vector2(1,0), "playerTwo");
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
       
        movePlayerOneAndTwo();
        
        renderer.getBatch().begin();
        
        for(PlayerToken player : playersList) {
            player.draw(renderer.getBatch());
        }
        
        renderer.getBatch().end();
    }
    
    public void addPlayerToBoard(Vector2 startPosition, String playerName) {
        PlayerToken newPlayer = new PlayerToken(robotSprite, playerName, startPosition);
        newPlayer.setSize(robotSpriteScale, robotSpriteScale);
        playersList.add(newPlayer);
        movePlayerBrain.updatePlayersList(playersList);
    }
    public Object cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        TiledMapTileLayer wallLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(wallLayer.getCell(xPos, yPos) == null)
            return null;
        return wallLayer.getCell(xPos, yPos).getTile().getProperties().get(key);
    }
    
    
    /*
     * Temporary, to move two players on the board
     */
    private void movePlayerOneAndTwo() {
        // As proof of concept:
        if(Gdx.input.isKeyJustPressed(Keys.UP)) {
            movePlayerBrain.movePlayer(Direction.NORTH, playersList.get(0));
        } else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            movePlayerBrain.movePlayer(Direction.SOUTH, playersList.get(0));
        } else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            movePlayerBrain.movePlayer(Direction.EAST, playersList.get(0));
        }else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            movePlayerBrain.movePlayer(Direction.WEST, playersList.get(0));
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.W)) {
            movePlayerBrain.movePlayer(Direction.NORTH, playersList.get(1));
        } else if(Gdx.input.isKeyJustPressed(Keys.S)) {
            movePlayerBrain.movePlayer(Direction.SOUTH, playersList.get(1));
        } else if(Gdx.input.isKeyJustPressed(Keys.D)) {
            movePlayerBrain.movePlayer(Direction.EAST, playersList.get(1));
        }else if(Gdx.input.isKeyJustPressed(Keys.A)) {
            movePlayerBrain.movePlayer(Direction.WEST, playersList.get(1));
        }
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
