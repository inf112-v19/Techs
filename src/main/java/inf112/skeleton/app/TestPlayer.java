package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TestPlayer extends Sprite {

    private Vector2 movementVelocity = new Vector2();
    private Vector2 position;
    private float speed = 120;
    private float tileScale = 28.7f;
    private String playerName;
    
    private boolean movingNorth = false;
    private boolean movingSouth = false;
    private boolean movingEast = false;
    private boolean movingWest = false;
   
    public TestPlayer(Sprite sprite, String playerName) {
        super(sprite);
        position = new Vector2(0,0);
        this.playerName = playerName;
    }
    
    public String getName() {
        return playerName;
    }

    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta) {
        if(movingNorth) {
            // position.y * tileScale : translation of player's position to correct number of pixels
            if(getY() >= position.y * tileScale) {
                movingNorth = false;
                movementVelocity.y = 0;             
                setY(position.y * tileScale);       // "hard sets" the position to avoid inaccuracies in position
            } else {
                movementVelocity.y = speed;
            }
        } else if (movingSouth) {   
            if(getY() < position.y  * tileScale) {
                movingSouth = false;
                setY(position.y * tileScale);
                movementVelocity.y = 0;
            } else {
                movementVelocity.y = -speed;
            }
        } else {
            movementVelocity.y = 0;
        }
        
        if(movingEast) {
            if(getX() > position.x * tileScale) {
                movingEast = false;
                setX(position.x * tileScale);
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = speed;
            }
        } else if (movingWest) {
            if(getX() < position.x * tileScale) {
                movingWest = false;
                setX(position.x * tileScale);
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = -speed;
            }
        } else {
            movementVelocity.x = 0;
        }
        
        setX(getX() + movementVelocity.x * delta);
        setY(getY() + movementVelocity.y * delta);
    }
    
    public void moveNorth() {
        movingNorth = true;
        position.y += 1;
    }
    
    public void moveSouth() {
        movingSouth = true;
        position.y -= 1;
    }
    
    public void moveEast() {
        movingEast = true;
        position.x += 1;
    }
    
    public void moveWest() {
        movingWest = true;
        position.x -= 1;
    }
    
    public boolean isAnimating() {
        return movingWest || movingEast || movingNorth || movingSouth;
    }
}
