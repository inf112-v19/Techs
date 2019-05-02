package inf112.skeleton.app.logic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.PlayerToken;
import java.util.ArrayList;

public class Lasers implements IBoardFeature {

    private BoardLogic boardLogic;
    private MapProperties prop;
    private ArrayList<PlayerToken> playerList;
    private ArrayList<Vector2> lasers;

    public Lasers (BoardLogic boardLogic, ArrayList<PlayerToken> playerList, MapProperties prop) {
        this.boardLogic = boardLogic;
        this.playerList = playerList;
        this.prop = prop;
        this.lasers = new ArrayList<>();
        int mapHeight = boardLogic.getProperties().get("height", Integer.class);
        int mapWidth = boardLogic.getProperties().get("width", Integer.class);

        for (int x = 0; x <= mapWidth; x++) {
            for (int y = 0; y <= mapHeight; y++) {
                if (boardLogic.cellContainsLayer(x, y, "FromLaser")) {
                    lasers.add(new Vector2(x, y));
                }
            }
        }
    }

    @Override
    public void processFeature() {
        shootLasersOnBoard();
    }

    private void shootLasersOnBoard() {

        for (Vector2 laserShot : lasers) {
            int x = (int) laserShot.x;
            int y = (int) laserShot.y;

            if (boardLogic.cellContainsLayerWithKey(x, y, "FromLaser", "LaserNorth")) {
                shootLaserFromBoardLasers(laserShot, Direction.NORTH);
            }
            if (boardLogic.cellContainsLayerWithKey(x, y, "FromLaser", "LaserEast")) {
                shootLaserFromBoardLasers(laserShot, Direction.EAST);
            }
            if (boardLogic.cellContainsLayerWithKey(x, y, "FromLaser", "LaserSouth")) {
                shootLaserFromBoardLasers(laserShot, Direction.SOUTH);
            }
            if (boardLogic.cellContainsLayerWithKey(x, y, "FromLaser", "LaserWest")) {
                shootLaserFromBoardLasers(laserShot, Direction.WEST);
            }
        }
    }

    /**
     * Shoots laser that exists on the board. Functions differently in that it checks the same tile as the board laser begins for a player.
     * @param fromTilePosition Start location in which the board laser shoots from.
     * @param dir The direction the board laser shoots.
     */
    private void shootLaserFromBoardLasers(Vector2 fromTilePosition, Direction dir) {
        if (fromTilePosition.x < 0 || fromTilePosition.y < 0 ||fromTilePosition.x >= prop.get("width", Integer.class) || fromTilePosition.y >= prop.get("height", Integer.class)) {
            return;
        }

        for (PlayerToken player : playerList) {
            if (player.getVector2Position().equals(fromTilePosition)) {
                player.addDamageToken();
                System.out.println(player.getName() + " got hit and got one damage token. Damage: " + player.getDamageToken());
                return;
            }
        }

        Vector2 nextTile = boardLogic.addDirectionToLocation((int) fromTilePosition.x, (int) fromTilePosition.y, dir);
        int x = (int) nextTile.x;
        int y = (int) nextTile.y;

        // Checks walls in regards to laser that is shot from players.
        switch (dir) {
            case NORTH:
                if (boardLogic.cellContainsLayer(x, y, "WallDown")) { return; }
                break;
            case SOUTH:
                if (boardLogic.cellContainsLayer(x, y, "WallUp")) { return; }
                break;
            case EAST:
                if (boardLogic.cellContainsLayer(x, y, "WallLeft")) { return; }
                break;
            case WEST:
                if (boardLogic.cellContainsLayer(x, y, "WallRight")) { return; }
                break;
        }
        shootLaserFromBoardLasers(nextTile, dir);
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

        Vector2 nextTile = boardLogic.addDirectionToLocation((int) fromTilePosition.x, (int) fromTilePosition.y, dir);
        int x = (int) nextTile.x;
        int y = (int) nextTile.y;

        // Checks walls in regards to laser that is shot from players.
        switch (dir) {
            case NORTH:
                if (boardLogic.cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallUp") || boardLogic.cellContainsLayer(x, y, "WallDown")) { return; }
                break;
            case SOUTH:
                if (boardLogic.cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallDown") || boardLogic.cellContainsLayer(x, y, "WallUp")) { return; }
                break;
            case EAST:
                if (boardLogic.cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallRight") || boardLogic.cellContainsLayer(x, y, "WallLeft")) { return; }
                break;
            case WEST:
                if (boardLogic.cellContainsLayer((int) fromTilePosition.x, (int) fromTilePosition.y, "WallLeft") || boardLogic.cellContainsLayer(x, y, "WallRight")) { return; }
                break;
        }

        for (PlayerToken player : boardLogic.getPlayersList()) {
            if (player.getVector2Position().equals(nextTile)) {
                player.addDamageToken();
                System.out.println(player.getName() + " got hit and got one damage token. Damage: " + player.getDamageToken());
                return;
            }
        }
        shootLaserFromTile(nextTile, dir);
    }
}
