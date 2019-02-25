package inf112.skeleton.app.inWork;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.inWork.state.StartState;
import inf112.skeleton.app.inWork.state.StateManager;


public class NewRoboRally extends ApplicationAdapter {
    public static final String TITLE = "RoboRally";
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    private StateManager statemanager;
    private SpriteBatch batch;

    @Override
    public void create() {
        statemanager = new StateManager();
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(1,0,0,1);
        statemanager.push(new StartState(statemanager));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        statemanager.update(Gdx.graphics.getDeltaTime());
        statemanager.render(batch);
    }

}
