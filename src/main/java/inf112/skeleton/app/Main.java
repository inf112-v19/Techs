package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main { 
   
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Robot-Rally";
        cfg.width = 480;
        cfg.height = 480;

        IBoard board = new Board(4, 4);
        // Adds a player to the board
        board.setTile(2, 2, 1);
        UserInterface userInterface = new UserInterface(board);
        new LwjglApplication(userInterface, cfg);
        
        boolean isPlaying = true;        
        while(isPlaying) {
            switch(userInterface.checkForUserInput()) {
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
    }
}