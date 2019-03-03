package inf112.skeleton.app.tiles;

import inf112.skeleton.app.Direction;

public class BoardLaser implements IBoardLaser {

    private String name = "boardLaser";
    private String symbol = "BL";
    private int weight = 3;
    private int distance = 2;
    private Direction direction = Direction.SOUTH;

    @Override
    public String getTileType() {
        return name;
    }

    @Override
    public void setTileType(String name) {
        this.name = name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int getTileWeight() {
        return 0;
    }

    @Override
    public void setTileWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getTileID() {
        return 0;
    }

    @Override
    public void setTileID(int id) {

    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
