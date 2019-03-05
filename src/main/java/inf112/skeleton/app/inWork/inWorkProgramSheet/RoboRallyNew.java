package inf112.skeleton.app.inWork.inWorkProgramSheet;

import com.badlogic.gdx.Game;
import inf112.skeleton.app.Board;

public class RoboRally extends Game {

    @Override
    public void create() {
        //setScreen(new Board());
        setScreen(new ProgramSheet());
    }
}
