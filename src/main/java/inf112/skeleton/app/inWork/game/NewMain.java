package inf112.skeleton.app.inWork.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class NewMain {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = BoardGame.TITLE;
        config.width = BoardGame.WIDTH;
        config.height = BoardGame.HEIGHT;
        config.resizable = false; // Is false at the moment to keep the background ratio correct.
        config.foregroundFPS = 60;

        new LwjglApplication(new BoardGame(), config);
    }
}
