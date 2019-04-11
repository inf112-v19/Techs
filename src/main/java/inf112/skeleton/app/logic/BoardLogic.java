package inf112.skeleton.app.logic;

import java.util.ArrayList;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.PlayerToken;

public class BoardLogic {

    private static final float ROBOT_SPRITE_SCALE = 96;

    // Robot color sprite sheet
    private static final String ROBOT_SPRITE_SHEET_BLUE = "assets/BlueRobotSpriteSheet.png";
    private static final String ROBOT_SPRITE_SHEET_GREEN = "assets/GreenRobotSpriteSheet.png";
    private static final String ROBOT_SPRITE_SHEET_RED = "assets/RedRobotSpriteSheet.png";
    private static final String ROBOT_SPRITE_SHEET_YELLOW = "assets/YellowRobotSpriteSheet.png";
    
    private ArrayList<PlayerToken> playersList;
    private MovePlayer movePlayerBrain;
    private MoveConveyorBelts moveConveyorBelts;
    private ProcessCheckpoints processCheckpoints;
    private RotateWheel moveRotateWheel;
    
    private TiledMap map;
    
    public BoardLogic(TiledMap map) {
        this.map = map;
        this.playersList = new ArrayList<PlayerToken>();
        this.movePlayerBrain = new MovePlayer(playersList, this);
        this.moveConveyorBelts = new MoveConveyorBelts(this, playersList);
        this.processCheckpoints = new ProcessCheckpoints(this, playersList);
        this.moveRotateWheel = new RotateWheel(this, playersList);
    }

    // Adds player to the board at specified position
    public void addPlayerToBoard(Vector2 startPosition, String givenName) {
        PlayerToken newPlayer = new PlayerToken(givenName, ROBOT_SPRITE_SHEET_RED, startPosition);
        newPlayer.setSize(ROBOT_SPRITE_SCALE, ROBOT_SPRITE_SCALE);
        playersList.add(newPlayer);
        movePlayerBrain.updatePlayersList(playersList);
    }

     // Checks if tile at (xPos, yPos) is in the specified layer
    public boolean cellContainsLayer(int xPos, int yPos, String layer) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(tileLayer == null) {
            return false;
        }
        return tileLayer.getCell(xPos, yPos) != null;
    }

    // Checks if tile at (xPos, yPos) has a property named key in the specified layer
    public boolean cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(tileLayer == null) {
            return false;
        }
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
        return player.getFacingDirection();
    }

    // Moves all players standing on rotating-left wheel
    public void moveRotateWheel() {
        moveRotateWheel.processFeature();
    }

     // Moves all players standing on conveyer belts
    public void moveConveyorBelts() {
        moveConveyorBelts.processFeature();
    }

    // Moves the player one unit in the direction specified
    public boolean movePlayer(String name, Direction directionToMove) {
        return movePlayerBrain.movePlayer(directionToMove, getPlayerByName(name));
    }

    public boolean movePlayerForward(String name) {
        PlayerToken player = getPlayerByName(name);
        return movePlayerBrain.movePlayer(player.getFacingDirection(), player);
    }

    public boolean movePlayerBackwards(String name) {
        PlayerToken player = getPlayerByName(name);
        return movePlayerBrain.movePlayer(getOppositeDirection(player.getFacingDirection()), player);
    }

    private Direction getOppositeDirection(Direction dir) {
        int directionNumber = dir.ordinal() + 2;
        return Direction.values()[directionNumber % 4];
    }

    // Rotates player 90 degrees clockwise for each numberOfTimes. 90 degrees counterclockwise when numberOfTimes is negative.
    public void rotatePlayer(String name, int numberOfTimes) {
        PlayerToken player = getPlayerByName(name);
        player.rotatePlayer(numberOfTimes);
    }

    public void checkAllCheckpoints() {
        processCheckpoints.processFeature();
    }

    // Calculates the correct vector2 of the tile in direction dir from a position
    protected Vector2 addDirectionToLocation(int x, int y, Direction dir) {
        switch(dir) {
            case NORTH:
                return new Vector2(x, y + 1);
            case EAST:
                return new Vector2(x + 1, y);
            case SOUTH:
                return new Vector2(x, y - 1);
            case WEST:
                return new Vector2(x - 1, y);
            default:
                return null;
        }
    }

    public void shootPlayerLaser() {
        for(PlayerToken player : playersList) {
            shootLaserFromTile(player.getVector2Position(), player.getFacingDirection());
        }
    }
    public void shootLaserFromTile(Vector2 fromTilePosition, Direction dir) {
        if(fromTilePosition.x <= 0 || fromTilePosition.y <= 0 ||fromTilePosition.x > 12 || fromTilePosition.y > 16) { return; }

        Vector2 nextTile = addDirectionToLocation((int) fromTilePosition.x, (int) fromTilePosition.y, dir);
        int x = (int) nextTile.x;
        int y = (int) nextTile.y;

        // Checks walls in regards to laser that is shot from players.
        switch (dir) {
            case NORTH:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallUp") ||cellContainsLayer(x, y, "WallDown")) { return; }
                break;
            case SOUTH:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallDown") ||cellContainsLayer(x, y, "WallUp")) { return; }
                break;
            case EAST:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallRight") ||cellContainsLayer(x, y, "WallLeft")) { return; }
                break;
            case WEST:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallLeft") ||cellContainsLayer(x, y, "WallRight")) { return; }
                break;
        }

        for (PlayerToken player : playersList) {
            if (player.getVector2Position().equals(nextTile)) {
                player.addDamageToken();
                System.out.println(player.getName() + " got hit and got one damage token. Damage: " + player.getDamageToken());
                return;
            }
        }
        shootLaserFromTile(nextTile, dir);
    }
    
    public void checkForDamageCleanup() {
    	for (PlayerToken player : playersList) {
    		player.checkForDamageCleanUp();
    	}
    }
}
