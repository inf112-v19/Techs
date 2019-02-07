package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.IItem;

public class RepairSite implements IItem {

    private String name = "repairSite";
    private String symbol = "R";
    private int weight = 2;

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
