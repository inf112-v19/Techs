package inf112.skeleton.app.inWork.logic;

import inf112.skeleton.app.inWork.objects.RobotToken;
import inf112.skeleton.app.inWork.screens.GameScreen;
import inf112.skeleton.app.logic.Direction;
import inf112.skeleton.app.logic.IBoardFeature;

import java.util.ArrayList;

public class NewMoveConveyorBelts implements IBoardFeature {

    private GameScreen board;
    private ArrayList<RobotToken> robotList;
    private String layerName = "Conveyor";
    private int xPos;
    private int yPos;

    public NewMoveConveyorBelts(NewBoardLogic board, ArrayList<RobotToken> robotList) {
        this.robotList = robotList;
    }

    @Override
    public void processFeature() {
        for(RobotToken robot : robotList) {
            xPos = robot.getXPosition();
            yPos = robot.getYPosition();

            if(board.cellContainsLayer(xPos, yPos, layerName)) {
                if(checkDirection(Direction.NORTH, board)) {
                    board.moveRobot(robot.getName(), Direction.NORTH);
                } else if (checkDirection(Direction.EAST, board)) {
                    board.moveRobot(robot.getName(), Direction.EAST);
                } else if (checkDirection(Direction.SOUTH, board)) {
                    board.moveRobot(robot.getName(), Direction.SOUTH);
                } else if (checkDirection(Direction.WEST, board)) {
                    board.moveRobot(robot.getName(), Direction.WEST);
                }
            }
        }
    }

    private boolean checkDirection(Direction dir, GameScreen board) {
        return board.cellContainsLayerWithKey(xPos, yPos, layerName, dir.toString());
    }
}