package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import inf112.skeleton.app.inWork.BoardGame;
import inf112.skeleton.app.inWork.robots.Robot;
public class GameScreen implements Screen {
    public static final float ZOOM_SPEED = 0.03f;
    public static final float MOVE_SPEED = 16;

    private Robot greenRobot;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    float elapsed; // Is at the moment used to handle the gif-animation on the robot.

    BoardGame game;

    // Must have this constructor so that the game same game gets transferred between screens.
    public GameScreen(BoardGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        greenRobot = new Robot(0,0);
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BoardGame.WIDTH, BoardGame.HEIGHT);

    }

    @Override
    public void render(float delta) {
        elapsed += Gdx.graphics.getDeltaTime();
        renderer.setView(camera);
        renderer.render();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(greenRobot.getGreenRobot().getKeyFrame(elapsed), greenRobot.getPosition().x, greenRobot.getPosition().y,96,96);
        game.batch.end();


        /*
        The code here handles the zoom- and camera movement functions. The drag-functionality might be removed if conflict arise when using
        the mouse button to click on program cards. The buttons used are WASD for camera-movement and E/Q for ZoomIn/ZoomOut.
         */
        if (Gdx.input.isTouched()) {
            camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom += ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom -= ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0,MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0,-MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-MOVE_SPEED,0,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(MOVE_SPEED,0,0);
            camera.update();
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

    }
}
