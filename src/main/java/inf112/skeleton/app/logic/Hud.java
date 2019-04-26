package inf112.skeleton.app.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.objects.PlayerToken;
import inf112.skeleton.app.screens.Board;

import java.util.ArrayList;

public class Hud extends Board {





    private ArrayList<PlayerToken> playerList;
    private SpriteBatch batch;

    public Hud(RoboRally game, SpriteBatch batch) {
        super(game);
        this.batch = batch;



    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        batch.end();



    }
}
