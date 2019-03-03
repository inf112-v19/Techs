package inf112.skeleton.app.inWork;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class NewMain {

/*    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = NewRoboRally.TITLE;
        config.width = NewRoboRally.WIDTH;
        config.height = NewRoboRally.HEIGHT;

        new LwjglApplication(new NewRoboRally(), config);
    }*/


    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "RoboRally";
        config.width = BoardGame.WIDTH;
        config.height = BoardGame.HEIGHT;
        config.resizable = false; // Is false at the moment to keep the background ratio correct.
        config.foregroundFPS = 60;

        new LwjglApplication(new BoardGame(), config);
    }
}
