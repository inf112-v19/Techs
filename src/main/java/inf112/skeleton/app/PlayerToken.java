package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayerToken extends Sprite {

    private Vector2 movementVelocity = new Vector2();
    private Vector2 position;
    private float speed = 120;
    private float tileScale = 96;
    private String playerName;
    private Direction facingDirection;
    
    private boolean movingNorth = true;
    private boolean movingSouth = true;
    private boolean movingEast = true;
    private boolean movingWest = true;
    
    private boolean rotatingLeft;
    private boolean rotatingRight;
    private int targetRotation;
    private float rotateSpeed = 400;
   
    public PlayerToken(Sprite sprite, String playerName, Vector2 startPosition, float spriteScale) {
        super(sprite);
        facingDirection = Direction.SOUTH;
        this.playerName = playerName;
        
        position = startPosition;
        setOrigin(spriteScale/2, spriteScale/2);
        
        setXPositionOnBoard();
        setYPositionOnBoard();
    }
    
    /*
     * Rotates player 90 degrees clockwise for each numberOfTimes. 90 degrees counterclockwise when numberOfTimes is negative.
     */
    public void rotatePlayer(int numberOfTimes) {
        int directionSum = (((facingDirection.ordinal() + numberOfTimes) % 4) + 4) % 4;
        facingDirection = Direction.values()[directionSum];
        
        targetRotation = (int) (this.getRotation() - (90 * numberOfTimes));
        if(numberOfTimes < 0) {
            rotatingRight = true;    
        } else {
            rotatingLeft = true;
        }
    }
    
    public String getName() {
        return playerName;
    }
    
    public Direction getDirection() {
        return facingDirection;
    }
    
    public Vector2 getVector2Position() {
        return position;
    }
    
    public int getXPosition() {
        return (int) position.x;
    }

    public int getYPosition() {
        return (int) position.y;
    }
    
    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    
    private void setYPositionOnBoard() {
        setY(position.y * tileScale);
    }
    
    private void setXPositionOnBoard() {
        setX(position.x * tileScale);
    } 
    
    private void animateXPositionOnBoard(float delta) {
        setX(getX() + movementVelocity.x * delta);
    }
    
    private void animateYPositionOnBoard(float delta) {
        setY(getY() + movementVelocity.y * delta);
    }

    public void update(float delta) {
        animateXPositionOnBoard(delta);
        animateYPositionOnBoard(delta);

        if(movingNorth) {
            // position.y * tileScale : translation of player's position to correct number of pixels
            if(getY() >= position.y * tileScale) {
                movingNorth = false;
                movementVelocity.y = 0;             
                setYPositionOnBoard();       // "hard sets" the position to avoid inaccuracies in position
            } else {
                movementVelocity.y = speed;
            }
        } else if (movingSouth) {   
            if(getY() <= position.y  * tileScale) {
                movingSouth = false;
                setYPositionOnBoard();
                movementVelocity.y = 0;
            } else {
                movementVelocity.y = -speed;
            }
        } else {
            movementVelocity.y = 0;
        }
        
        if(movingEast) {
            if(getX() >= position.x * tileScale) {
                movingEast = false;
                setXPositionOnBoard();
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = speed;
            }
        } else if (movingWest) {
            if(getX() <= position.x * tileScale) {
                movingWest = false;
                setXPositionOnBoard();
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = -speed;
            }
        } else {
            movementVelocity.x = 0;
        }
        
        if(rotatingLeft) {
            if(this.getRotation() > targetRotation) {
                this.rotate(-rotateSpeed * delta);
            } else {
                this.setRotation(targetRotation);
                rotatingLeft = false;
            }
        } else if(rotatingRight) {
            if(this.getRotation() < targetRotation) {
                this.rotate(rotateSpeed * delta);
            } else {
                this.setRotation(targetRotation);
                rotatingRight = false;
            }
        }
    }
    
    public void moveDirection(Direction dir) {
        switch(dir) {
        case EAST:
            moveEast();
            break;
        case NORTH:
            moveNorth();
            break;
        case SOUTH:
            moveSouth();
            break;
        case WEST:
            moveWest();
            break;
        default:
            break;
        }
    }
    
    private void moveNorth() {
        movingNorth = true;
        position.y += 1;
    }
    
    private void moveSouth() {
        movingSouth = true;
        position.y -= 1;
    }
    
    private void moveEast() {
        movingEast = true;
        position.x += 1;
    }
    
    private void moveWest() {
        movingWest = true;
        position.x -= 1;
    }
}
