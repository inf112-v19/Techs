package inf112.skeleton.app;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class MoveConveyorBelts implements IBoardFeature {

    private BoardLogic boardLogic;
    private ArrayList<PlayerToken> playersList;
    private ArrayList<String> playersChecked;
    private String layerName = "Conveyor";
    private int xPos;
    private int yPos;
    
    public MoveConveyorBelts(BoardLogic boardLogic, ArrayList<PlayerToken> playersList) {
        this.playersList = playersList;
        this.boardLogic = boardLogic;
    }
    
    @Override
    public void processFeature() {
        playersChecked = new ArrayList<String>();
        
        for(PlayerToken player : playersList) {
            if(!playersChecked.contains(player.getName())) {
                movePlayerIfOnConveyorBelt(player);
                playersChecked.add(player.getName());
            }
        }
    }
    
    private void movePlayerIfOnConveyorBelt(PlayerToken player) {
        xPos = player.getXPosition();
        yPos = player.getYPosition();
        
        if(boardLogic.cellContainsLayerWithKey(xPos, yPos, layerName)) {
            if(checkDirection(Direction.NORTH, boardLogic, xPos, yPos)) {
                PlayerToken otherPlayer = checkForPlayer(Direction.NORTH);
                
                    
            }
            boardLogic.movePlayer(player.getName(), Direction.NORTH);
            } else if (checkDirection(Direction.EAST, boardLogic, xPos, yPos)) {
                boardLogic.movePlayer(player.getName(), Direction.EAST);
            } else if (checkDirection(Direction.SOUTH, boardLogic, xPos, yPos)) {
                boardLogic.movePlayer(player.getName(), Direction.SOUTH);
            } else if (checkDirection(Direction.WEST, boardLogic, xPos, yPos)) {
                boardLogic.movePlayer(player.getName(), Direction.WEST);
            }
    }

    private boolean checkDirection(Direction dir, Board board, int xPos, int yPos) {
        return board.cellContainsLayerWithKey(xPos, yPos, layerName, dir.toString());
    }
    
    private PlayerToken checkForPlayer(Direction dir) {        
        Vector2 checkPosition = addDirectionToLocation(xPos, yPos, dir);

        for(PlayerToken player : playersList) {
            if(player.getVector2Position().equals(checkPosition)) {
                return player;
            }
        }
        return null;
    }
    
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
