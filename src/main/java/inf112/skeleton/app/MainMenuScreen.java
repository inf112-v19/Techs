package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
    final RoboRally roboRally;
    OrthographicCamera camera;

    public MainMenuScreen(final RoboRally roboRally) {
        this.roboRally = roboRally;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        this.roboRally.batch.setProjectionMatrix(camera.combined);

        this.roboRally.batch.begin();
        this.roboRally.font.draw(roboRally.batch, "RoboRally!", 100, 150);
        this.roboRally.font.draw(roboRally.batch, "Click anywhere to begin..", 100, 100);

        if(Gdx.input.isTouched()) {
            roboRally.setScreen(new RoboRallyScreen(roboRally));
            dispose();
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
