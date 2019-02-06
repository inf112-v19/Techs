package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "hello-world";
        cfg.width = 480;
        cfg.height = 480;

        IBoard board = new Board(4, 4);
        board.setTile(2, 2, 1);
        new LwjglApplication(new UserInterface(board), cfg);
        
    }
}