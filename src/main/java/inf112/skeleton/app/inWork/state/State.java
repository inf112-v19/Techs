package inf112.skeleton.app.inWork.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected StateManager statemanager;

    public State(StateManager statemanager) {
        this.statemanager = statemanager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();

}
