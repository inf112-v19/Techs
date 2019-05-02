package inf112.skeleton.app.logic;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.PlayerToken;
import java.util.ArrayList;

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
        // Moves all player on double conveyor belts
        playersChecked = new ArrayList<String>();
        for (PlayerToken player : playersList) {
            movePlayerIfOnConveyorBelt(player, true);
        }
        // Moves all player on single or double conveyor belts
        playersChecked = new ArrayList<String>();
        for (PlayerToken player : playersList) {
            movePlayerIfOnConveyorBelt(player, false);
        }
    }
    
    private void movePlayerIfOnConveyorBelt(PlayerToken player, boolean moveDouble) {
        if(playersChecked.contains(player.getName())) {
            return;
        }
        
        playersChecked.add(player.getName());
        int xPos = player.getXPosition();
        int yPos = player.getYPosition();

        if(boardLogic.cellContainsLayer(xPos, yPos, layerName)) {
            for(Direction dir : Direction.values()) {
                if(moveDouble && !(boardLogic.cellContainsLayerWithKey(xPos, yPos, layerName, "DOUBLE"))) {
                    System.out.println(moveDouble);
                    continue;
                }
                if(hasConveyorPointingToDirection(xPos, yPos, dir)) {
                    // Moves other player on conveyer belts that are standing in the way first  
                    PlayerToken otherPlayer = checkForPlayer(xPos, yPos, dir);
                    if(otherPlayer != null) {
                        movePlayerIfOnConveyorBelt(otherPlayer, moveDouble);
                    }
                    boardLogic.movePlayer(player.getName(), dir);
                    rotatePlayerIfMovedToRotatingConveyor(player, xPos, yPos, dir);
                    return;
                }
            }
        }
    }

    private boolean hasConveyorPointingToDirection(int xPos, int yPos, Direction dir) {
        return boardLogic.cellContainsLayerWithKey(xPos, yPos, layerName, dir.toString());
    }

    private PlayerToken checkForPlayer(int xPos, int yPos, Direction dir) {
        Vector2 checkPosition = boardLogic.addDirectionToLocation(xPos, yPos, dir);

        for(PlayerToken player : playersList) {
            if(player.getVector2Position().equals(checkPosition)) {
                return player;
            }
        }
        return null;
    }

    private void rotatePlayerIfMovedToRotatingConveyor(PlayerToken player, int xPos, int yPos, Direction dir) {
        if(xPos != player.getXPosition() || yPos != player.getYPosition()) {
            if(boardLogic.cellContainsLayerWithKey(player.getXPosition(), player.getYPosition(), layerName, dir.toString() + "RotateLeft")) {
                boardLogic.rotatePlayer(player.getName(), -1);
            }
            else if(boardLogic.cellContainsLayerWithKey(player.getXPosition(), player.getYPosition(), layerName, dir.toString() + "RotateRight")) {
                boardLogic.rotatePlayer(player.getName(), 1);;
            }
        }
    }
}
