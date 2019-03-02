package inf112.skeleton.app.inWork.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.PlayerToken;
import inf112.skeleton.app.inWork.BoardGame;
import inf112.skeleton.app.inWork.robots.Robot;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GameScreen implements Screen {
    public static final float ZOOM_SPEED = 0.03f;
    public static final float MOVE_SPEED = 7;

    private Robot greenRobot;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    float elapsed;

    private ArrayList<Robot> robotList;

    BoardGame game;

    public GameScreen(BoardGame game) {
        this.game = game;
    }


    @Override
    public void show() {
        greenRobot = new Robot("Player_One", 0,0);
        map = new TmxMapLoader().load("assets/RoboRallyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BoardGame.WIDTH, BoardGame.HEIGHT);
        robotList = new ArrayList<Robot>();

    }

    @Override
    public void render(float delta) {
        elapsed += Gdx.graphics.getDeltaTime();
        renderer.setView(camera);
        renderer.render();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(greenRobot.getGreenRobot().getKeyFrame(elapsed), greenRobot.getPosition().x, greenRobot.getPosition().y,96,96);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            camera.zoom += ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            camera.zoom -= ZOOM_SPEED;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0,MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0,-MOVE_SPEED,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-MOVE_SPEED,0,0);
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(MOVE_SPEED,0,0);
            camera.update();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void addRobotToBoard(Vector2 startPosition, String playerName) {
        Robot newPlayer = new Robot(playerName, 0, 0);
        newPlayer.setSize(96, 96);
        robotList.add(newPlayer);
    }

    public boolean moveRobot(Direction directionToMove, String robot) {
        Robot playerToMove = getPlayerByName(robot);
        int xPos = playerToMove.getXPosition();
        int yPos = playerToMove.getYPosition();

        switch(directionToMove) {
            case EAST:
                if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallEast") != null
                        || cellContainsLayerWithKey(xPos + 1, yPos, "Wall", "wallWest") != null) {
                    return false;
                }
                if(!moveOtherRobot(xPos + 1, yPos, robot, directionToMove))
                    return false;
                playerToMove.moveDirection(Direction.EAST);
                return true;

            case NORTH:
                if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallNorth") != null ||
                        cellContainsLayerWithKey(xPos, yPos + 1, "Wall", "wallSouth") != null) {
                    return false;
                }
                if(!moveOtherRobot(xPos, yPos + 1, robot, directionToMove))
                    return false;
                playerToMove.moveDirection(Direction.NORTH);
                return true;

            case SOUTH:
                if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallSouth") != null ||
                        cellContainsLayerWithKey(xPos, yPos - 1, "Wall", "wallNorth") != null) {
                    return false;
                }
                if(!moveOtherRobot(xPos, yPos - 1, robot, directionToMove)) {
                    return false;
                }
                playerToMove.moveDirection(Direction.SOUTH);
                return true;

            case WEST:
                if(cellContainsLayerWithKey(xPos, yPos, "Wall", "wallWest") != null ||
                        cellContainsLayerWithKey(xPos - 1, yPos, "Wall", "wallEast") != null) {
                    return false;
                }
                if(!moveOtherRobot(xPos - 1, yPos, robot, directionToMove)) {
                    return false;
                }
                playerToMove.moveDirection(Direction.WEST);
                return true;

            default:
                break;

        }
        return false;
    }

    /*
     * Moves player standing at (xPos, yPos) to the given direction
     */
    private boolean moveOtherRobot(int xPos, int yPos, String robotToMove, Direction directionToMove) {
        for(Robot robot : robotList) {
            if(robot.getXPosition() == xPos && robot.getYPosition() == yPos) {
                return moveRobot(directionToMove, robot.getRobot());
            }
        }
        return true;
    }

    /*
     * Temporary, to move two players on the board
     */
    private void movePlayerOneAndTwo() {
        // As proof of concept:
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            moveRobot(Direction.NORTH, "Player_One");
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            moveRobot(Direction.SOUTH, "Player_One");
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            moveRobot(Direction.EAST, "Player_One");
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            moveRobot(Direction.WEST, "Player_One");
        }

        /*if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            moveRobot(Direction.NORTH, "Player_Two");
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            moveRobot(Direction.SOUTH, "Player_Two");
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            moveRobot(Direction.EAST, "Player_Two");
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            moveRobot(Direction.WEST, "_layer_Two");
        }*/
    }

    private Object cellContainsLayerWithKey(int xPos, int yPos, String layer, String key) {
        TiledMapTileLayer wallLayer = (TiledMapTileLayer) map.getLayers().get(layer);
        if(wallLayer.getCell(xPos, yPos) == null)
            return null;
        return wallLayer.getCell(xPos, yPos).getTile().getProperties().get(key);
    }

    private Robot getPlayerByName(String robotName) {
        for(Robot robot : robotList) {
            if(robot.getRobot().equals(robotName))
                return robot;
        }
        throw new NoSuchElementException("There is no player in the grid by the name " + robotName);
    }
}
