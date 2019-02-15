package inf112.skeleton.app.Tiles;

public class Floor implements ITile {

    String tileType = "floor";
    int tileID;
    int tileWeight;

    public Floor() {
        this.tileID = 1;
        this.tileWeight = 1;
    }

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
}