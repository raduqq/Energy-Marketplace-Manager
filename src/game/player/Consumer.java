package game.player;

import game.element.Contract;

public final class Consumer extends Player {
    private int monthlyIncome;
    private Contract currContract;
    private Contract overdueContract;

    public Consumer(int id, int budget, int monthlyIncome) {
        super(id, budget);
        this.monthlyIncome = monthlyIncome;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Contract getCurrContract() {
        return currContract;
    }

    public void setCurrContract(Contract currContract) {
        this.currContract = currContract;
    }

    public Contract getOverdueContract() {
        return overdueContract;
    }

    public void setOverdueContract(Contract overdueContract) {
        this.overdueContract = overdueContract;
    }

    @Override
    public void takeTurn() {

    }

    @Override
    public void update() {
        setBudget(getBudget() + getMonthlyIncome());
    }
}
