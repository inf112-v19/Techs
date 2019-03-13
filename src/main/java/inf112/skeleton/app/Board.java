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

    //Card handling
    private TextureAtlas atlasCards;
    private ProgramCardDeck deck = new ProgramCardDeck();
    private ArrayList<IProgramCard> cardsToSelect;

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

    private Texture number1;
    private Texture number2;
    private Texture number3;
    private Texture number4;
    private Texture number5;

    //shows order of selected cards
    private ArrayList<Integer> numberXPos;
    private ArrayList<Integer> numberYPos;
    private boolean[] hasBeenSelected = new boolean[9];
    private int numCardsSelected = 0;
    
    public static final float ZOOM_SPEED = 0.03f;
    public static final float MOVE_SPEED = 16;
    public static final float ANIMATION_SPEED = 0.08f;
    public static final int ROBOT_WIDTH_PIXEL = 64;
    public static final int ROBOT_HEIGHT_PIXEL = 64;
    public static final int ROBOT_WIDTH = 96;
    public static final int ROBOT_HEIGHT = 96;

    RoboRally game;
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    
    private float robotSpriteScale = 96;
    private Sprite robotSprite;
    private BoardLogic boardLogic;

    public Board(RoboRally game) {
        this.game = game;
        robotSprite = new Sprite (new Texture("assets/GreenRobot.png"));
        
        // Card functions
        atlasCards = new TextureAtlas("assets/ProgramSheet/ProgramCardsTexturePack/cardsTexture.atlas");
        spriteBatchCards = new SpriteBatch();
        createNewCards();
        setStandardNumberPosition();
    }
        
    @Override
    public void show() {
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        camera.setToOrtho(false, RoboRally.WIDTH, RoboRally.HEIGHT);
        boardLogic = new BoardLogic(robotSprite, robotSpriteScale, map);
    }

    @Override
    public void render(float v) {
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        
        for (PlayerToken player : boardLogic.getPlayersList()) {
            player.draw(renderer.getBatch());
        }
        renderer.getBatch().end();

        if(Gdx.input.isKeyJustPressed(Keys.A))
            addPlayerToBoard(new Vector2(0,0), "Test");
        
        int centerOfScreen = Gdx.graphics.getWidth()/2;

        //shows 9 cards player can select
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

        //shows numbers for order of selected cards
        spriteBatchCards.draw(number1, numberXPos.get(0), numberYPos.get(0),35,35);
        spriteBatchCards.draw(number2, numberXPos.get(1), numberYPos.get(1),35,35);
        spriteBatchCards.draw(number3, numberXPos.get(2), numberYPos.get(2),35,35);
        spriteBatchCards.draw(number4, numberXPos.get(3), numberYPos.get(3),35,35);
        spriteBatchCards.draw(number5, numberXPos.get(4), numberYPos.get(4),35,35);
        spriteBatchCards.end();

        //these if-statements handles which cards has been selected på user
        if (numCardsSelected < 5) {
            if (Gdx.input.isKeyPressed(Keys.NUM_1)) {
                if (!hasBeenSelected[0]) {
                    numberXPos.set(numCardsSelected, centerOfScreen - 330);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[0] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
                if (!hasBeenSelected[1]) {
                    numberXPos.set(numCardsSelected, centerOfScreen - 240);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[1] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
                if (!hasBeenSelected[2]) {
                    numberXPos.set(numCardsSelected, centerOfScreen - 150);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[2] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
                if (!hasBeenSelected[3]) {
                    numberXPos.set(numCardsSelected, centerOfScreen - 60);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[3] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_5)) {
                if (!hasBeenSelected[4]) {
                    numberXPos.set(numCardsSelected, centerOfScreen + 30);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[4] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_6)) {
                if (!hasBeenSelected[5]) {
                    numberXPos.set(numCardsSelected, centerOfScreen + 120);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[5] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_7)) {
                if (!hasBeenSelected[6]) {
                    numberXPos.set(numCardsSelected, centerOfScreen + 210);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[6] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_8)) {
                if (!hasBeenSelected[7]) {
                    numberXPos.set(numCardsSelected, centerOfScreen + 300);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[7] = true;
                    numCardsSelected++;
                }
            }

            if (Gdx.input.isKeyPressed(Keys.NUM_9)) {
                if (!hasBeenSelected[8]) {
                    numberXPos.set(numCardsSelected, centerOfScreen + 390);
                    numberYPos.set(numCardsSelected, 10);
                    hasBeenSelected[8] = true;
                    numCardsSelected++;
                }
            }
            /*
            if (numCardsSelected == 5){
                //Show ENTER right side in flashing green
            }
            */
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

    //puts all numbers in right corner
    public void setStandardNumberPosition(){
        number1 = new Texture("assets/ProgramSheet/numbersInCircle/numberOne.png");
        number2 = new Texture("assets/ProgramSheet/numbersInCircle/numberTwo.png");
        number3 = new Texture("assets/ProgramSheet/numbersInCircle/numberThree.png");
        number4 = new Texture("assets/ProgramSheet/numbersInCircle/numberFour.png");
        number5 = new Texture("assets/ProgramSheet/numbersInCircle/numberFive.png");

        this.numberXPos = new ArrayList<>();
        this.numberYPos = new ArrayList<>();

        int rightOfScreen = Gdx.graphics.getWidth() - 35;
        for (int i = 0; i < 5; i++) {
            numberXPos.add(rightOfScreen);
        }

        numberYPos.add(160);
        numberYPos.add(120);
        numberYPos.add(80);
        numberYPos.add(40);
        numberYPos.add(0);
    }

    //selects 9 random cards from deck and puts them in 
    public void createNewCards(){
        cardsToSelect = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            cardsToSelect.add(deck.getTopCard());

        cardToSelect0 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect1 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect2 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect3 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect4 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect5 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect6 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect7 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
        cardToSelect8 = atlasCards.createSprite(deck.getTopCard().toString(), -1);
    }
    
    
    
    /*
     * Checks if tile at (xPos, yPos) is in the specified layer
     */
    public boolean cellContainsLayer(int xPos, int yPos, String layer) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        return tileLayer.getCell(xPos, yPos) != null;
    }
    
    public boolean movePlayer(String name, Direction directionToMove) {
        return boardLogic.movePlayer(name, directionToMove);
    }
    
    public void moveConveyorBelts() {
        boardLogic.moveConveyorBelts();
    }

    public void rotatePlayer(String name, int numberOfTimes) {
        boardLogic.rotatePlayer(name, numberOfTimes);
    }
    
    public boolean cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        return boardLogic.cellContainsLayerWithKey(xPos, yPos, layer, key);
    }

    public void addPlayerToBoard(Vector2 startPosition, String playerName) {
        boardLogic.addPlayerToBoard(startPosition, playerName);
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
