package inf112.skeleton.app;

import java.util.ArrayList;

public class MoveConveyorBelts implements IBoardFeature {

    private Board board;
    private ArrayList<RobotToken> playersList;
    private String layerName = "Conveyor";
    private int xPos;
    private int yPos;
    
    public MoveConveyorBelts(BoardLogic board, ArrayList<RobotToken> playersList) {
        this.playersList = playersList;
    }
    
    @Override
    public void processFeature() {
        for(RobotToken player : playersList) {
            xPos = player.getXPosition();
            yPos = player.getYPosition();
            
            if(board.cellContainsLayer(xPos, yPos, layerName)) {
                if(checkDirection(Direction.NORTH, board)) {
                    board.movePlayer(player.getName(), Direction.NORTH);
                } else if (checkDirection(Direction.EAST, board)) {
                    board.movePlayer(player.getName(), Direction.EAST);
                } else if (checkDirection(Direction.SOUTH, board)) {
                    board.movePlayer(player.getName(), Direction.SOUTH);
                } else if (checkDirection(Direction.WEST, board)) {
                    board.movePlayer(player.getName(), Direction.WEST);
                }
            }
        }
    }

    private boolean checkDirection(Direction dir, Board board) {
        return board.cellContainsLayerWithKey(xPos, yPos, layerName, dir.toString());
    }
}
