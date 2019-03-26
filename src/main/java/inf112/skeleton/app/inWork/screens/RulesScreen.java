package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.RulesScreen2;
import inf112.skeleton.app.logic.BoardCards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class RulesScreen implements Screen {

	private static final int NEXTBUTTON_HEIGHT = 100;
	private static final int NEXTBUTTON_WIDTH = 200;
	private static final int NEXTBUTTON_X = (RoboRally.WIDTH / 4 ) * 3;
	private static final int NEXTBUTTON_Y = 20;
	private static final int BACKBUTTON_WIDTH = 200;
	private static final int BACKBUTTON_HEIGHT = 100;
	private static final int BACKBUTTON_X = (RoboRally.WIDTH - BACKBUTTON_WIDTH) / 2;
	private static final int BACKBUTTON_Y = 220;
	private static final int RULES_HEIGHT = 770;
	private static final int RULES_WIDTH = 1280;
	private static final int RULES_X = 0;
	private static final int RULES_Y = 0;
	
	
	private Texture nextButtonInactive; //nextButton = exitButton
	private Texture nextButtonActive;
	private Texture skipButton;
	private Texture rules;

	private RoboRally game;
	
	private BoardCards BoardScreen;
	//private RulesScreen2 nextScreen;


	public RulesScreen(RoboRally game) {
		this.game = game;
		this.nextButtonInactive = new Texture("assets/ExitButtonInactive.png"); //exit for next foreløpig
		this.nextButtonActive = new Texture("assets/ExitButtonActive.png");
		//this.rules = new Texture("assets/tiledbackgroundtest.png");
		this.rules = new Texture("assets/RulesScreenTest2.png");
		this.BoardScreen = new BoardCards(game);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		game.batch.draw(rules, RULES_X, RULES_Y , RULES_WIDTH, RULES_HEIGHT);
		
		if(Gdx.input.getX() < NEXTBUTTON_X + NEXTBUTTON_WIDTH && Gdx.input.getX() > NEXTBUTTON_X && RoboRally.HEIGHT - Gdx.input.getY() < NEXTBUTTON_Y + NEXTBUTTON_HEIGHT && RoboRally.HEIGHT - Gdx.input.getY() > NEXTBUTTON_Y ) {
			game.batch.draw(this.nextButtonActive, NEXTBUTTON_X, NEXTBUTTON_Y, NEXTBUTTON_WIDTH, NEXTBUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {			
				this.dispose();
				
				game.setScreen(new RulesScreen2(game));
				
			}
		} else {			
			game.batch.draw(this.nextButtonInactive, NEXTBUTTON_X, NEXTBUTTON_Y, NEXTBUTTON_WIDTH,
					NEXTBUTTON_HEIGHT);
		}
		
		//this.dispose();

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
