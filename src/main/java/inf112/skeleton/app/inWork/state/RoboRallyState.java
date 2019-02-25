package inf112.skeleton.app.inWork.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RoboRallyState extends State {

    private Texture greenRobot;

    public RoboRallyState(StateManager statemanager) {
        super(statemanager);
        greenRobot = new Texture("assets/GreenRobot.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
    batch.begin();
    batch.draw(greenRobot,50,50);
    batch.end();
    }

    @Override
    public void dispose() {

    }
}
