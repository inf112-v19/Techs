package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.PlayerToken;
import java.util.ArrayList;

public class Lasers implements IBoardFeature {

    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playersList;
    private ArrayList<String> playersChecked;
    private String layerName = "Laser";

    public Lasers (BoardLogic boardLogic, ArrayList<PlayerToken> playerList) {
        this.boardLogic = boardLogic;
        this.playersList = playerList;
    }

    @Override
    public void processFeature() {
        playersChecked = new ArrayList<String>();
        for (PlayerToken player : playersList) {
            checkPlayerInLaser(player);
        }
    }

    private void checkPlayerInLaser(PlayerToken player) {
        if (playersChecked.contains(player.getName())) {
            return;
        }

    }
}
