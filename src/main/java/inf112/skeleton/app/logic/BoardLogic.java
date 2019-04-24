package inf112.skeleton.app.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
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
    private Lasers lasers;
    
    private TiledMap map;
    private MapProperties prop;
    
    public BoardLogic(TiledMap map) {
        this.map = map;
        this.prop = map.getProperties();
        this.playersList = new ArrayList<>();
        this.movePlayerBrain = new MovePlayer(playersList, this);
        this.moveConveyorBelts = new MoveConveyorBelts(this, playersList);
        this.processCheckpoints = new ProcessCheckpoints(this, playersList);
        this.lasers = new Lasers(this, playersList, prop);
    }

    /**
     * The lasers on the board will activate. If any player is in its trajectory, those players will get one damage token.
     */
    public void activateLasersOnBoard() {
        lasers.processFeature();
    }

    /**
     * Calculates the correct vector2-position of a tile in a direction from given position.
     * @param x X-position from where it calculates
     * @param y Y-position from where it calculates
     * @param dir The direction in which it calculates
     * @return The new vector2-position
     */
    public Vector2 addDirectionToLocation(int x, int y, Direction dir) {
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

    /**
     * Adds a player at a given position.
     * @param startPosition The startposition of the player
     * @param givenName The name of the player
     */
    public void addPlayerToBoard(Vector2 startPosition, String givenName) {
        PlayerToken newPlayer = new PlayerToken(givenName, ROBOT_SPRITE_SHEET_RED, startPosition);
        newPlayer.setSize(ROBOT_SPRITE_SCALE, ROBOT_SPRITE_SCALE);
        playersList.add(newPlayer);
        movePlayerBrain.updatePlayersList(playersList);
    }

    /**
     * Checks if tiles at (xPos, yPos) is in a specified layer.
     * @param xPos X-position on board
     * @param yPos Y-position on board
     * @param layer Name of the layer that is checked
     * @return true if tile exists in layer otherwise false
     */
    public boolean cellContainsLayer(int xPos, int yPos, String layer) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if (tileLayer == null) {
            return false;
        }
        return tileLayer.getCell(xPos, yPos) != null;
    }

    /**
     * Checks if tile at (xPos, yPos) has a property key in the specified layer.
     * @param xPos X-position on board
     * @param yPos Y-position on board
     * @param layer Name of the layer that is checked
     * @param key The key property that is checked
     * @return true if tile has key in layer, otherwise false
     */
    public boolean cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(tileLayer == null) {
            return false;
        }
        if(tileLayer.getCell(xPos, yPos) == null)
            return false;
        return tileLayer.getCell(xPos, yPos).getTile().getProperties().containsKey(key);
    }

    /**
     * Checks if any of the players stands on a checkpoint. Is done at the end of a turn
     */
    public void checkAllCheckpoints() {
        processCheckpoints.processFeature();
    }

    /**
     * Checks if any of the player has full damage and does what is necessary if its true
     */
    public void checkForDamageCleanup() {
        for (PlayerToken player : playersList) {
            player.checkForDamageCleanUp();
        }
    }

    /**
     * Checks if any of the players have no lives/health tokens left.
     * @return True if any player has no lives left, otherwise false
     */
    public void checkPlayersLife() {
        for (PlayerToken player : playersList) {
            if (player.getHealth() <= 0) {
                System.out.println(player.getName() + " has no lives left and the game is over.");
                Gdx.app.exit();
            }
        }
    }

    /**
     * Get the opposite direction of a given direction.
     * @param dir The direction from where the opposite is calculates
     * @return The opposite direction
     */
    private Direction getOppositeDirection(Direction dir) {
        int directionNumber = dir.ordinal() + 2;
        return Direction.values()[directionNumber % 4];
    }

    /**
     * Gets the player given a name
     * @param playerName The name of the player that is searched
     * @return The player
     */
    public PlayerToken getPlayerByName(String playerName) {
        for(PlayerToken player : playersList) {
            if(player.getName().equals(playerName))
                return player;

        }
        return null;
    }

    /**
     * Gets the list of players in the game
     * @return The list of players
     */
    public ArrayList<PlayerToken> getPlayersList() {
        return playersList;
    }

    /**
     * Gets the vector2-position of a player given a name.
     * @param name The name of the player that is searched for
     * @return The position of searched player.
     */
    public Vector2 getPlayerLocation(String name) {
        PlayerToken player = getPlayerByName(name);
        return new Vector2 (player.getXPosition(), player.getYPosition());
    }

    /**
     * Gets the direction of a player, given a name
     * @param name The name of the player
     * @return The direction of the player
     */
    public Direction getPlayerRotation(String name) {
        PlayerToken player = getPlayerByName(name);
        return player.getFacingDirection();
    }

    /**
     * Gets the map properties of the map. These could be width and height of TiledMap
     * @return the map properties
     */
    public MapProperties getProperties() {
        return this.prop;
    }

    /**
     * Moves every player that stands on a conveyor belt accordingly.
     */
    public void moveConveyorBelts() {
        moveConveyorBelts.processFeature();
    }

    /**
     * Moves the player one unit in the specified direction.
     * @param name Name of the player that is moved
     * @param directionToMove Direction the player is going to move
     * @return true if possible to move, otherwise false
     */
    public boolean movePlayer(String name, Direction directionToMove) {
        return movePlayerBrain.movePlayer(directionToMove, getPlayerByName(name));
    }

    /**
     * Moves the player backwards from its facing direction.
     * @param name Name of the player that is moved.
     * @return true if possible to move, otherwise false
     */
    public boolean movePlayerBackwards(String name) {
        PlayerToken player = getPlayerByName(name);
        return movePlayerBrain.movePlayer(getOppositeDirection(player.getFacingDirection()), player);
    }

    /**
     * Moves the player forward in its facing direction.
     * @param name Name of the player that is moved.
     * @return true if possible to move, otherwise false
     */
    public boolean movePlayerForward(String name) {
        PlayerToken player = getPlayerByName(name);
        return movePlayerBrain.movePlayer(player.getFacingDirection(), player);
    }

    /**
     * Check if any of the players is on a rotating wheel. Rotates player if they are.
     */
    public void moveRotateWheel() {
        for (PlayerToken player : playersList) {
            int x = player.getXPosition();
            int y = player.getYPosition();

            if (cellContainsLayer(x, y, "RotateLeft")) {
                System.out.println(player.getName() + " rotated left.");
                player.rotatePlayer(-1);
            }

            if (cellContainsLayer(x, y, "RotateRight")) {
                System.out.println(player.getName() + " rotated right.");
                player.rotatePlayer(1);
            }
        }
    }

    /**
     * Checks if any of the players stands on a repair tile and removes one damage token if they are.
     */
    public void repairRobots() {
        for (PlayerToken player : playersList) {
            int x = player.getXPosition();
            int y = player.getYPosition();

            if (cellContainsLayer(x, y, "Repair")) {
                player.removeDamageToken();
                System.out.println(player.getName() + " got repaired. Damage: " + player.getDamageToken());
            }
        }
    }

    /**
     * Rotates the player 90 degrees a given number of times.
     * @param name The name of the player that is rotated
     * @param numberOfTimes Less than 0 rotates left, greater than 0 rotates right
     */
    public void rotatePlayer(String name, int numberOfTimes) {
        PlayerToken player = getPlayerByName(name);
        player.rotatePlayer(numberOfTimes);
    }

    /**
     * Checks every tile in given direction until out of board, wall hit or player hit. Player gets one damage token if hit.
     * @param fromTilePosition The position from where the laser is shot
     * @param dir The direction the laser moves.
     */
    public void shootLaserFromTile(Vector2 fromTilePosition, Direction dir) {
        if (fromTilePosition.x < 0 || fromTilePosition.y < 0 ||fromTilePosition.x >= prop.get("width", Integer.class) || fromTilePosition.y >= prop.get("height", Integer.class)) {
            return;
        }

        Vector2 nextTile = addDirectionToLocation((int) fromTilePosition.x, (int) fromTilePosition.y, dir);
        int x = (int) nextTile.x;
        int y = (int) nextTile.y;

        // Checks walls in regards to laser that is shot from players.
        switch (dir) {
            case NORTH:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallUp") || cellContainsLayer(x, y, "WallDown")) { return; }
                break;
            case SOUTH:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallDown") || cellContainsLayer(x, y, "WallUp")) { return; }
                break;
            case EAST:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallRight") || cellContainsLayer(x, y, "WallLeft")) { return; }
                break;
            case WEST:
                if (cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallLeft") || cellContainsLayer(x, y, "WallRight")) { return; }
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

    /**
     * Makes all the robots shoot in their facing direction.
     */
    public void shootPlayerLaser() {
        for(PlayerToken player : playersList) {
            shootLaserFromTile(player.getVector2Position(), player.getFacingDirection());
        }
    }
}
