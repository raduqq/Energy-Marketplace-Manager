package game.entity.player;

public abstract class Player {
    private int id;
    private int budget;
    private boolean isBankrupt;

    public Player(final int id,
                  final int budget) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = false;
    }

    /**
     * Extension should not cause any problems
     * @return player id
     */
    public int getId() {
        return id;
    }
    /**
     * Extension should not cause any problems
     * @return player budget (health)
     */
    public int getBudget() {
        return budget;
    }
    /**
     * Extension should not cause any problems
     * @param id to set
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**
     * Extension should not cause any problems
     * @param budget to set
     */
    public void setBudget(final int budget) {
        this.budget = budget;
    }
    /**
     * Extension should not cause any problems
     * @return player status (alive/dead)
     */
    public boolean getIsBankrupt() {
        return isBankrupt;
    }
    /**
     * Extension should not cause any problems
     * @param bankrupt status to set
     */
    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
    /**
     * Updates player state each round
     */
    public abstract void roundUpdate();
    /**
     * Turn taking method called every round
     */
    public abstract void takeTurn();
    /**
     * Method for going bankrupt
     */
    public abstract void goBankrupt();
}
