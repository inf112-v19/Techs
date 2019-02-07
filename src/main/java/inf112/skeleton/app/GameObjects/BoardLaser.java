package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.IItem;

public class BoardLaser implements IItem {
    private String name = "boardLaser";
    private String symbol = "BL";
    private int weight = 3;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
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
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
