package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;

public class MovePlayer {

    private BoardLogic board;
    private ArrayList<PlayerToken> playersList;
    
    public MovePlayer(ArrayList<PlayerToken> playersList, BoardLogic board) {
        this.playersList = playersList;
        this.board = board;
    }
    
    public boolean movePlayer(Direction directionToMove, PlayerToken playerToMove) {
        int xPos = playerToMove.getXPosition();
        int yPos = playerToMove.getYPosition();
        
        
        switch(directionToMove) {
        case EAST:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallEast") 
                || board.cellContainsLayerWithKey(xPos + 1, yPos, "Wall", "wallWest")) {
                return false;
            }
            if(!moveOtherPlayers(xPos + 1, yPos, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.EAST);            
            return true;
            
        case NORTH:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallNorth") || 
                board.cellContainsLayerWithKey(xPos, yPos + 1, "Wall", "wallSouth")) {
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos + 1, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.NORTH);
            return true;
            
        case SOUTH:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallSouth") || 
                board.cellContainsLayerWithKey(xPos, yPos - 1, "Wall", "wallNorth")) {
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos - 1, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.SOUTH);
            return true;
            
        case WEST:
            if(board.cellContainsLayerWithKey(xPos, yPos, "Wall", "wallWest") || 
                board.cellContainsLayerWithKey(xPos - 1, yPos, "Wall", "wallEast")) {
                return false;
            }
            if(!moveOtherPlayers(xPos - 1, yPos, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.WEST);
            return true;       
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
    public void updatePlayersList(ArrayList<PlayerToken> playersList) {
        this.playersList = playersList;
    }
}
