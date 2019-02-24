package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class NewRoboRally extends ApplicationAdapter {

    private Rectangle robot;
    private Texture greenRobot;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    @Override
    public void create() {
        greenRobot = new Texture(Gdx.files.internal("GreenRobot.png"));
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        // The robot
        robot = new Rectangle();
        robot.x = 800 / 2 - 28 / 2;
        robot.y = 20;
        robot.width = 28;
        robot.height = 28;

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(greenRobot, robot.x, robot.y);
        batch.end();

        // Moving the robot when clicking the mouse. At the moment, the movement is not grid-based
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            robot.x = touchPos.x;
            robot.y = touchPos.y;
        }

        // Moving the robot using keyboard keys.
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) robot.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) robot.x += 200 * Gdx.graphics.getDeltaTime();
        if(robot.x < 0) robot.x = 0;
        if(robot.x > 800 - 28) robot.x = 800 - 28;
    }

    @Override
    public void dispose() {
        greenRobot.dispose();
        batch.dispose();
    }

}
