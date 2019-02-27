package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "RoboRally";
        cfg.width = 480;
        cfg.height = 480;

        new LwjglApplication(new RoboRally(), cfg);
        
/*
        IBoard board = new Board(4, 4);
        // Adds a player to the board
        board.setTile(2, 2, 1);
        UserInterface userInterface = new UserInterface(board);

        /*
        boolean isPlaying = true;
        while (isPlaying) {
            switch (userInterface.checkForUserInput()) {
                case NORTH:
                    board.moveVertical(1);
                    break;
                case SOUTH:
                    board.moveVertical(-1);
                    break;
                case EAST:
                    board.moveHorizontal(1);
                    break;
                case WEST:
                    board.moveHorizontal(-1);
                    break;
                case STAY:
                    break;
            }
        }
        */
    }
}