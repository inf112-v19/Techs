package inf112.skeleton.app.logic;

import java.util.ArrayList;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.PlayerToken;

public class BoardLogic {

    private static final float ROBOT_SPRITE_SCALE = 96;

    // Robot color sprite sheet
    private static final String BLUE_ROBOT = "assets/BlueRobotSpriteSheet.png";
    private static final String GREEN_ROBOT = "assets/GreenRobotSpriteSheet.png";
    private static final String RED_ROBOT = "assets/RedRobotSpriteSheet.png";
    private static final String YELLOW_ROBOT = "assets/YellowRobotSpriteSheet.png";
    
    private ArrayList<PlayerToken> playersList;
    private MovePlayer movePlayerBrain;
    private MoveConveyorBelts moveConveyorBelts;
    
    private TiledMap map;
    
    public BoardLogic(TiledMap map) {
        this.map = map;
        this.playersList = new ArrayList<PlayerToken>();
        this.movePlayerBrain = new MovePlayer(playersList, this);
        this.moveConveyorBelts = new MoveConveyorBelts(this, playersList);
    }

    // Adds player to the board at specified position
    public void addPlayerToBoard(Vector2 startPosition, String givenName) {
        PlayerToken newPlayer = new PlayerToken(givenName, YELLOW_ROBOT, startPosition);
        newPlayer.setSize(ROBOT_SPRITE_SCALE, ROBOT_SPRITE_SCALE);
        playersList.add(newPlayer);
        movePlayerBrain.updatePlayersList(playersList);
    }

     // Checks if tile at (xPos, yPos) is in the specified layer
    public boolean cellContainsLayer(int xPos, int yPos, String layer) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        return tileLayer.getCell(xPos, yPos) != null;
    }

    // Checks if tile at (xPos, yPos) has a property named key in the specified layer
    public boolean cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(tileLayer.getCell(xPos, yPos) == null)
            return false;
        return tileLayer.getCell(xPos, yPos).getTile().getProperties().containsKey(key);
    }

    private PlayerToken getPlayerByName(String playerName) {
        for(PlayerToken player : playersList) {
            if(player.getName().equals(playerName))
                return player;

        }
        return null;
    }
    public ArrayList<PlayerToken> getPlayersList() {
        return playersList;
    }
    public Vector2 getPlayerLocation(String name) {
        PlayerToken player = getPlayerByName(name);
        return new Vector2 (player.getXPosition(), player.getYPosition());
    }
    public Direction getPlayerRotation(String name) {
        PlayerToken player = getPlayerByName(name);
        return player.getDirection();
    }

     // Moves all players standing on conveyer belts
    public void moveConveyorBelts() {
        moveConveyorBelts.processFeature();
    }

    // Moves the player one unit in the direction specified
    public boolean movePlayer(String name, Direction directionToMove) {
        return movePlayerBrain.movePlayer(directionToMove, getPlayerByName(name));
    }
    
    // Rotates player 90 degrees clockwise for each numberOfTimes. 90 degrees counterclockwise when numberOfTimes is negative.
    public void rotatePlayer(String name, int numberOfTimes) {
        PlayerToken player = getPlayerByName(name);
        player.rotatePlayer(numberOfTimes);
    }
}
