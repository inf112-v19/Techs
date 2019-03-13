package inf112.skeleton.app.inWork;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.inWork.robots.Robot;

import java.util.ArrayList;

public class RobotToken {

    private static final float ANIMATION_SPEED = 0.08f;
    private static final int ROBOT_WIDTH_PIXEL = 64;
    private static final int ROBOT_HEIGHT_PIXEL = 64;

    Animation<TextureRegion>[] blueRobot;
    Animation<TextureRegion>[] greenRobot;
    Animation<TextureRegion>[] redRobot;
    Animation<TextureRegion>[] yellowRobot;
    private int blueRobotAnimation;
    private int greenRobotAnimation;
    private int redRobotAnimation;
    private int yellowRobotAnimation;
    private float x;
    private float y;
    private float statetime;

    public ArrayList<RobotToken> roboList;

    public RobotToken() {
        // This part initializes what is needed to animate a robot.
        x = 0;
        y = 0;
        blueRobotAnimation = 0;
        greenRobotAnimation = 0;
        redRobotAnimation = 0;
        yellowRobotAnimation = 0;
        blueRobot = new Animation[11];
        greenRobot = new Animation[11];
        redRobot = new Animation[11];
        yellowRobot = new Animation[11];
        TextureRegion[][] blueRobotSpriteSheet = TextureRegion.split(new Texture("assets/BlueRobotSpriteSheet.png"), ROBOT_WIDTH_PIXEL, ROBOT_HEIGHT_PIXEL);
        TextureRegion[][] greenRobotSpriteSheet = TextureRegion.split(new Texture("assets/GreenRobotSpriteSheet.png"), ROBOT_WIDTH_PIXEL, ROBOT_HEIGHT_PIXEL);
        TextureRegion[][] redRobotSpriteSheet = TextureRegion.split(new Texture("assets/RedRobotSpriteSheet.png"), ROBOT_WIDTH_PIXEL, ROBOT_HEIGHT_PIXEL);
        TextureRegion[][] yellowRobotSpriteSheet = TextureRegion.split(new Texture("assets/YellowRobotSpriteSheet.png"), ROBOT_WIDTH_PIXEL, ROBOT_HEIGHT_PIXEL);
        blueRobot[blueRobotAnimation] = new Animation(ANIMATION_SPEED, blueRobotSpriteSheet[0]);
        greenRobot[greenRobotAnimation] = new Animation(ANIMATION_SPEED, greenRobotSpriteSheet[0]);
        redRobot[redRobotAnimation] = new Animation(ANIMATION_SPEED, redRobotSpriteSheet[0]);
        yellowRobot[yellowRobotAnimation] = new Animation(ANIMATION_SPEED, yellowRobotSpriteSheet[0]);
    }
}
