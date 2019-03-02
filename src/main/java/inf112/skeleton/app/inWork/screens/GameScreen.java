package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.PlayerToken;
import inf112.skeleton.app.inWork.BoardGame;
import inf112.skeleton.app.inWork.robots.Robot;

import java.util.ArrayList;

public class GameScreen implements Screen {
    public static final float ZOOM_SPEED = 0.03f;
    public static final float MOVE_SPEED = 7;

    private Robot greenRobot;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    float elapsed;
    private ArrayList<Robot> playersList;

    BoardGame game;

    public GameScreen(BoardGame game) {
        this.game = game;
    }


    @Override
    public void show() {
        greenRobot = new Robot("Player_One", 0,0);
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

        if (Gdx.input.isTouched()) {
            camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            camera.zoom += ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            camera.zoom -= ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0,MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0,-MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-MOVE_SPEED,0,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
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
