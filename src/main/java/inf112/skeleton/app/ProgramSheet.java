package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * RULES
 * Life tokens == number of life
 *
 * Conveyor belt has higher priority than any card
 *
 * Locked register
 * card is locked and has to be used for each turn
 * can only be removed if register gets fixed
 *
 * Program Sheet
 * 1) Each player gets (9 - damage) cards start of each turn
 * 2) 5 of these cards are selected for programming
 *       *selected by numbers on keyboard 1-9
 *       *Hurry up timer?
 */

/**
 * Layout
 *
 *Life tokens = number of lifes player has left.
 *
 *Power button = shows power status.
 *
 *damage = show damage. Positioned in middle
 *
 *card holder = shows cards. Positioned on the bottom.
 */


public class ProgramSheet implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private SpriteBatch spriteBatch;
    private TextureAtlas atlas;
    private Sprite ProgramCard0;
    private Sprite ProgramCard1;
    private Sprite ProgramCard2;
    private Sprite ProgramCard3;
    private Sprite ProgramCard4;

    public ProgramSheet(){
        spriteBatch = new SpriteBatch();
        atlas = new TextureAtlas("assets/ProgramSheet/ProgramCardsTexturePack/cardsTexture.atlas");

        ProgramCard0 = atlas.createSprite("10 U Turn", -1);
        ProgramCard1 = atlas.createSprite("680 Move 2", -1);
        ProgramCard2 = atlas.createSprite("680 Move 2", -1);
        ProgramCard3 = atlas.createSprite("680 Move 2", -1);
        ProgramCard4 = atlas.createSprite("680 Move 2", -1);

        map = new TmxMapLoader().load("assets/ProgramSheet/ProgramSheet.tmx");
    }

    @Override
    public void show() {
        renderer = new OrthogonalTiledMapRenderer(map, .3f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();

        spriteBatch.begin();
        spriteBatch.draw(ProgramCard0,0,0,94,130);
        spriteBatch.draw(ProgramCard1,90,0,94,130);
        spriteBatch.draw(ProgramCard2,180,0,94,130);
        spriteBatch.draw(ProgramCard3,270,0,94,130);
        spriteBatch.draw(ProgramCard4,360,0,94,130);
        spriteBatch.end();
    }

    @Override
    public void resize(int i, int i1) {

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
        spriteBatch.dispose();
        atlas.dispose();
    }
}
