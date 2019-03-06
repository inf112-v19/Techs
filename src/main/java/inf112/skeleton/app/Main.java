package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = RoboRally.TITLE;
        cfg.width = RoboRally.WIDTH;
        cfg.height = RoboRally.HEIGHT;
        cfg.resizable = false;
        cfg.foregroundFPS = 60;

        new LwjglApplication(new RoboRally(), cfg);
    }
}