package inf112.skeleton.app.logic;


import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;

public class PitFall {

	private BoardLogic board;
	private ArrayList<PlayerToken> playerList;
	private String layerName = "Pit";

	public PitFall(BoardLogic board, ArrayList<PlayerToken> playerList) {
		this.playerList = playerList;
		this.board = board;
	}

	public void processFeature() {
		for (PlayerToken player : playerList) {
			movePlayerIfPitFall(player);
		}
	}

	public void movePlayerIfPitFall(PlayerToken player) {
		if (checkIfPitfall(player)) {
			player.setDamageToken(10);
			player.damageCleanup();
		}
	}
	
	public boolean checkIfPitfall(PlayerToken player) {
		
		int xPos = player.getXPosition();
		int yPos = player.getYPosition();
		
		if(board.cellContainsLayer(xPos, yPos, layerName)) {
			System.out.println(player.getName() + " fell into pit.");
			return true;
		}
		return false;
	}
}
