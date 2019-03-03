package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.inWork.BoardGame;

public class MainMenuScreen implements Screen {
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 250;

    BoardGame game;

    Texture playButtonActive;

    public MainMenuScreen(BoardGame game) {
        this.game = game;
        this.playButtonActive = new Texture("assets/PlayButtons.png");
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
        int x = (BoardGame.WIDTH - BUTTON_WIDTH) / 2;
        int y = (BoardGame.WIDTH - BUTTON_HEIGHT) / 2;
        if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && BoardGame.HEIGHT - Gdx.input.getY() < y + BUTTON_HEIGHT && BoardGame.HEIGHT - Gdx.input.getY() > y) {
            game.batch.draw(playButtonActive, (BoardGame.WIDTH - BUTTON_WIDTH) / 2, (BoardGame.HEIGHT - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        } else {
            game.batch.draw(playButtonActive, (BoardGame.WIDTH - BUTTON_WIDTH) / 2, (BoardGame.HEIGHT - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
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
