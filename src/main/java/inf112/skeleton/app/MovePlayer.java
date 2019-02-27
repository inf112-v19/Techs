package inf112.skeleton.app;

import java.util.ArrayList;

public class MovePlayer {

    private Board board;
    private ArrayList<PlayerToken> playersList;
    
    public MovePlayer(Board board, ArrayList<PlayerToken> playersList) {
        this.board = board;
        this.playersList = playersList;
    }
    
    public void updatePlayersList(ArrayList<PlayerToken> playersList) {
        this.playersList = playersList;
    }
    
    public boolean movePlayer(Direction directionToMove, PlayerToken playerToMove) {
        int xPos = playerToMove.getXPosition();
        int yPos = playerToMove.getYPosition();
        
        switch(directionToMove) {
        case EAST:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallEast") != null 
                || board.cellContainsLayerWithKey(xPos + 1, yPos, "Wall", "wallWest") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos + 1, yPos, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.EAST);            
            return true;
            
        case NORTH:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallNorth") != null || 
                board.cellContainsLayerWithKey(xPos, yPos + 1, "Wall", "wallSouth") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos + 1, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.NORTH);
            return true;
            
        case SOUTH:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallSouth") != null || 
                board.cellContainsLayerWithKey(xPos, yPos - 1, "Wall", "wallNorth") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos - 1, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.SOUTH);
            return true;
            
        case WEST:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallWest") != null || 
                board.cellContainsLayerWithKey(xPos - 1, yPos, "Wall", "wallEast") != null) {
                return false;
            }
            if(!moveOtherPlayers(xPos - 1, yPos, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.WEST);
            return true;
            
        default:
            break;
        
        }        
        return false;
    }
    
    private boolean moveOtherPlayers(int xPos, int yPos, Direction directionToMove) {
        for(PlayerToken player : playersList) {
            if(player.getXPosition() == xPos && player.getYPosition() == yPos) {
                return movePlayer(directionToMove, player);
            }
        }
        return true;
    }
}
