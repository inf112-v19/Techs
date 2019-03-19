package inf112.skeleton.app;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class BoardLogic {

    private MovePlayer movePlayerBrain;
    private ArrayList<PlayerToken> playersList;
    private MoveConveyorBelts moveConveyorBelts;

    private TiledMap map;
    Sprite robotSprite;
    float robotSpriteScale;

    public BoardLogic(Sprite robotSprite, float robotSpriteScale, TiledMap map) {
        this.map = map;
        this.robotSprite = robotSprite;
        this.robotSpriteScale = robotSpriteScale;
        this.playersList = new ArrayList<PlayerToken>();
        this.movePlayerBrain = new MovePlayer(playersList, this);
        this.moveConveyorBelts = new MoveConveyorBelts(this, playersList);
    }

    public ArrayList<PlayerToken> getPlayersList() {
        return playersList;
    }

    /*
     * Adds player to the board at specified position
     */
    public void addPlayerToBoard(Vector2 startPosition, String playerName) {
        PlayerToken newPlayer = new PlayerToken(robotSprite, playerName, startPosition, robotSpriteScale);
        newPlayer.setSize(robotSpriteScale, robotSpriteScale);
        playersList.add(newPlayer);
        movePlayerBrain.updatePlayersList(playersList);
    }

    /*
     * Checks if tile at (xPos, yPos) has a property named key in the specified layer
     */
    public boolean cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(tileLayer.getCell(xPos, yPos) == null)
            return false;
        return tileLayer.getCell(xPos, yPos).getTile().getProperties().containsKey(key);
    }

    /*
     * Moves all players standing on conveyer belts
     */
    public void moveConveyorBelts() {
        moveConveyorBelts.processFeature();
    }

    public Direction getPlayerRotation(String name) {
        PlayerToken player = getPlayerByName(name);
        return player.getDirection();
    }

    public Vector2 getPlayerLocation(String name) {
        PlayerToken player = getPlayerByName(name);
        return new Vector2 (player.getXPosition(), player.getYPosition());
    }

    private PlayerToken getPlayerByName(String playerName) {
        for(PlayerToken player : playersList) {
            if(player.getName().equals(playerName))
                return player;

        }
        return null;
    }

    /*
     * Moves the player one unit in the direction specified
     */
    public boolean movePlayer(String name, Direction directionToMove) {
        return movePlayerBrain.movePlayer(directionToMove, getPlayerByName(name));
    }

    /*
     * Rotates player 90 degrees clockwise for each numberOfTimes. 90 degrees counterclockwise when numberOfTimes is negative.
     */
    public void rotatePlayer(String name, int numberOfTimes) {
        PlayerToken player = getPlayerByName(name);
        player.rotatePlayer(numberOfTimes);
    }
}