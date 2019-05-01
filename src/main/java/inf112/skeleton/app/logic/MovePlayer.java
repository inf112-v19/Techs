package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;

public class MovePlayer {

    private BoardLogic board;
    private ArrayList<PlayerToken> playersList;
    private PitFall pitfall;
    
    public MovePlayer(ArrayList<PlayerToken> playersList, BoardLogic board) {
        this.playersList = playersList;
        this.board = board;
    }
    
    public boolean movePlayer(Direction directionToMove, PlayerToken playerToMove) {
        int xPos = playerToMove.getXPosition();
        int yPos = playerToMove.getYPosition();

        pitfall = new PitFall(board, playersList);

        playerToMove.setRecentlyBackuped(false);
        switch(directionToMove) {
        case EAST:
            if(board.cellContainsLayerWithKey(xPos, yPos, "WallRight", "wallEast")
                || board.cellContainsLayerWithKey(xPos + 1, yPos, "WallLeft", "wallWest")) {
                System.out.println(playerToMove.getName() + " couldn't move because of wall.");
                return false;
            }
            if(!moveOtherPlayers(xPos + 1, yPos, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.EAST);
            System.out.println(playerToMove.getName() + " moved right and its direction is " + playerToMove.getFacingDirection());
            pitfall.processFeature();
            return true;
            
        case NORTH:
            if(board.cellContainsLayerWithKey(xPos, yPos, "WallUp", "wallNorth") ||
                board.cellContainsLayerWithKey(xPos, yPos + 1, "WallDown", "wallSouth")) {
                System.out.println(playerToMove.getName() + " couldn't move because of wall.");
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos + 1, directionToMove))
                return false;
            playerToMove.moveDirection(Direction.NORTH);
            System.out.println(playerToMove.getName() + " moved up and its direction is " + playerToMove.getFacingDirection());
            pitfall.processFeature();
            return true;
            
        case SOUTH:
            if(board.cellContainsLayerWithKey(xPos, yPos, "WallDown", "wallSouth") ||
                board.cellContainsLayerWithKey(xPos, yPos - 1, "WallUp", "wallNorth")) {
                System.out.println(playerToMove.getName() + " couldn't move because of wall.");
                return false;
            }
            if(!moveOtherPlayers(xPos, yPos - 1, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.SOUTH);
            System.out.println(playerToMove.getName() + " moved down and its direction is " + playerToMove.getFacingDirection());
            pitfall.processFeature();
            return true;
            
        case WEST:
            if(board.cellContainsLayerWithKey(xPos, yPos, "WallLeft", "wallWest") ||
                board.cellContainsLayerWithKey(xPos - 1, yPos, "WallRight", "wallEast")) {
                System.out.println(playerToMove.getName() + " couldn't move because of wall.");
                return false;
            }
            if(!moveOtherPlayers(xPos - 1, yPos, directionToMove)) {
                return false;
            }
            playerToMove.moveDirection(Direction.WEST);
            System.out.println(playerToMove.getName() + " moved left and its direction is " + playerToMove.getFacingDirection());
            pitfall.processFeature();
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
