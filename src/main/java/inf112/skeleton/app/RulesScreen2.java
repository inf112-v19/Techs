package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.inWork.screens.RulesScreen;
import inf112.skeleton.app.logic.BoardCards;

public class RulesScreen2 implements Screen {

	private static final int NEXTBUTTON_HEIGHT = 100;
	private static final int NEXTBUTTON_WIDTH = 200;
	private static final int NEXTBUTTON_X = (RoboRally.WIDTH / 4) * 3;
	private static final int NEXTBUTTON_Y = 20;
	private static final int SKIPBUTTON_HEIGHT = 100;
	private static final int SKIPBUTTON_WIDTH = 200;
	private static final int SKIPBUTTON_X = (RoboRally.WIDTH / 4) * 2;
	private static final int SKIPBUTTON_Y = 20;
	private static final int BACKBUTTON_WIDTH = 200;
	private static final int BACKBUTTON_HEIGHT = 100;
	private static final int BACKBUTTON_X = (RoboRally.WIDTH / 4);
	private static final int BACKBUTTON_Y = 220;
	private static final int RULES_HEIGHT = 770;
	private static final int RULES_WIDTH = 1280;
	private static final int RULES_X = 0;
	private static final int RULES_Y = 0;

	private Texture nextButtonInactive; // nextButton = exitButton
	private Texture nextButtonActive;
	private Texture skipButtonActive;
	private Texture skipButtonInactive;
	private Texture backButton;
	private Texture rules;

	private RoboRally game;

	private BoardCards BoardScreen;
	private RulesScreen previousScreen;

	public RulesScreen2(RoboRally game) {
		this.game = game;
		this.nextButtonInactive = new Texture("assets/ExitButtonInactive.png"); // Exit asset for next foreløpig
		this.nextButtonActive = new Texture("assets/ExitButtonActive.png");
		this.skipButtonActive = new Texture("assets/PlayButtonActive.png");
		this.skipButtonInactive = new Texture("assets/PlayButtonInactive.png");

		this.rules = new Texture("assets/RulesScreen_test_screen2.png");
		this.BoardScreen = new BoardCards(game);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();

		game.batch.draw(rules, RULES_X, RULES_Y, RULES_WIDTH, RULES_HEIGHT);

		if (Gdx.input.getX() < NEXTBUTTON_X + NEXTBUTTON_WIDTH && Gdx.input.getX() > NEXTBUTTON_X
				&& RoboRally.HEIGHT - Gdx.input.getY() < NEXTBUTTON_Y + NEXTBUTTON_HEIGHT
				&& RoboRally.HEIGHT - Gdx.input.getY() > NEXTBUTTON_Y) {
			game.batch.draw(this.nextButtonActive, NEXTBUTTON_X, NEXTBUTTON_Y, NEXTBUTTON_WIDTH, NEXTBUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();

				game.setScreen(BoardScreen);
			}
		} else {
			game.batch.draw(this.nextButtonInactive, NEXTBUTTON_X, NEXTBUTTON_Y, NEXTBUTTON_WIDTH, NEXTBUTTON_HEIGHT);
		}

		if (Gdx.input.getX() < SKIPBUTTON_X + SKIPBUTTON_WIDTH && Gdx.input.getX() > SKIPBUTTON_X
				&& RoboRally.HEIGHT - Gdx.input.getY() < SKIPBUTTON_Y + SKIPBUTTON_HEIGHT
				&& RoboRally.HEIGHT - Gdx.input.getY() > SKIPBUTTON_Y) {
			game.batch.draw(this.skipButtonActive, SKIPBUTTON_X, SKIPBUTTON_Y, SKIPBUTTON_WIDTH, SKIPBUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new RulesScreen(game));
			}
		} else {
			game.batch.draw(this.skipButtonInactive, SKIPBUTTON_X, SKIPBUTTON_Y, SKIPBUTTON_WIDTH, SKIPBUTTON_HEIGHT);
		}

		// this.dispose();

		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
