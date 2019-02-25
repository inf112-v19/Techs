package inf112.skeleton.app.InWork;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class NewMain {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = NewRoboRally.TITLE;
        config.width = NewRoboRally.WIDTH;
        config.height = NewRoboRally.HEIGHT;

        new LwjglApplication(new NewRoboRally(), config);
    }
}
