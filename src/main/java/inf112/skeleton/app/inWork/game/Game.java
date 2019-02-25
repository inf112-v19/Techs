package inf112.skeleton.app.inWork.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class Game {

    public abstract void render (OrthographicCamera camera);
    public abstract void update (float delta);
    public abstract void dispose();

    // Gets a tile by pixel position within the game world at a specified layer
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();
}
