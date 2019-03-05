package inf112.skeleton.app.inWork.inWorkProgramSheet;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.RoboRally;

public class MainProgramSheet {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "ProgramSheet";
        cfg.width = 480;
        cfg.height = 480;

        new LwjglApplication(new RoboRallyNew(), cfg);
    }
}
