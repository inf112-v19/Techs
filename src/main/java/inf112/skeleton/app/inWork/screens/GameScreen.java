package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.inWork.BoardGame;

public class GameScreen implements Screen {
    public static final float ZOOM_SPEED = 0.03f;
    public static final float MOVE_SPEED = 16;
    public static final float ANIMATION_SPEED = 0.08f;
    public static final int ROBOT_WIDTH_PIXEL = 64;
    public static final int ROBOT_HEIGHT_PIXEL = 64;
    public static final int ROBOT_WIDTH = 96;
    public static final int ROBOT_HEIGHT = 96;

    // Variables used in regards to animating robots
    Animation<TextureRegion>[] greenRobot;
    private int greenRobotAnimation;
    private float x;
    private float y;
    private float statetime;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    BoardGame game;

    // Must have this constructor so that the game same game gets transferred between screens.
    public GameScreen(BoardGame game) {
        this.game = game;

        // This part initializes what is needed to animate robot(s).
        x = 0;
        y = 0;
        greenRobotAnimation = 0;
        greenRobot = new Animation[11];
        TextureRegion[][] greenRobotSpriteSheet = TextureRegion.split(new Texture("assets/GreenRobotSpriteSheet.png"), ROBOT_WIDTH_PIXEL, ROBOT_HEIGHT_PIXEL);
        greenRobot[greenRobotAnimation] = new Animation(ANIMATION_SPEED, greenRobotSpriteSheet[0]);
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BoardGame.WIDTH, BoardGame.HEIGHT);
    }

    @Override
    public void render(float delta) {
        renderer.setView(camera);
        renderer.render();

        statetime += delta;

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(greenRobot[greenRobotAnimation].getKeyFrame(statetime, true), x, y, ROBOT_WIDTH, ROBOT_HEIGHT);
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
