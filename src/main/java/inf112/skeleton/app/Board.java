package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
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

    //Card handling
    private TextureAtlas atlasCards;
    private Deck deck = new Deck();

    private SpriteBatch spriteBatchCards;
    private Sprite cardToSelect0;
    private Sprite cardToSelect1;
    private Sprite cardToSelect2;
    private Sprite cardToSelect3;
    private Sprite cardToSelect4;
    private Sprite cardToSelect5;
    private Sprite cardToSelect6;
    private Sprite cardToSelect7;
    private Sprite cardToSelect8;

    private Sprite number1;
    private Sprite number2;
    private Sprite number3;
    private Sprite number4;
    private Sprite number5;

    private ArrayList<Integer> selectedCards;

    public Board(RoboRally game) {
        this.game = game;
        playersList = new ArrayList<PlayerToken>();
        robotSprite = new Sprite (new Texture("assets/GreenRobot.png"));

        //cards
        atlasCards = new TextureAtlas("assets/ProgramSheet/ProgramCardsTexturePack/cardsTexture.atlas");
        spriteBatchCards = new SpriteBatch();

        selectedCards = new ArrayList<>();
        createNewCards();
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


        addPlayerToBoard(new Vector2(0,0), "playerOne");
        addPlayerToBoard(new Vector2(1,0), "playerTwo");
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

        int centerOfScreen = Gdx.graphics.getWidth()/2;
        spriteBatchCards.begin();
        spriteBatchCards.draw(cardToSelect0,centerOfScreen - 360,0,94,130);
        spriteBatchCards.draw(cardToSelect1,centerOfScreen - 270,0,94,130);
        spriteBatchCards.draw(cardToSelect2,centerOfScreen - 180,0,94,130);
        spriteBatchCards.draw(cardToSelect3,centerOfScreen - 90,0,94,130);
        spriteBatchCards.draw(cardToSelect4,centerOfScreen,0,94,130);
        spriteBatchCards.draw(cardToSelect5,centerOfScreen + 90,0,94,130);
        spriteBatchCards.draw(cardToSelect6,centerOfScreen + 180,0,94,130);
        spriteBatchCards.draw(cardToSelect7,centerOfScreen + 270,0,94,130);
        spriteBatchCards.draw(cardToSelect8,centerOfScreen + 360,0,94,130);
        if (!selectedCards.isEmpty())

        spriteBatchCards.end();



        /*
        while (!fiveCardsSelected){

        }
        */
        if (Gdx.input.isKeyPressed(Keys.NUM_1)) {
            selectedCards.add(0);
            //System.out.println("card1");
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
            selectedCards.add(1);
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
            selectedCards.add(2);
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
            selectedCards.add(3);
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_5)) {
            selectedCards.add(4);
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_6)) {
            selectedCards.add(5);
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_7)) {
            selectedCards.add(6);
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_8)) {
            selectedCards.add(7);
        }

        if (Gdx.input.isKeyPressed(Keys.NUM_9)) {
            selectedCards.add(8);
        }


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

    public void createNewCards(){
        cardToSelect0 = atlasCards.createSprite("10 U Turn", -1);
        cardToSelect1 = atlasCards.createSprite("320 Rotate Right", -1);
        cardToSelect2 = atlasCards.createSprite("280 Rotate Right", -1);
        cardToSelect3 = atlasCards.createSprite("520 Move 1", -1);
        cardToSelect4 = atlasCards.createSprite("640 Move 1", -1);
        cardToSelect5 = atlasCards.createSprite("710 Move 2", -1);
        cardToSelect6 = atlasCards.createSprite("140 Rotate Right", -1);
        cardToSelect7 = atlasCards.createSprite("510 Move 1", -1);
        cardToSelect8 = atlasCards.createSprite("690 Move 2", -1);
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
    
    public boolean movePlayer(String name, Direction directionToMove) {
        return movePlayerBrain.movePlayer(directionToMove, getPlayerByName(name));
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
