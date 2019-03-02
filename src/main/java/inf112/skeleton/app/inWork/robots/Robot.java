package inf112.skeleton.app.inWork.robots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.Direction;

public class Robot {

    private final float SPPED = 120;

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

    public int getXPosition() {
        return (int) position.x;
    }

    public int getYPosition() {
        return (int) position.y;
    }

    public Animation<TextureRegion> getGreenRobot() {
        return greenRobot;
    }
}
