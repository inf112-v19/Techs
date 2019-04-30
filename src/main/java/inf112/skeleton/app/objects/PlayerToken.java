package inf112.skeleton.app.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.Direction;

public class PlayerToken extends Sprite {

	private static final float ROTATE_SPEED = 400;
	private static final float MOVEMENT_SPEED = 360;
	private static final float ANIMATION_SPEED = 0.06f;
	private static final int FRAME_COL = 8;
	private static final int FRAME_ROW = 2;
	private static final float TILE_SCALE = 96;
	// Variables needed for movement, direction and position
	private Vector2 movementVelocity = new Vector2();
	private Vector2 backupPosition;
	private Vector2 position;
	private Direction facingDirection;
	private boolean movingNorth = true;
	private boolean movingSouth = true;
	private boolean movingEast = true;
	private boolean movingWest = true;

	private boolean rotatingLeft;
	private boolean rotatingRight;
	private int targetRotation;
	private boolean recentlyBackuped;

	private int damageToken = 0;
	private int health = 3;
	private boolean destroyed = false;
	private boolean powerdownStatus = false;
	private boolean AI;
	private int numberOfCheckpointsPassed;

	// Variables needed for animated sprites
	private String playerName;
	private TextureRegion[] animationFrames;
	private Animation<TextureRegion> robotAnimation;
	private Texture spriteSheet;

	public PlayerToken(String givenName, String textureSpriteSheet, Vector2 startPosition, boolean aI) {
		this.AI = aI;
		this.playerName = givenName;
		this.backupPosition = new Vector2(startPosition.x, startPosition.y);
		this.facingDirection = Direction.NORTH;
		this.damageToken = 0;
		this.health = 3;
        this.position = startPosition;
		this.recentlyBackuped = true;

		// All regarding spritesheet and getting the frames correctly is done here.
		spriteSheet = new Texture(textureSpriteSheet);
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / FRAME_COL,
				spriteSheet.getHeight() / FRAME_ROW);
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

		robotAnimation = new Animation<>(ANIMATION_SPEED, animationFrames);

		setOrigin(TILE_SCALE / 2, TILE_SCALE / 2);

		setXPositionOnBoard();
		setYPositionOnBoard();
	}

    /**
     * Adds one damage token to the player and also decrement the amount of program cards dealt to player.
     */
	public void addDamageToken() {
		if (!damageTokenFull()) {
			damageToken++;
		}
	}

    /**
     * Animates the x-movement on the board
     * @param delta
     */
    private void animateXPositionOnBoard(float delta) {
        setX(getX() + movementVelocity.x * delta);
    }

    /**
     * Animates the y-movement on the board
     * @param delta
     */
    private void animateYPositionOnBoard(float delta) {
        setY(getY() + movementVelocity.y * delta);
    }

    /**
     * If the player has full damage, the player loses one health, is moved to its backup-position and starts with two damage tokens.
     */
    public void checkForDamageCleanUp() {
        if (damageTokenFull()) {
            takeHealth();
            setDamageToken(2);
            moveToBackup();
        }
    }

    /**
     * Checks if player is destroyed.
     * @return Returns true if destroyed, otherwise false.
     */
    public boolean checkIfDestroyed() {
        return this.destroyed;
    }

    /**
     * Checks if player has recently been backuped. It's used in regards to movement after player has been destroyed.
     * @return True if recently backuped, otherwise false
     */
    public boolean checkRecentlyBackuped() {
        return recentlyBackuped;
    }

    /**
     * Checks if players damage is equal or greater than 10.
     * @return True if damage is 10 or more, otherwise false.
     */
    public boolean damageTokenFull() {
        return (damageToken >= 10);
    }

    /**
     * Gets the player's backup-position
     * @return The vector2-position of the player's backup-position
     */
    public Vector2 getBackupPosition() {
        return new Vector2(backupPosition.x, backupPosition.y);
    }

    /**
     * Gets the number of damage tokens of the player
     * @return The number of damage tokens
     */
    public int getDamageToken() {
        return this.damageToken;
    }

    /**
     * Gets the facing direction of the player
     * @return The direction the player is facing
     */
    public Direction getFacingDirection() {
        return facingDirection;
    }

    /**
     * Gets the player health. Each player has three lives
     * @return The number of lives left
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the name of the player
     * @return The player name
     */
    public String getName() {
        return playerName;
    }

    /**
     * Gets the animation for the spritesheet
     * @return The animation that uses the spritesheet
     */
    public Animation<TextureRegion> getRobotAnimation() {
        return robotAnimation;
    }

    /**
     * Gets the vector2-position of the player
     * @return The vector2-position
     */
    public Vector2 getVector2Position() {
        return position;
    }

    /**
     * Gets the x-position of the player
     * @return The x-position
     */
    public int getXPosition() {
        return (int) position.x;
    }

    /**
     * Gets the y-position of the player
     * @return The y-position
     */
    public int getYPosition() {
        return (int) position.y;
    }

    /**
     * Moves the player in a given direction, ignoring the facing direction of the player
     * @param dir The direction in which the player is going to move
     */
    public void moveDirection(Direction dir) {
        switch (dir) {
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

    /**
     * Moves the player in the east direction
     */
    private void moveEast() {
        movingEast = true;
        position.x += 1;
    }

    /**
     * Moves the player in its facing direction
     */
    public void moveInFacingDirection() {
        moveDirection(facingDirection);
    }

    /**
     * Moves the player in the north direction
     */
    private void moveNorth() {
        movingNorth = true;
        position.y += 1;
    }

    /**
     * Moves the player in the south direction
     */
    private void moveSouth() {
        movingSouth = true;
        position.y -= 1;
    }

    /**
     * Moves the player to its last checkpoint
     */
    public void moveToBackup() {
        position = getBackupPosition();
        setXPositionOnBoard();
        setYPositionOnBoard();
    }

    /**
     * Moves the player in the west direction
     */
    private void moveWest() {
        movingWest = true;
        position.x -= 1;
    }

    /**
     * Gets the number of checkpoints the player has passed
     * @return Numbers of checkpoints passed
     */
    public int numberOfCheckpointsPassed() {
        return numberOfCheckpointsPassed;
    }

    /**
     * Confirms that the players has passed a checkpoint
     */
    public void passCheckpoint() {
        numberOfCheckpointsPassed++;
        Vector2 checkpoint = this.getVector2Position();
        setBackupPosition(checkpoint);
    }

    /**
     * Removes one damage token from the player and increment the number of program cards dealt to the player. Is used if player stands on a repair tile at the end of a round
     */
	public void removeDamageToken() {
		if (damageToken == 0) {
			return;
		}
		damageToken--;
	}

    /**
     * Rotates the player 90 degrees clockwise for each number of times given. 90 degrees counter-clockwise when given number of times is negative.
     * @param numberOfTimes Clockwise for positive integer, counter-clockwise for negative integer.
     */
    public void rotatePlayer(int numberOfTimes) {
        int directionSum = (((facingDirection.ordinal() + numberOfTimes) % 4) + 4) % 4;
        facingDirection = Direction.values()[directionSum];

        targetRotation = (int) (this.getRotation() - (90 * numberOfTimes));
        if (numberOfTimes < 0) {
            rotatingRight = true;
        } else {
            rotatingLeft = true;
        }
    }

    /**
     * Updates the players backup-position to a given vector2-position
     * @param lastCheckpoint The last checkpoint the player has passed is set as new backup-position
     */
    public void setBackupPosition(Vector2 lastCheckpoint) {
        if (numberOfCheckpointsPassed() < 1) {
            return;
        } else {
            backupPosition = new Vector2(lastCheckpoint.x, lastCheckpoint.y);
        }
    }

    /**
     * Sets the damage of the player of the number of cards dealt accordingly
     * @param damage The damage given to the player
     */
    public void setDamageToken(int damage) {
        this.damageToken = damage;
    }

    /**
     * Sets the player as destroyed
     * @param destroyed True if destroyed, otherwise false
     */
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	
	public void doPowerdown() {	
		this.damageToken = 0;
		this.powerdownStatus = true;
		System.out.println("Doing powerdown");
	}
	
	public boolean getPowerdownStatus() {
		return this.powerdownStatus;
	}

    /**
     * Sets the health of a player
     * @param health The health the player is given
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Set the player as recently backuped (i.e. destroyed)
     * @param bool True if recently destroyed, otherwise false
     */
    public void setRecentlyBackuped(boolean bool) {
        this.recentlyBackuped = bool;
    }

    /**
     * Sets the player x-position on the board
     */
    private void setXPositionOnBoard() {
        setX(position.x * TILE_SCALE);
    }

    /**
     * Sets the player y-position on the board
     */
    private void setYPositionOnBoard() {
        setY(position.y * TILE_SCALE);
    }

    /**
     * Takes health from player and sets the player as destroyed if health is less than 1
     */
    public void takeHealth() {
        health--;
        if (health < 1) {
            setDestroyed(true);
        }
    }

    /**
     * Updates the player on the board. This is used in regards to its movement on the board.
     * @param delta
     */
    public void update(float delta) {
        animateXPositionOnBoard(delta);
        animateYPositionOnBoard(delta);

        if (movingNorth) {
            // position.y * tileScale : translation of player's position to correct number
            // of pixels
            if (getY() >= position.y * TILE_SCALE) {
                movingNorth = false;
                movementVelocity.y = 0;
                setYPositionOnBoard(); // "hard sets" the position to avoid inaccuracies in position
            } else {
                movementVelocity.y = MOVEMENT_SPEED;
            }
        } else if (movingSouth) {
            if (getY() <= position.y * TILE_SCALE) {
                movingSouth = false;
                setYPositionOnBoard();
                movementVelocity.y = 0;
            } else {
                movementVelocity.y = -MOVEMENT_SPEED;
            }
        } else {
            movementVelocity.y = 0;
        }

        if (movingEast) {
            if (getX() >= position.x * TILE_SCALE) {
                movingEast = false;
                setXPositionOnBoard();
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = MOVEMENT_SPEED;
            }
        } else if (movingWest) {
            if (getX() <= position.x * TILE_SCALE) {
                movingWest = false;
                setXPositionOnBoard();
                movementVelocity.x = 0;
            } else {
                movementVelocity.x = -MOVEMENT_SPEED;
            }
        } else {
            movementVelocity.x = 0;
        }

        if (rotatingLeft) {
            if (this.getRotation() > targetRotation) {
                this.rotate(-ROTATE_SPEED * delta);
            } else {
                this.setRotation(targetRotation);
                rotatingLeft = false;
            }
        } else if (rotatingRight) {
            if (this.getRotation() < targetRotation) {
                this.rotate(ROTATE_SPEED * delta);
            } else {
                this.setRotation(targetRotation);
                rotatingRight = false;
            }
        }
    }

	public boolean isAI() {
		return AI;
	}

	public void setAI(boolean aI) {
		AI = aI;
	}

}
