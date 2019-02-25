package inf112.skeleton.app.inWork.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.inWork.NewRoboRally;
import inf112.skeleton.app.inWork.robots.GreenRobot;

public class RoboRallyState extends State {
    private GreenRobot greenRobot;
    float elapsed;

    public RoboRallyState(StateManager statemanager) {
        super(statemanager);
        greenRobot = new GreenRobot(50,50);
        camera.setToOrtho(false, NewRoboRally.WIDTH/2, NewRoboRally.HEIGHT/2);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float delta) {
handleInput();
greenRobot.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        elapsed += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(greenRobot.getGreenRobot().getKeyFrame(elapsed), greenRobot.getPosition().x, greenRobot.getPosition().y);
    batch.end();
    }

    @Override
    public void dispose() {

    }
}
