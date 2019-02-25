package inf112.skeleton.app.inWork;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class NewMain {
/*
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = NewRoboRally.TITLE;
        config.width = NewRoboRally.WIDTH;
        config.height = NewRoboRally.HEIGHT;

        new LwjglApplication(new NewRoboRally(), config);
    }
    */

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "RoboRally";
        config.width = 480;
        config.height = 480;

        new LwjglApplication(new BoardGame(), config);
    }
}
