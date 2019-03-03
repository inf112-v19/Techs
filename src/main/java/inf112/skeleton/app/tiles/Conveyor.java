package inf112.skeleton.app.tiles;

import inf112.skeleton.app.Direction;

public class Conveyor implements IConveyorBelt {

    String tileType = "conveyor";
    int tileID = 4;
    int tileWeight = 4;
    int speed = 1;

    @Override
    public String getTileType() {
        return this.tileType;
    }

    @Override
    public void setTileType(String name) {
        this.tileType = name;
    }

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public void setSymbol(String symbol) {

    }

    @Override
    public int getTileWeight() {
        return this.tileWeight;
    }

    @Override
    public void setTileWeight(int weight) {
        this.tileWeight = weight;
    }

    @Override
    public int getTileID() {
        return this.tileID;
    }

    @Override
    public void setTileID(int id) {
        this.tileID = id;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void setDirection(Direction direction) {

    }
}
