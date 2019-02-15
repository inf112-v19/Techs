package inf112.skeleton.app.Tiles;

public class RepairSite implements ITile {

    private String name = "repairSite";
    private String symbol = "R";
    private int weight = 2;

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
}
