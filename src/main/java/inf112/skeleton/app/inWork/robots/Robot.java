package inf112.skeleton.app.inWork.robots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.Direction;

public class Robot extends Sprite {

    private static final float SPEED = 120;
    private static final float tileScale = 28.7f;

    private Vector3 position;
    private Vector3 velocity;
    private Direction facingDirection;
    private Animation<TextureRegion> greenRobot;
    private String robot;

    private boolean movingNorth = true;
    private boolean movingSouth = true;
    private boolean movingEast = true;
    private boolean movingWest = true;

    public Robot(String robot, int x, int y) {
        this.robot = robot;
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        greenRobot = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/AnimatedGreenRobot.gif").read());
    }

    public void update(float delta) {
        animateXPositionOnBoard(delta);
        animateYPositionOnBoard(delta);

        if(movingNorth) {
            // position.y * tileScale : translation of player's position to correct number of pixels
            if(getY() >= position.y * tileScale) {
                movingNorth = false;
                velocity.y = 0;
                setYPositionOnBoard();       // "hard sets" the position to avoid inaccuracies in position
            } else {
                velocity.y = SPEED;
            }
        } else if (movingSouth) {
            if(getY() <= position.y * tileScale) {
                movingSouth = false;
                setYPositionOnBoard();
                velocity.y = 0;
            } else {
                velocity.y = -SPEED;
            }
        } else {
            velocity.y = 0;
        }

        if(movingEast) {
            if(getX() >= position.x * tileScale) {
                movingEast = false;
                setXPositionOnBoard();
                velocity.x = 0;
            } else {
                velocity.x = SPEED;
            }
        } else if (movingWest) {
            if(getX() <= position.x * tileScale) {
                movingWest = false;
                setXPositionOnBoard();
                velocity.x = 0;
            } else {
                velocity.x = -SPEED;
            }
        } else {
            velocity.x = 0;
        }
    }

    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
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

    public boolean isAnimating() {
        return movingWest || movingEast || movingNorth || movingSouth;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Direction getDirection() {
        return facingDirection;
    }

    public String getRobot() {
        return robot;
    }

    public int getXPosition() {
        return (int) position.x;
    }

    public int getYPosition() {
        return (int) position.y;
    }

    private void setXPositionOnBoard() {
        setX(position.x * tileScale);
    }

    private void setYPositionOnBoard() {
        setY(position.y * tileScale);
    }

    private void animateXPositionOnBoard(float delta) {
        setX(getX() + velocity.x * delta);
    }

    private void animateYPositionOnBoard(float delta) {
        setY(getY() + velocity.y * delta);
    }

    public Animation<TextureRegion> getGreenRobot() {
        return greenRobot;
    }
}
