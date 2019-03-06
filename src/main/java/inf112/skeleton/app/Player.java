package inf112.skeleton.app;

import java.util.ArrayList;

public class Player implements IPlayer{

    String name;
    int health;
    int weight;
    int indexOnGrid;
    boolean powerDownStatus = false;
    ArrayList<ProgramCard> playerHand;
    

    @Override
    public int getPlayerIndexOnGrid() {
        return indexOnGrid;
    }

    public Player(String name) {
        this.name = name;
        this.health = 10;
        this.weight = 10;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void setPlayerIndexOnGrid(int indexOnGrid) {
        this.indexOnGrid = indexOnGrid;
    }
    
    public boolean getPowerDownStatus() {
    	return powerDownStatus;
    }
    
    public void powerDown() {
    	powerDownStatus = true;
    }

}
