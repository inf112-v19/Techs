package inf112.skeleton.app.inWork.robots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Robot {

    private Vector3 position;
    private Vector3 velocity;

    private Animation<TextureRegion> greenRobot;


    public Robot(int x, int y) {
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

    public Animation<TextureRegion> getGreenRobot() {
        return greenRobot;
    }
}
