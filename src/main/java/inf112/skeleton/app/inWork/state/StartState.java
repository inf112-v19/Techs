package inf112.skeleton.app.inWork.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.inWork.NewRoboRally;

public class StartState extends State {

    private Texture background;

    public StartState(StateManager statemanager) {
        super(statemanager);
        background = new Texture("assets/StoneTile.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            statemanager.set(new RoboRallyState(statemanager));
            dispose();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.begin();
        batch.draw(background,0,0, NewRoboRally.WIDTH, NewRoboRally.HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
