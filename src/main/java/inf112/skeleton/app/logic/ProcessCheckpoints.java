package inf112.skeleton.app.logic;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.objects.PlayerToken;

public class ProcessCheckpoints implements IBoardFeature {
    
    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playersList;
    private String layerName = "Checkpoints";
    
    public ProcessCheckpoints(BoardLogic boardLogic, ArrayList<PlayerToken> playersList) {
        this.playersList = playersList;
        this.boardLogic = boardLogic;
    }
    
    public void processFeature() {
        for(PlayerToken player : playersList) {
            if(checkIfOnRightCheckpoint(player)) {
                player.passCheckpoint();
            }
        }
    }

    private boolean checkIfOnRightCheckpoint(PlayerToken player) {
        if(boardLogic.cellContainsLayer(player.getXPosition(), player.getYPosition(), layerName)) {
            return boardLogic.cellContainsLayerWithKey(player.getXPosition(), player.getYPosition(), layerName, Integer.toString(player.numberOfCheckpointsPassed() + 1));
        } 
        return false;
    }
    
}
