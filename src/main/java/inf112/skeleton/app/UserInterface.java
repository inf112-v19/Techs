package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserInterface implements ApplicationListener {

    
    private int normalFloorSpriteSize = 120;    // size of sprite, ensures tiles spawn with right spacing
    private Texture normalFloorTexture;
    private Texture robotTexture;
    
    private SpriteBatch batch;
    private IBoard board;
    
    private boolean moveRight, moveLeft, moveUp, moveDown;
    
    /* 
     * @param board - The board to be rendered in the GUI
     */
    public UserInterface(IBoard board) {
        this.board = board;
    }
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        normalFloorTexture = new Texture(Gdx.files.internal("assets/StoneTile.png"));
        robotTexture = new Texture(Gdx.files.internal("assets/Robot.png"));
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            moveRight = true;
        } else if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            moveLeft = true;
        } else if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            moveUp = true;
        } else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            moveDown = true;
        }
        
        batch.begin();
        for(int i = 0; i < board.getWidth(); i++) {
            for(int j = 0; j < board.getHeight(); j++) {
                // Draws a normal tile on each square of the grid
                batch.draw(normalFloorTexture, normalFloorSpriteSize * i, normalFloorSpriteSize * j);
                // If there is a player on the tile, draws a player on top of it
                if(board.getTile(i, j) == 1) {
                    batch.draw(robotTexture, normalFloorSpriteSize * i, normalFloorSpriteSize * j);
                }
            }
        }
        batch.end();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
    
    /*
     * Returns the corresponding Direction if the player pressed an arrow key.
     * Returns Direction.STAY if no arrow key is pressed.
     */
    public Direction checkForUserInput() {
        if(moveRight) {
            moveRight = false;
            return Direction.EAST;
        } else if (moveLeft) {
            moveLeft = false;
            return Direction.WEST;
        } else if (moveUp) {
            moveUp = false;
            return Direction.NORTH;
        } 
        else if (moveDown) {
            moveDown = false;
            return Direction.SOUTH;
        } else {
            return Direction.STAY;
        }
    }
    
    // TODO: Find a better place to put this
    public enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST,
        STAY
    }
}
