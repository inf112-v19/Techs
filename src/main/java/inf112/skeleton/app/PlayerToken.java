package inf112.skeleton.app;

public class PlayerToken implements IPlayer {

    private String name;
    private String symbol;
    private int health;
    
    public PlayerToken(String name) {
        this.name = name;
        this.health = 9;
    }
    
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
        return 0;
    }

    @Override
    public void setWeight(int weight) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        // TODO Auto-generated method stub
        
    }

}
