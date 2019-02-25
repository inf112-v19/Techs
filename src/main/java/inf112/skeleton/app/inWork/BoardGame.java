package inf112.skeleton.app.inWork;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.inWork.game.Game;
import inf112.skeleton.app.inWork.game.TiledGame;

public class BoardGame extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    OrthographicCamera camera;

    Game boardgame;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("assets/GreenRobot.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        boardgame = new TiledGame();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()) {
            camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            camera.zoom += 0.03;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            camera.zoom -= 0.03;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0,7,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0,-7,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-5,0,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(5,0,0);
            camera.update();
        }

        boardgame.render(camera);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
