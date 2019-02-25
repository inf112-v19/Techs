package inf112.skeleton.app.InWork;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class NewRoboRally extends ApplicationAdapter {
    public static final String TITLE = "RoboRally";
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    SpriteBatch batch;
    Texture greenrobot;

    @Override
    public void create() {
        batch = new SpriteBatch();
        greenrobot = new Texture("assets/GreenRobot.png");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(greenrobot,0,0);
        batch.end();
    }

}
