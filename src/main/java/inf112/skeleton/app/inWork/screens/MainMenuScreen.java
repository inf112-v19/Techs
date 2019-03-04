package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.inWork.BoardGame;

public class MainMenuScreen implements Screen {
    private static final int PLAYBUTTON_WIDTH = 200;
    private static final int PLAYBUTTON_HEIGHT = 100;
    public static final int PLAYBUTTON_X = (BoardGame.WIDTH - PLAYBUTTON_WIDTH) / 2;
    private static final int PLAYBUTTON_Y = 310;
    private static final int EXITBUTTON_WIDTH = 160;
    private static final int EXITBUTTON_HEIGHT = 80;
    public static final int EXITBUTTON_X = (BoardGame.WIDTH - EXITBUTTON_WIDTH) / 2;
    private static final int EXITBUTTON_Y = 220;

    BoardGame game;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    public MainMenuScreen(BoardGame game) {
        this.game = game;
        this.playButtonActive = new Texture("assets/PlayButtonActive.png");
        this.playButtonInactive = new Texture("assets/PlayButtonInactive.png");
        this.exitButtonActive = new Texture("assets/ExitButtonActive.png");
        this.exitButtonInactive = new Texture("assets/ExitButtonInactive.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        /*
        This part of the code is where the main menu buttons are handled. The if-statement is there to register the coordinates of the mouse, where
        we will be able to make it so that each button is highlighted as the mouse hovers over. We need to make an if-statement for each button.
         */
        if(Gdx.input.getX() < PLAYBUTTON_X + PLAYBUTTON_WIDTH && Gdx.input.getX() > PLAYBUTTON_X && BoardGame.HEIGHT - Gdx.input.getY() < PLAYBUTTON_Y + PLAYBUTTON_HEIGHT && BoardGame.HEIGHT - Gdx.input.getY() > PLAYBUTTON_Y) {
            game.batch.draw(playButtonActive, PLAYBUTTON_X, (BoardGame.HEIGHT - PLAYBUTTON_HEIGHT) / 2, PLAYBUTTON_WIDTH, PLAYBUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, PLAYBUTTON_X, (BoardGame.HEIGHT - PLAYBUTTON_HEIGHT) / 2, PLAYBUTTON_WIDTH, PLAYBUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < EXITBUTTON_X + EXITBUTTON_WIDTH && Gdx.input.getX() > EXITBUTTON_X && BoardGame.HEIGHT - Gdx.input.getY() < EXITBUTTON_Y + EXITBUTTON_HEIGHT && BoardGame.HEIGHT - Gdx.input.getY() > EXITBUTTON_Y) {
            game.batch.draw(exitButtonActive, EXITBUTTON_X, EXITBUTTON_Y, EXITBUTTON_WIDTH, EXITBUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, EXITBUTTON_X, EXITBUTTON_Y, EXITBUTTON_WIDTH, EXITBUTTON_HEIGHT);
        }

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

