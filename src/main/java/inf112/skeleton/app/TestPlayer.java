package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TestPlayer extends Sprite {

    /* The movement velocity */
    private Vector2 velocity = new Vector2();
    private float speed = 60 * 2;
    private float gravity = 60 * 1.8f;
    private String playerName;

    public TestPlayer(Sprite sprite, String playerName) {
        super(sprite);
        this.playerName = playerName;
    }
    
    public String getName() {
        return playerName;
    }

    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta) {
        /*
        velocity.y -= gravity * delta;
        if (velocity.y > speed) {
            velocity.y = speed;
        } else if (velocity.y < speed) {
            velocity.y = -speed;
        }*/

        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
    }
}
