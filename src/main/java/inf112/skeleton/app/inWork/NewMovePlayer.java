package inf112.skeleton.app.inWork;

import inf112.skeleton.app.Direction;

import java.util.ArrayList;

public class NewMovePlayer {

    private NewBoardLogic board;
    private ArrayList<RobotToken> robotList;

    public NewMovePlayer(ArrayList<RobotToken> robotList, NewBoardLogic board) {
        this.robotList = robotList;
        this.board = board;
    }

    public void updatePlayersList(ArrayList<RobotToken> robotList) {
        this.robotList = robotList;
    }

    public boolean moveRobot(Direction directionToMove, RobotToken robotToMove) {
        int xPos = robotToMove.getXPosition();
        int yPos = robotToMove.getYPosition();

        switch(directionToMove) {
            case EAST:
                if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallEast")
                        || board.cellContainsLayerWithKey(xPos + 1, yPos, "Wall", "wallWest")) {
                    return false;
                }
                if(!moveOtherRobots(xPos + 1, yPos, directionToMove))
                    return false;
                robotToMove.moveDirection(Direction.EAST);
                return true;

            case NORTH:
                if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallNorth") ||
                        board.cellContainsLayerWithKey(xPos, yPos + 1, "Wall", "wallSouth")) {
                    return false;
                }
                if(!moveOtherRobots(xPos, yPos + 1, directionToMove))
                    return false;
                robotToMove.moveDirection(Direction.NORTH);
                return true;

            case SOUTH:
                if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallSouth") ||
                        board.cellContainsLayerWithKey(xPos, yPos - 1, "Wall", "wallNorth")) {
                    return false;
                }
                if(!moveOtherRobots(xPos, yPos - 1, directionToMove)) {
                    return false;
                }
                robotToMove.moveDirection(Direction.SOUTH);
                return true;

            case WEST:
                if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallWest") ||
                        board.cellContainsLayerWithKey(xPos - 1, yPos, "Wall", "wallEast")) {
                    return false;
                }
                if(!moveOtherRobots(xPos - 1, yPos, directionToMove)) {
                    return false;
                }
                robotToMove.moveDirection(Direction.WEST);
                return true;
        }
        return false;
    }

    private boolean moveOtherRobots(int xPos, int yPos, Direction directionToMove) {
        for(RobotToken robot : robotList) {
            if(robot.getXPosition() == xPos && robot.getYPosition() == yPos) {
                return moveRobot(directionToMove, robot);
            }
        }
        return true;
    }
}
