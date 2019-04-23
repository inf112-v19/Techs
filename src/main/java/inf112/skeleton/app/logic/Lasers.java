package inf112.skeleton.app.logic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.PlayerToken;
import java.util.ArrayList;

public class Lasers implements IBoardFeature {

    private BoardLogic boardLogic;

    private ArrayList<Vector2> lasers;
    private int mapHeight;
    private int mapWidth;

    public Lasers (BoardLogic boardLogic) {
        this.boardLogic = boardLogic;
        this.lasers = new ArrayList<>();
        this.mapHeight = boardLogic.getProperties().get("height", Integer.class);
        this.mapWidth = boardLogic.getProperties().get("width", Integer.class);

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
                boardLogic.shootLaserFromTile(laserShot, Direction.NORTH);
            }
            if (boardLogic.cellContainsLayerWithKey(x, y, "FromLaser", "LaserEast")) {
                boardLogic.shootLaserFromTile(laserShot, Direction.EAST);
            }
            if (boardLogic.cellContainsLayerWithKey(x, y, "FromLaser", "LaserSouth")) {
                boardLogic.shootLaserFromTile(laserShot, Direction.SOUTH);
            }
            if (boardLogic.cellContainsLayerWithKey(x, y, "FromLaser", "LaserWest")) {
                boardLogic.shootLaserFromTile(laserShot, Direction.WEST);
            }
        }
    }
}
