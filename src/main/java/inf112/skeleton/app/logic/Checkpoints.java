package inf112.skeleton.app.logic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.objects.PlayerToken;

public class Checkpoints implements IBoardFeature {
    
    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playersList;
    private String layerName = "Checkpoints";
    
    public Checkpoints(BoardLogic boardLogic, ArrayList<PlayerToken> playersList) {
        this.playersList = playersList;
        this.boardLogic = boardLogic;
    }
    
    public void processFeature() {
        for (PlayerToken player : playersList) {
            if(checkIfOnRightCheckpoint(player)) {
                player.passCheckpoint();
            }
            if(player.numberOfCheckpointsPassed() == 3) {
                System.out.println("The winner is: " + player.getName());
                Gdx.app.exit();
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
