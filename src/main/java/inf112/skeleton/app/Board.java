package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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
    private IBoardFeature moveConveyorBelts;
    
    @Override
    public void show() {
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, .3f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
        playersList = new ArrayList<PlayerToken>();
        robotSprite = new Sprite (new Texture("assets/GreenRobot.png"));
        movePlayerBrain = new MovePlayer (this, playersList);
        moveConveyorBelts = new MoveConveyorBelts(this, playersList);

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
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            playersList.get(0).rotatePlayer(1);
        }
        if(playersList.get(0).isRotating() != playersList.get(0).isMoving()) {
            if(playersList.get(0).isRotating()) {
                System.out.println("Still rotating");
            } else {
                System.out.println("Still moving");
            }
        }
        
        renderer.getBatch().begin();
        
        for(PlayerToken player : playersList) {
            player.draw(renderer.getBatch());
        }
        
        renderer.getBatch().end();
    }
    
    public void addPlayerToBoard(Vector2 startPosition, String playerName) {
        PlayerToken newPlayer = new PlayerToken(robotSprite, playerName, startPosition, robotSpriteScale);
        newPlayer.setSize(robotSpriteScale, robotSpriteScale);
        playersList.add(newPlayer);
        movePlayerBrain.updatePlayersList(playersList);
    }
    
    /*
     * Checks if tile at (xPos, yPos) has a property named key in the specified layer 
     */
    public boolean cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(tileLayer.getCell(xPos, yPos) == null)
            return false;
        return tileLayer.getCell(xPos, yPos).getTile().getProperties().containsKey(key);
    }
    
    /*
     * Checks if tile at (xPos, yPos) is in the specified layer
     */
    public boolean cellContainsLayer(int xPos, int yPos, String layer) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        return tileLayer.getCell(xPos, yPos) != null;
    }
    
    public boolean movePlayer(String name, Direction directionToMove) {
        return movePlayerBrain.movePlayer(directionToMove, getPlayerTokenByName(name));
    }
    
    /*
     * Return PlayerToken of corresponding playerName, return null if no such PlayerToken exists
     */
    private PlayerToken getPlayerTokenByName(String playerName) {
        for(PlayerToken player : playersList) {
            if(player.getName().equals(playerName))
                return player;
        }
        throw new NoSuchElementException("Player by name " + playerName + " has no token");
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
