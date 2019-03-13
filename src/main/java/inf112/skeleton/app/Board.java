package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Board implements Screen {

    public static final float ZOOM_SPEED = 0.03f;
    public static final float MOVE_SPEED = 16;
    public static final float ANIMATION_SPEED = 0.08f;
    public static final int ROBOT_WIDTH_PIXEL = 64;
    public static final int ROBOT_HEIGHT_PIXEL = 64;
    public static final int ROBOT_WIDTH = 96;
    public static final int ROBOT_HEIGHT = 96;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    
    private ArrayList<PlayerToken> playersList;
    private float robotSpriteScale = 96;
    private Sprite robotSprite;

    RoboRally game;


    public Board(RoboRally game) {
        this.game = game;
        playersList = new ArrayList<PlayerToken>();
        robotSprite = new Sprite (new Texture("assets/GreenRobot.png"));
    }
    
    private MovePlayer movePlayerBrain;
    private IBoardFeature moveConveyorBelts;
    
    @Override
    public void show() {
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        camera.setToOrtho(false, RoboRally.WIDTH, RoboRally.HEIGHT);
        
        playersList = new ArrayList<PlayerToken>();
        robotSprite = new Sprite (new Texture("assets/GreenRobot.png"));
        movePlayerBrain = new MovePlayer (this, playersList);
        moveConveyorBelts = new MoveConveyorBelts(this, playersList);
    }

    @Override
    public void render(float v) {
        renderer.setView(camera);
        renderer.render();

        movePlayerOneAndTwo();

        renderer.getBatch().begin();

        for (PlayerToken player : playersList) {
            player.draw(renderer.getBatch());
        }
        renderer.getBatch().end();
            /*
            if (numCardsSelected == 5){
                //Show ENTER right side in flashing green
            }
            */



                /*
        The code here handles the zoom- and camera movement functions. The drag-functionality might be removed if conflict arise when using
        the mouse button to click on program cards. The buttons used are WASD for camera-movement and E/Q for ZoomIn/ZoomOut.
         */
        if (Gdx.input.isTouched()) {
            camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom += ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom -= ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0, MOVE_SPEED, 0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0, -MOVE_SPEED, 0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-MOVE_SPEED, 0, 0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(MOVE_SPEED, 0, 0);
            camera.update();
        }
    }

    /*
     * Adds player to the board at specified position
     */
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
    
    /*
     * Moves the player one unit in the direction specified 
     */
    public boolean movePlayer(String name, Direction directionToMove) {
        return movePlayerBrain.movePlayer(directionToMove, getPlayerByName(name));
    }
    
    /*
     * Rotates player 90 degrees clockwise for each numberOfTimes. 90 degrees counterclockwise when numberOfTimes is negative.
     */
    public void rotatePlayer(String name, int numberOfTimes) {
        PlayerToken player = getPlayerByName(name);
        player.rotatePlayer(numberOfTimes);
    }

    /*
     * Moves all players standing on conveyer belts
     */
    public void moveConveyorBelts() {
        moveConveyorBelts.processFeature();
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
        /*
        if(Gdx.input.isKeyJustPressed(Keys.W)) {
            movePlayerBrain.movePlayer(Direction.NORTH, playersList.get(1));
        } else if(Gdx.input.isKeyJustPressed(Keys.S)) {
            movePlayerBrain.movePlayer(Direction.SOUTH, playersList.get(1));
        } else if(Gdx.input.isKeyJustPressed(Keys.D)) {
            movePlayerBrain.movePlayer(Direction.EAST, playersList.get(1));
        }else if(Gdx.input.isKeyJustPressed(Keys.A)) {

            movePlayerBrain.movePlayer(Direction.WEST, playersList.get(1));

        }
        */
    }
    
    public Direction getPlayerRotation(String name) {
        PlayerToken player = getPlayerByName(name);
        return player.getDirection();
    }
    
    public Vector2 getPlayerLocation(String name) {
        PlayerToken player = getPlayerByName(name);
        return new Vector2 (player.getXPosition(), player.getYPosition());
    }
    
    private PlayerToken getPlayerByName(String playerName) {
        for(PlayerToken player : playersList) {
            if(player.getName().equals(playerName))
                return player;

        }
        return null;
    }

    @Override
    public void resize(int width, int height) {

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
