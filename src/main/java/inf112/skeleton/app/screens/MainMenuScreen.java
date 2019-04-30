package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.RoboRally;

public class MainMenuScreen implements Screen {
    private static final int PLAYERBUTTON_WIDTH = 100;
    private static final int PLAYERBUTTON_HEIGHT = 100;

    // Just need to change these if moving around the player buttons and if they shall be horizontally placed
    private static final int PLAYERBUTTON_Y = 310;
    private static final int MIDDLE_OF_SCREEN = (RoboRally.SCREEN_WIDTH - PLAYERBUTTON_WIDTH) / 2;
    private static final int DISTANCE_BETWEEN_BUTTONS = 136;

    private static final int PLAYERTWO_X = MIDDLE_OF_SCREEN - (DISTANCE_BETWEEN_BUTTONS / 2);
    private static final int PLAYERONE_X = PLAYERTWO_X - DISTANCE_BETWEEN_BUTTONS;
    private static final int PLAYERTHREE_X = MIDDLE_OF_SCREEN + (DISTANCE_BETWEEN_BUTTONS / 2);
    private static final int PLAYERFOUR_X = PLAYERTHREE_X + DISTANCE_BETWEEN_BUTTONS;
    private static final int EXITBUTTON_WIDTH = 400;
    private static final int EXITBUTTON_HEIGHT = 117;
    private static final int EXITBUTTON_X = (RoboRally.SCREEN_WIDTH - EXITBUTTON_WIDTH) / 2;
    private static final int EXITBUTTON_Y = 220;

    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    private RoboRally game;
    private BoardCards BoardScreen;

    public MainMenuScreen(RoboRally game) {
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
        if(Gdx.input.getX() < PLAYERONE_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERONE_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTON_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTON_Y) {
            game.batch.draw(playButtonActive, PLAYERONE_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                this.BoardScreen = new BoardCards(game, 1);
                this.dispose();
                game.setScreen(BoardScreen);
            }
        } else {
            game.batch.draw(playButtonInactive, PLAYERONE_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < PLAYERTWO_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERTWO_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTON_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTON_Y) {
            game.batch.draw(playButtonActive, PLAYERTWO_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.BoardScreen = new BoardCards(game, 2);
                this.dispose();
                game.setScreen(BoardScreen);
            }
        } else {
            game.batch.draw(playButtonInactive, PLAYERTWO_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < PLAYERTHREE_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERTHREE_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTON_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTON_Y) {
            game.batch.draw(playButtonActive, PLAYERTHREE_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.BoardScreen = new BoardCards(game, 3);
                this.dispose();
                game.setScreen(BoardScreen);
            }
        } else {
            game.batch.draw(playButtonInactive, PLAYERTHREE_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < PLAYERFOUR_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERFOUR_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTON_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTON_Y) {
            game.batch.draw(playButtonActive, PLAYERFOUR_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.BoardScreen = new BoardCards(game, 4);
                this.dispose();
                game.setScreen(BoardScreen);
            }
        } else {
            game.batch.draw(playButtonInactive, PLAYERFOUR_X, (RoboRally.SCREEN_HEIGHT - PLAYERBUTTON_HEIGHT) / 2, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
        }

        // The exit-buttion
        if(Gdx.input.getX() < EXITBUTTON_X + EXITBUTTON_WIDTH && Gdx.input.getX() > EXITBUTTON_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < EXITBUTTON_Y + EXITBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > EXITBUTTON_Y) {
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

