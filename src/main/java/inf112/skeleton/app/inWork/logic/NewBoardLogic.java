package inf112.skeleton.app.inWork.logic;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.inWork.objects.RobotToken;

public class NewBoardLogic {

    private static final float ROBOT_SPRITE_SCALE = 96;

    // Robot color sprite sheet
    private static final String BLUE_ROBOT = "assets/BlueRobotSpriteSheet.png";
    private static final String GREEN_ROBOT = "assets/GreenRobotSpriteSheet.png";
    private static final String RED_ROBOT = "assets/RedRobotSpriteSheet.png";
    private static final String YELLOW_ROBOT = "assets/YellowRobotSpriteSheet.png";

    private ArrayList<RobotToken> robotList;
    private NewMovePlayer moveRobotBrain;
    private NewMoveConveyorBelts moveConveyorBelts;

    private TiledMap map;

    public NewBoardLogic(TiledMap map) {
        this.map = map;
        this.robotList = new ArrayList<RobotToken>();
        this.moveRobotBrain = new NewMovePlayer(robotList, this);
        this.moveConveyorBelts = new NewMoveConveyorBelts(this, robotList);
    }

    /*
     * Adds player to the board at specified position
     */
    public void addPlayerToBoard(Vector2 startPosition, String givenName) {
        RobotToken newRobot = new RobotToken(givenName, YELLOW_ROBOT, startPosition);
        newRobot.setSize(ROBOT_SPRITE_SCALE, ROBOT_SPRITE_SCALE);
        robotList.add(newRobot);
        moveRobotBrain.updatePlayersList(robotList);
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

    private RobotToken getRobotByName(String robotName) {
        for(RobotToken robot : robotList) {
            if(robot.getName().equals(robotName))
                return robot;

        }
        return null;
    }

    public ArrayList<RobotToken> getRobotList() {
        return robotList;
    }

    public Vector2 getRobotLocation(String name) {
        RobotToken robot = getRobotByName(name);
        return new Vector2 (robot.getXPosition(), robot.getYPosition());
    }

    public Direction getRobotRotation(String name) {
        RobotToken robot = getRobotByName(name);
        return robot.getDirection();
    }

    /*
     * Moves all players standing on conveyer belts
     */
    public void moveConveyorBelts() {
        moveConveyorBelts.processFeature();
    }

    /*
     * Moves the player one unit in the direction specified
     */
    public boolean moveRobot(String name, Direction directionToMove) {
        return moveRobotBrain.moveRobot(directionToMove, getRobotByName(name));
    }

    /*
     * Rotates player 90 degrees clockwise for each numberOfTimes. 90 degrees counterclockwise when numberOfTimes is negative.
     */
    public void rotateRobot(String name, int numberOfTimes) {
        RobotToken robot = getRobotByName(name);
        robot.rotatePlayer(numberOfTimes);
    }
}
