package game.player;

public abstract class Player {
    private int id;
    private int budget;
    private boolean isBankrupt;

    public Player(int id, int budget) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = false;
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public abstract void update();
    public abstract void takeTurn();
    public abstract void goBankrupt();
}
