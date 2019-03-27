package inf112.skeleton.app.logic;

import inf112.skeleton.app.objects.PlayerToken;

import java.util.ArrayList;

public interface IMovePlayer {

    /**
     * @param directionToMove Decides which direction the robot should move
     * @param playerToMove Which player to move.
     * @return Returns true if the robot is able to do the move, false otherwise.
     */
    boolean movePlayer(Direction directionToMove, PlayerToken playerToMove);

    /**
     * @param xPos X position to be checked.
     * @param yPos Y position to be checked.
     * @param directionToMove Which direction to be checked.
     * @return True TODO: finish this
     */
    boolean moveOtherPlayers(int xPos, int yPos, Direction directionToMove);

    /**
     * @param playersList list with players to update.
     */
    void updatePlayersList(ArrayList<PlayerToken> playersList);
}
