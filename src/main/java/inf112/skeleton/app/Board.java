package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import inf112.skeleton.app.inWork.BoardGame;

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
    
    @Override
    public void show() {
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, RoboRally.WIDTH, RoboRally.HEIGHT);
        
        addPlayerToBoard(new Vector2(0,0), "playerOne");
        addPlayerToBoard(new Vector2(1,0), "playerTwo");
    }

    @Override
    public void render(float v) {
        renderer.setView(camera);
        renderer.render();
       
        movePlayerOneAndTwo();
        
        PlayerToken playerOne = getPlayerByName("playerOne");
        
        renderer.getBatch().begin();
        
        for (PlayerToken player : playersList) {
            player.draw(renderer.getBatch());
        }
        renderer.getBatch().end();

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
            camera.translate(0,MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0,-MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-MOVE_SPEED,0,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(MOVE_SPEED, 0, 0);
            camera.update();
        }
    }
    
    public void addPlayerToBoard(Vector2 startPosition, String playerName) {
        PlayerToken newPlayer = new PlayerToken(robotSprite, playerName, startPosition);
        newPlayer.setSize(robotSpriteScale, robotSpriteScale);
        playersList.add(newPlayer);
    }
    
    public boolean movePlayer(Direction directionToMove, String playerName) {
        PlayerToken playerToMove = getPlayerByName(playerName);
        int xPos = playerToMove.getXPosition();
        int yPos = playerToMove.getYPosition();
        
        switch(directionToMove) {
        case EAST:
            if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallEast") != null 
                || cellContainsLayerWithKey(xPos + 1, yPos, "Wall", "wallWest") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos + 1, yPos, playerName, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.EAST);            
            return true;
            
        case NORTH:
            if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallNorth") != null || 
                cellContainsLayerWithKey(xPos, yPos + 1, "Wall", "wallSouth") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos + 1, playerName, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.NORTH);
            return true;
            
        case SOUTH:
            if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallSouth") != null || 
                cellContainsLayerWithKey(xPos, yPos - 1, "Wall", "wallNorth") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos - 1, playerName, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.SOUTH);
            return true;
            
        case WEST:
            if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallWest") != null || 
                cellContainsLayerWithKey(xPos - 1, yPos, "Wall", "wallEast") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos - 1, yPos, playerName, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.WEST);
            return true;
            
        default:
            break;
        
        }        
        return false;
    }
    
    /*
     * Moves player standing at (xPos, yPos) to the given direction
     */
    private boolean moveOtherPlayers(int xPos, int yPos, String playerToMove, Direction directionToMove) {
        for(PlayerToken player : playersList) {
            if(player.getXPosition() == xPos && player.getYPosition() == yPos) {
                return movePlayer(directionToMove, player.getName());
            }
        }
        return true;
    }

    private Object cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
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
            movePlayer(Direction.NORTH, "playerOne");
        } else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            movePlayer(Direction.SOUTH, "playerOne");
        } else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            movePlayer(Direction.EAST, "playerOne");
        }else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            movePlayer(Direction.WEST, "playerOne");
        }
        /*
        if(Gdx.input.isKeyJustPressed(Keys.W)) {
            movePlayer(Direction.NORTH, "playerTwo");
        } else if(Gdx.input.isKeyJustPressed(Keys.S)) {
            movePlayer(Direction.SOUTH, "playerTwo");
        } else if(Gdx.input.isKeyJustPressed(Keys.D)) {
            movePlayer(Direction.EAST, "playerTwo");
        }else if(Gdx.input.isKeyJustPressed(Keys.A)) {
            movePlayer(Direction.WEST, "playerTwo");
        }
        */
    }
    
    private PlayerToken getPlayerByName(String playerName) {
        for(PlayerToken player : playersList) {
            if(player.getName().equals(playerName))
                return player;
        }
        throw new NoSuchElementException("There is no player in the grid by the name " + playerName);
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
