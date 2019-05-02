package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.RoboRally;

public class MainMenuScreen implements Screen {

    // Aspect ratio lengths for the buttons
    private static final int LOGO_WIDTH = 350;
    private static final int LOGO_HEIGHT = 350;
    private static final int BUTTON_WIDTH = 233;
    private static final int BUTTON_HEIGHT = 68;
    private static final int PLAYERBUTTON_WIDTH = 52;
    private static final int PLAYERBUTTON_HEIGHT = 46;

    // Just need to change these if moving around the assets and if they shall be horizontally placed
    private static final int GAMELOGO_X = (RoboRally.SCREEN_WIDTH - LOGO_WIDTH) / 2 + 15;
    private static final int GAMELOGO_Y = 285;
    private static final int PLAYERBUTTONS_MIDDLE = (RoboRally.SCREEN_WIDTH - PLAYERBUTTON_WIDTH) / 2;
    private static final int BUTTONS_MIDDLE = (RoboRally.SCREEN_WIDTH - BUTTON_WIDTH) / 2;
    private static final int BUTTONS_Y = 200;
    private static final int DISTANCE_BETWEEN_BUTTONS_X = 60;
    private static final int DISTANCE_BETWEEN_BUTTONS_Y = 7;

    private static final int PLAYBUTTON_X = BUTTONS_MIDDLE;
    private static final int EXITBUTTON_X = BUTTONS_MIDDLE;
    private static final int PLAYERTWO_X = PLAYERBUTTONS_MIDDLE - (DISTANCE_BETWEEN_BUTTONS_X / 2);
    private static final int PLAYERONE_X = PLAYERTWO_X - DISTANCE_BETWEEN_BUTTONS_X;
    private static final int PLAYERTHREE_X = PLAYERBUTTONS_MIDDLE + (DISTANCE_BETWEEN_BUTTONS_X / 2);
    private static final int PLAYERFOUR_X = PLAYERTHREE_X + DISTANCE_BETWEEN_BUTTONS_X;
    private static final int EXITBUTTON_Y = BUTTONS_Y - BUTTON_HEIGHT - DISTANCE_BETWEEN_BUTTONS_Y;
    private static final int PLAYERBUTTONS_Y = EXITBUTTON_Y - PLAYERBUTTON_HEIGHT - DISTANCE_BETWEEN_BUTTONS_Y;

    private Texture gameLogo;
    private Texture onePlayerActive;
    private Texture onePlayerInactive;
    private Texture twoPlayerActive;
    private Texture twoPlayerInactive;
    private Texture threePlayerActive;
    private Texture threePlayerInactive;
    private Texture fourPlayerActive;
    private Texture fourPlayerInactive;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    private RoboRally game;
    private BoardCards boardScreen;
    private int numberOfPlayers;
    private int numberOfAI;
    private boolean playersError = false;

    public MainMenuScreen(RoboRally game) {
        this.game = game;
        this.gameLogo = new Texture("assets/MainMenuLogo.png");
        this.onePlayerActive = new Texture("assets/1Active.png");
        this.onePlayerInactive = new Texture("assets/1Inactive.png");
        this.twoPlayerActive = new Texture("assets/2Active.png");
        this.twoPlayerInactive = new Texture("assets/2Inactive.png");
        this.threePlayerActive = new Texture("assets/3Active.png");
        this.threePlayerInactive = new Texture("assets/3Inactive.png");
        this.fourPlayerActive = new Texture("assets/4Active.png");
        this.fourPlayerInactive = new Texture("assets/4Inactive.png");
        this.playButtonActive = new Texture("assets/PlayButtonActive.png");
        this.playButtonInactive = new Texture("assets/PlayButtonInactive.png");
        this.exitButtonActive = new Texture("assets/ExitButtonActive.png");
        this.exitButtonInactive = new Texture("assets/ExitButtonInactive.png");

        this.numberOfPlayers = 0;
        this.numberOfAI = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(38/255f, 38/255f, 38/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(gameLogo, GAMELOGO_X, GAMELOGO_Y, LOGO_WIDTH, LOGO_HEIGHT);

        /*
        This part of the code is where the main menu buttons are handled. The if-statement is there to register the coordinates of the mouse, where
        we will be able to make it so that each button is highlighted as the mouse hovers over. We need to make an if-statement for each button.
         */
        if (playersError) {
            game.font.draw(game.batch, "Must choose number of players! Use the buttons at the bottom to choose.", 420, 285);
        }

        switch (this.numberOfPlayers) {
            case 1:
                game.batch.draw(onePlayerActive, PLAYERONE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(twoPlayerInactive, PLAYERTWO_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(threePlayerInactive, PLAYERTHREE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(fourPlayerInactive, PLAYERFOUR_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                break;
            case 2:
                game.batch.draw(onePlayerInactive, PLAYERONE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(twoPlayerActive, PLAYERTWO_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(threePlayerInactive, PLAYERTHREE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(fourPlayerInactive, PLAYERFOUR_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                break;
            case 3:
                game.batch.draw(onePlayerInactive, PLAYERONE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(twoPlayerInactive, PLAYERTWO_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(threePlayerActive, PLAYERTHREE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(fourPlayerInactive, PLAYERFOUR_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                break;
            case 4:
                game.batch.draw(onePlayerInactive, PLAYERONE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(twoPlayerInactive, PLAYERTWO_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(threePlayerInactive, PLAYERTHREE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(fourPlayerActive, PLAYERFOUR_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                break;
            default:
                game.batch.draw(onePlayerInactive, PLAYERONE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(twoPlayerInactive, PLAYERTWO_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(threePlayerInactive, PLAYERTHREE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
                game.batch.draw(fourPlayerInactive, PLAYERFOUR_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < PLAYBUTTON_X + BUTTON_WIDTH && Gdx.input.getX() > PLAYBUTTON_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < BUTTONS_Y + BUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > BUTTONS_Y) {
            game.batch.draw(playButtonActive, PLAYBUTTON_X, BUTTONS_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                if (this.numberOfPlayers > 0) {
                    this.boardScreen = new BoardCards(game, numberOfPlayers, numberOfAI);
                    this.dispose();
                    game.setScreen(boardScreen);
                } else {
                    playersError = true;
                }
            }
        } else {
            game.batch.draw(playButtonInactive, PLAYBUTTON_X, BUTTONS_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

        if(Gdx.input.getX() < PLAYERONE_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERONE_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTONS_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTONS_Y) {
            game.batch.draw(onePlayerActive, PLAYERONE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                this.numberOfPlayers = 1;
                this.numberOfAI = 1;
                this.playersError = false;
            }
        }

        if(Gdx.input.getX() < PLAYERTWO_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERTWO_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTONS_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTONS_Y) {
            game.batch.draw(twoPlayerActive, PLAYERTWO_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.numberOfPlayers = 2;
                this.playersError = false;
            }
        }

        if(Gdx.input.getX() < PLAYERTHREE_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERTHREE_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTONS_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTONS_Y) {
            game.batch.draw(threePlayerActive, PLAYERTHREE_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.numberOfPlayers = 3;
                this.playersError = false;
            }
        }

        if(Gdx.input.getX() < PLAYERFOUR_X + PLAYERBUTTON_WIDTH && Gdx.input.getX() > PLAYERFOUR_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < PLAYERBUTTONS_Y + PLAYERBUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > PLAYERBUTTONS_Y) {
            game.batch.draw(fourPlayerActive, PLAYERFOUR_X, PLAYERBUTTONS_Y, PLAYERBUTTON_WIDTH, PLAYERBUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.numberOfPlayers = 4;
                this.playersError = false;
            }
        }

        // The exit-buttion
        if(Gdx.input.getX() < EXITBUTTON_X + BUTTON_WIDTH && Gdx.input.getX() > EXITBUTTON_X && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() < EXITBUTTON_Y + BUTTON_HEIGHT && RoboRally.SCREEN_HEIGHT - Gdx.input.getY() > EXITBUTTON_Y) {
            game.batch.draw(exitButtonActive, EXITBUTTON_X, EXITBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, EXITBUTTON_X, EXITBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
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

