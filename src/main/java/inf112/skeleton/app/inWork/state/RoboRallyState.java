package inf112.skeleton.app.inWork.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.inWork.NewRoboRally;
import inf112.skeleton.app.inWork.robots.Robot;

public class RoboRallyState extends State {
    private Robot greenRobot;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    float elapsed;

    public RoboRallyState(StateManager statemanager) {
        super(statemanager);
        greenRobot = new Robot(0,0);
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera.setToOrtho(false, NewRoboRally.WIDTH, NewRoboRally.HEIGHT);
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
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(greenRobot.getGreenRobot().getKeyFrame(elapsed), greenRobot.getPosition().x, greenRobot.getPosition().y, 96,96);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
