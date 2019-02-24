package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class RoboRallyScreen implements Screen {
    final RoboRally roboRally;

    private Texture greenRobot;
    private OrthographicCamera camera;
    private Rectangle robot;


    public RoboRallyScreen(final RoboRally roboRally) {
        this.roboRally = roboRally;

        // Loads the images of the robots
        greenRobot = new Texture(Gdx.files.internal("GreenRobot.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Creates a rectangle to logically represent the robot
        robot = new Rectangle();
        robot.x = 800 / 2 - 64 / 2; // The math here centers the robot horizontally. Not necessary for the grid-based board.
        robot.y = 20;
        robot.width = 64;
        robot.height = 64;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        roboRally.batch.begin();
        roboRally.batch.draw(greenRobot, robot.x, robot.y);
        roboRally.batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            robot.x = touchPos.x - 64 / 2;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        greenRobot.dispose();
    }
}
