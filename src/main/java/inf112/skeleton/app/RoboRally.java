package inf112.skeleton.app;

import com.badlogic.gdx.Game;

public class RoboRally extends Game {
    @Override
    public void create() {
        setScreen(new Board());
        //setScreen(new ProgramSheet());
    }
}