package inf112.skeleton.app.inWork;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

public class RobotToken extends Sprite {

    private static final float ROTATE_SPEED = 400;
    private static final float MOVEMENT_SPEED = 360;
    private static final int FRAME_COL = 8;
    private static final int FRAME_ROW = 2;
    private static final float TILE_SCALE = 96;

    // Variables needed for movement, direction and position
    private Vector2 movementVelocity = new Vector2();
    private Vector2 position;
    private Direction facingDirection;
    private boolean movingNorth = true;
    private boolean movingSouth = true;
    private boolean movingEast = true;
    private boolean movingWest = true;

    private boolean rotatingLeft;
    private boolean rotatingRight;
    private int targetRotation;


    // Variables needed for animated sprites
    TextureRegion[] animationFrames;
    Texture spriteSheet;
    String playerName;

    public RobotToken(String givenName, String textureSpriteSheet, Vector2 startPosition) {
        playerName = givenName;
        position = startPosition;
        facingDirection = Direction.SOUTH;

        // All regarding spritesheet and getting the frames correctly is done here.
        spriteSheet = new Texture(textureSpriteSheet);
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / FRAME_COL, spriteSheet.getHeight() / FRAME_ROW);
        animationFrames = new TextureRegion[(FRAME_ROW * FRAME_COL) - 5];
        int index = 0;
        for (int i = 0; i < FRAME_ROW; i++) {
            for (int j = 0; j < FRAME_COL; j++) {
                if (i == 1 && j >= 3) {
                    continue;
                }
                animationFrames[index++] = tmp[i][j];
            }
        }
    }

    private void animateXPositionOnBoard(float delta) {
        setX(getX() + movementVelocity.x * delta);
    }

    private void animateYPositionOnBoard(float delta) {
        setY(getY() + movementVelocity.y * delta);
    }

    public TextureRegion[] getAnimationFrames() {
        return animationFrames;
    }

    public Direction getDirection() {
        return facingDirection;
    }

    public String getName() {
        return playerName;
    }

    public int getXPosition() {
        return (int) position.x;
    }

    public int getYPosition() {
        return (int) position.y;
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


    private void moveEast() {
        movingEast = true;
        position.x += 1;
    }

    private void moveNorth() {
        movingNorth = true;
        position.y += 1;
    }

    private void moveSouth() {
        movingSouth = true;
        position.y -= 1;
    }

    private void moveWest() {
        movingWest = true;
        position.x -= 1;
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

    private void setYPositionOnBoard() {
        setY(position.y * TILE_SCALE);
    }

    private void setXPositionOnBoard() {
        setX(position.x * TILE_SCALE);
    }

    public void update(float delta) {
        animateXPositionOnBoard(delta);
        animateYPositionOnBoard(delta);

        if(movingNorth) {
            // position.y * TILE_SCALE : translation of player's position to correct number of pixels
            if(getY() >= position.y * TILE_SCALE) {
                movingNorth = false;
                movementVelocity.y = 0;
                setYPositionOnBoard();       // "hard sets" the position to avoid inaccuracies in position
            } else {
                movementVelocity.y = MOVEMENT_SPEED;
            }
        } else if (movingSouth) {
            if(getY() <= position.y  * TILE_SCALE) {
                movingSouth = false;
                setYPositionOnBoard();
                movementVelocity.y = 0;
            } else {
                movementVelocity.y = -MOVEMENT_SPEED;
            }
        } else {
            movementVelocity.y = 0;
        }

        if(movingEast) {
            if(getX() >= position.x * TILE_SCALE) {
                movingEast = false;
                setXPositionOnBoard();
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = MOVEMENT_SPEED;
            }
        } else if (movingWest) {
            if(getX() <= position.x * TILE_SCALE) {
                movingWest = false;
                setXPositionOnBoard();
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = -MOVEMENT_SPEED;
            }
        } else {
            movementVelocity.x = 0;
        }

        if(rotatingLeft) {
            if(this.getRotation() > targetRotation) {
                this.rotate(-ROTATE_SPEED * delta);
            } else {
                this.setRotation(targetRotation);
                rotatingLeft = false;
            }
        } else if(rotatingRight) {
            if(this.getRotation() < targetRotation) {
                this.rotate(ROTATE_SPEED * delta);
            } else {
                this.setRotation(targetRotation);
                rotatingRight = false;
            }
        }
    }
}
