package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.PlayerToken;
import java.util.ArrayList;

public class RotateLeft implements IBoardFeature {

    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playerList;
    private ArrayList<String> playersChecked;
    private String layerName = "RotateLeft";

    public RotateLeft(BoardLogic boardLogic, ArrayList<PlayerToken> playerList) {
        this.playerList = playerList;
        this.boardLogic = boardLogic;
    }

    @Override
    public void processFeature() {
        playersChecked = new ArrayList<String>();

        for (PlayerToken player : playerList) {
            movePlayerIfOnRotateLeft(player);
        }
    }

    private void movePlayerIfOnRotateLeft(PlayerToken player){
        if (playersChecked.contains((player.getName()))) {
            return;
        }

        playersChecked.add(player.getName());
        int xPos = player.getXPosition();
        int yPos = player.getYPosition();

        if (boardLogic.cellContainsLayer(xPos, yPos, layerName)) {
                player.rotatePlayer(-1);
        }
    }
}
