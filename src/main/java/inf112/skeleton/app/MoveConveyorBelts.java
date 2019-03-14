package inf112.skeleton.app;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class MoveConveyorBelts implements IBoardFeature {

    private Board board;
    private ArrayList<PlayerToken> playersList;
    private ArrayList<String> playersChecked;
    private String layerName = "Conveyor";
    private int xPos;
    private int yPos;
    
    public MoveConveyorBelts(BoardLogic board, ArrayList<PlayerToken> playersList) {
        this.playersList = playersList;
    }
    
    @Override
    public void processFeature() {
        playersChecked = new ArrayList<String>();
        for(PlayerToken player : playersList) {
            xPos = player.getXPosition();
            yPos = player.getYPosition();
            
            if(board.cellContainsLayer(xPos, yPos, layerName)) {
                if(checkDirection(Direction.NORTH, board)) {
                    if(checkForPlayer(Direction.NORTH)) {
                        
                    }
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
    
    private PlayerToken checkForPlayer(Direction dir) {        
        Vector2 checkPosition = addDirectionToLocation(xPos, yPos, dir);

        for(PlayerToken player : playersList) {
            if(player.getVector2Position().equals(checkPosition)) {
                return player;
            }
        }
        return null;
    }
    
    private Vector2 addDirectionToLocation(int x, int y, Direction dir) {
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
}
