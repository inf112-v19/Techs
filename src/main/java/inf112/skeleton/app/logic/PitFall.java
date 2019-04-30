package inf112.skeleton.app.logic;


import inf112.skeleton.app.objects.PlayerToken;
import inf112.skeleton.app.screens.Board;

public class PitFall {

	private BoardLogic board;
	private PlayerToken player;
	private String layerName = "Pit";

	public PitFall(PlayerToken player, BoardLogic board) {
		this.player = player;
		this.board = board;
	}

	public void processFeatureCheckForPitFalls(PlayerToken player) {
		movePlayerIfPitFall(player, board);
		// should lose a damagetoken when implemented
	}

	public void movePlayerIfPitFall(PlayerToken player, BoardLogic board) {
		if (checkIfPitfall(player, board)) {
			player.setDamageToken(10);
			player.setDestroyed(true);
			player.moveToDeathPosition();
			return;
		} 
		return;
	}
	
	public boolean checkIfPitfall(PlayerToken player, BoardLogic board) {
		
		int xPos = player.getXPosition();
		int yPos = player.getYPosition();
		
		if(board.cellContainsLayer(xPos, yPos, layerName)) {
			System.out.println(player.getName() + " fell into pit. Moved to last backup");
			return true;
		}
		return false;
	}
}
