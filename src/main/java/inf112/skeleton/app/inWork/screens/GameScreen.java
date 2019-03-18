package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.inWork.BoardGame;
import inf112.skeleton.app.inWork.RobotToken;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private static final float ZOOM_SPEED = 0.03f;
    private static final float MOVE_SPEED = 16;
    private static final float ANIMATION_SPEED = 0.06f;
    private static final int ROBOT_WIDTH = 96;
    private static final int ROBOT_HEIGHT = 96;
    private static final float TILE_SCALE = 96;

    // Variables used in regards to animating robots
    Animation<TextureRegion> playerOneRobotAnimation;
    private float statetime;


    private RobotToken playerOne;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    BoardGame game;

    // Must have this constructor so that the game same game gets transferred between screens.
    public GameScreen(BoardGame game) {
        this.game = game;
        playerOne = new RobotToken("TestPlayer1", "assets/BlueRobotSpriteSheet.png", new Vector2(0,0));

        playerOneRobotAnimation = new Animation<TextureRegion>(ANIMATION_SPEED, playerOne.getAnimationFrames());
        statetime = 0f;
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
        TextureRegion playerOneFrames = playerOneRobotAnimation.getKeyFrame(statetime, true);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(playerOneFrames, playerOne.getX(), playerOne.getY(), ROBOT_WIDTH, ROBOT_HEIGHT);
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
