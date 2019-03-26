package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.PlayerToken;
import java.util.ArrayList;

public class RotateWheel implements IBoardFeature {

    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playerList;
    private ArrayList<String> playersChecked;

    public RotateWheel(BoardLogic boardLogic, ArrayList<PlayerToken> playerList) {
        this.playerList = playerList;
        this.boardLogic = boardLogic;
    }

    private void movePlayerIfOnRotateWheel(PlayerToken player){
        if (playersChecked.contains((player.getName()))) {
            return;
        }

        playersChecked.add(player.getName());
        int xPos = player.getXPosition();
        int yPos = player.getYPosition();

        if (boardLogic.cellContainsLayer(xPos, yPos, "RotateWheel")) {
            player.rotatePlayer(-1);
        }

        if (boardLogic.cellContainsLayer(xPos, yPos, "RotateRight")) {
            player.rotatePlayer(1);
        }
    }

    @Override
    public void processFeature() {
        playersChecked = new ArrayList<String>();

        for (PlayerToken player : playerList) {
            movePlayerIfOnRotateWheel(player);
        }
    }
}
