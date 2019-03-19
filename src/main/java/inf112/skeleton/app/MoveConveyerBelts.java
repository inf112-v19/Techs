package inf112.skeleton.app;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class MoveConveyerBelts implements IBoardFeature {

    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playersList;
    private String layerName = "Conveyor";
    
    // List of all players that have been moved by conveyer belts this turn 
    private ArrayList<String> playersChecked;
    
    public MoveConveyerBelts(BoardLogic boardLogic, ArrayList<PlayerToken> playersList) {
        this.playersList = playersList;
        this.boardLogic = boardLogic;
    }
    
    @Override
    public void processFeature() {
        playersChecked = new ArrayList<String>();
        
        for(PlayerToken player : playersList) {
            movePlayerIfOnConveyorBelt(player);
        }
    }
    
    private void movePlayerIfOnConveyorBelt(PlayerToken player) {
        if(playersChecked.contains(player.getName())) {
            return;
        }
        
        playersChecked.add(player.getName());
        int xPos = player.getXPosition();
        int yPos = player.getYPosition();
        
        if(boardLogic.cellContainsLayer(xPos, yPos, layerName)) {
            for(Direction dir : Direction.values()) {
                if(checkDirection(player, dir, xPos, yPos)) {
                    boardLogic.movePlayer(player.getName(), dir);
                    checkRotation(xPos, yPos, player);
                    return;
                }
            }
        }
    }

    /*
     * Checks if there is a conveyer belt in direction dir on the tile the player is standing. 
     * If a player stands in its path, it will first process that player.
     */
    private boolean checkDirection(PlayerToken player, Direction dir, int xPos, int yPos) {
        if(boardLogic.cellContainsLayerWithKey(xPos, yPos, layerName, dir.toString())) {
            PlayerToken otherPlayer = checkForPlayer(dir, xPos, yPos);
            if(otherPlayer != null) {
                movePlayerIfOnConveyorBelt(otherPlayer);
            }
            return true;
        }
        return false;
    }
    
    /*
     * Checks if the player has a new position, then rotates it if needed
     */
    private void checkRotation(int startX, int startY, PlayerToken player) {
        if(startX != player.getXPosition() || startY != player.getYPosition()) {
            if(checkSpecificRotation(player, "LEFT")) {
                player.rotatePlayer(-1);
            } else if (checkSpecificRotation(player, "RIGHT")) {
                player.rotatePlayer(1);
            }
        }
    }
    
    /*
     * Checks if the tile the player is standing on has a conveyer belt that rotates in the specified rotation
     */
    private boolean checkSpecificRotation(PlayerToken player, String direction) {
        return boardLogic.cellContainsLayerWithKey(player.getXPosition(), player.getYPosition(), layerName, direction);
    }
    
    /*
     * Returns PlayerToken that stand on the tile in the direction dir
     */
    private PlayerToken checkForPlayer(Direction dir, int xPos, int yPos) {        
        Vector2 checkPosition = addDirectionToLocation(xPos, yPos, dir);

        for(PlayerToken player : playersList) {
            if(player.getVector2Position().equals(checkPosition)) {
                return player;
            }
        }
        return null;
    }
    
    /*
     * Calculates the correct vector2 of the tile in direction dir from a position 
     */
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