package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.PlayerToken;
import inf112.skeleton.app.screens.Board;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class MoveConveyorBelts implements IBoardFeature {

    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playersList;
    private ArrayList<String> playersChecked;
    private String layerName = "Conveyor";
    
    public MoveConveyorBelts(BoardLogic boardLogic, ArrayList<PlayerToken> playersList) {
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
                if(checkDirectionAndThenMove(player, dir, xPos, yPos))
                    return;
            }
        }
    }

    /*
     * Checks if there is a conveyorbelt in direction dir, if yes, moves that direction. 
     * If a player stands in its path, it will first process that player.
     */
    private boolean checkDirectionAndThenMove(PlayerToken player, Direction dir, int xPos, int yPos) {
        if(boardLogic.cellContainsLayerWithKey(xPos, yPos, layerName, dir.toString())) {
            PlayerToken otherPlayer = checkForPlayer(dir, xPos, yPos);
            if(otherPlayer != null) {
                movePlayerIfOnConveyorBelt(otherPlayer);
            }
            boardLogic.movePlayer(player.getName(), dir);
            return true;
        }
        return false;
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
