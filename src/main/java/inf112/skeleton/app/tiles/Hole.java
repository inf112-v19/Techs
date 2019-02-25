package inf112.skeleton.app.tiles;

public class Hole implements ITile {

    String tileType = "hole";
    int tileID = 3;
    int tileWeight = 3;

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