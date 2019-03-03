package inf112.skeleton.app.inWork;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.inWork.screens.GameScreen;
import inf112.skeleton.app.inWork.screens.MainMenuScreen;

public class BoardGame extends Game {
    public static final String TITLE = "RoboRally";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(0,0,0,1);
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
