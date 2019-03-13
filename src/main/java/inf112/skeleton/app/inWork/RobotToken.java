package inf112.skeleton.app.inWork;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

public class RobotToken {

    private static final float ANIMATION_SPEED = 0.08f;
    private static final int ROBOT_WIDTH_PIXEL = 64;
    private static final int ROBOT_HEIGHT_PIXEL = 64;
    private static final int ROBOT_WIDTH_PIXEL = 64;
    private static final int ROBOT_HEIGHT_PIXEL = 64;

    // Variables needed for movement, direction and position
    private Vector2 movementVelocity = new Vector2();
    private Vector2 position;
    private Direction facingDirection;
    private boolean movingNorth = true;
    private boolean movingSouth = true;
    private boolean movingEast = true;
    private boolean movingWest = true;

    // Variables needed for animated sprites
    Animation<TextureRegion>[] robot;
    private int robotAnimation;
    private float statetime;

    public RobotToken(TextureRegion[][] spriteSheet, Vector2 startPosition) {
        position = startPosition;
        robotAnimation = 0;
        robot = new Animation[11];
        TextureRegion[][] robotSpriteSheet = spriteSheet;
        robot[robotAnimation] = new Animation(ANIMATION_SPEED, robotSpriteSheet[0]);
    }
}
