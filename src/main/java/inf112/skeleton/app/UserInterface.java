package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserInterface implements ApplicationListener {

    
    private int normalFloorSpriteSize = 120;    // size of sprite, ensures tiles spawn with right spacing
    private Texture normalFloorTexture;
    private Texture robotTexture;
    
    private SpriteBatch batch;
    private IBoard board;
    
    public UserInterface(IBoard board) {
        this.board = board;
    }
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        normalFloorTexture = new Texture(Gdx.files.internal("assets/StoneTile.png"));
        robotTexture = new Texture(Gdx.files.internal("assets/GreenRobot.png"));
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render() {
        batch.begin();
        for(int i = 0; i < board.getWidth(); i++) {
            for(int j = 0; j < board.getHeight(); j++) {
                // Draws a normal tile on each square of the grid
                batch.draw(normalFloorTexture, normalFloorSpriteSize * i, normalFloorSpriteSize * j);
                // If there is a player on the tile, draws a player ontop of it
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
    
}
